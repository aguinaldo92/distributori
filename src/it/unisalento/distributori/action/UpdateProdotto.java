package it.unisalento.distributori.action;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import it.unisalento.distributori.domain.Categoria;
import it.unisalento.distributori.domain.Famiglia;
import it.unisalento.distributori.domain.FamiglieProdotto;
import it.unisalento.distributori.domain.Prodotto;
import it.unisalento.distributori.domain.Stabilimento;
import it.unisalento.distributori.factory.FactoryDao;
import it.unisalento.distributori.model.ProdottoModel;

public class UpdateProdotto extends ActionSupport implements ModelDriven<ProdottoModel>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3349557042562383110L;
	private String famiglia_scelta;
	private Integer idProdotto;
	private ProdottoModel prodotto_form = new ProdottoModel();
	private Logger logger = LogManager.getLogger(this.getClass().getName());


	public String execute()  {
		try{
			logger.debug("execute()");
			prodotto_form.setIDsfamiglie(famiglia_scelta);
			Prodotto newprodotto = FactoryDao.getIstance().getProdottoDao().get(idProdotto, Prodotto.class);
			//aggiornamento del prodotto con le info del form
			newprodotto.setCategoria(FactoryDao.getIstance().getCategoriaDao().get(prodotto_form.getCategoria().getId(), Categoria.class));
			newprodotto.setDescrizione(prodotto_form.getDescrizione());
			newprodotto.setIngredienti(prodotto_form.getIngredienti());
			newprodotto.setNome(prodotto_form.getNome());
			newprodotto.setPreparazione(prodotto_form.getPreparazione());
			newprodotto.setPrezzo(new BigDecimal(prodotto_form.getPrezzo()));
			newprodotto.setStabilimento(FactoryDao.getIstance().getStabilimentoDao().get(prodotto_form.getStabilimento().getId(), Stabilimento.class));

			BigDecimal sconto_decimale = BigDecimal.valueOf(Integer.parseInt(prodotto_form.getSconto())).divide(BigDecimal.valueOf(100));
			newprodotto.setScontoUtenti(sconto_decimale);

			//inserimento nel DATABASE
			FactoryDao.getIstance().getProdottoDao().update(newprodotto);

			//eliminazione di tutte le famiglie associate al prodotto dal DATABASE
			FactoryDao.getIstance().getFamiglieProdottoDao().deleteProdottoFamilies(newprodotto.getId());

			//aggiornamento della relazione con le famiglie del form e inserimento nel DATABASE
			FamiglieProdotto fam_prod_obj = new FamiglieProdotto();
			for (int i=0; i<prodotto_form.getIDsfamiglie().size();i++){
				fam_prod_obj.setFamiglia(FactoryDao.getIstance().getFamigliaDao().get(prodotto_form.getIDsfamiglie().get(i), Famiglia.class));
				fam_prod_obj.setProdotto(newprodotto);
				FactoryDao.getIstance().getFamiglieProdottoDao().set(fam_prod_obj);
			}

			return SUCCESS;
		} catch (Exception e){
			logger.error("Impossibile aggiornare il prodotto con id: "+idProdotto,e);
			addActionError("Impossibile aggiornare il prodotto con le informazioni inserite");
			return ERROR;
		}
	}

	public void validate(){
		try{
			logger.debug("validate()");
			prodotto_form.setIDsfamiglie(famiglia_scelta);
			ServletActionContext.getRequest().setAttribute("prodotto", prodotto_form);

			if(famiglia_scelta.length()==0){
				addFieldError("nofamilyselected", "Selezionare ALMENO una famiglia di prodotti.");
			}
		} catch (Exception e){
			logger.error("Impossibile effettuare la validation per l'update prodotto",e);
		}
	}

	@Override
	public ProdottoModel getModel() {
		return prodotto_form;
	}


	public Integer getIdProdotto() {
		return idProdotto;
	}
	public void setIdProdotto(Integer idProdotto) {
		this.idProdotto = idProdotto;
	}
	public String getFamiglia_scelta() {
		return famiglia_scelta;
	}
	public void setFamiglia_scelta(String famiglia_scelta) {
		this.famiglia_scelta = famiglia_scelta;
	}

}