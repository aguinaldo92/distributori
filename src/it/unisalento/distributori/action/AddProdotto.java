package it.unisalento.distributori.action;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import it.unisalento.distributori.domain.Categoria;
import it.unisalento.distributori.domain.Famiglia;
import it.unisalento.distributori.domain.FamiglieProdotto;
import it.unisalento.distributori.domain.Prodotto;
import it.unisalento.distributori.domain.Stabilimento;
import it.unisalento.distributori.factory.FactoryDao;
import it.unisalento.distributori.model.ProdottoModel;

public class AddProdotto extends ActionSupport implements ModelDriven<ProdottoModel>{
	private static final long serialVersionUID = -986495379892706519L;
	private ProdottoModel prodotto_Form = new ProdottoModel();
	private String famiglia_scelta; 
	private ArrayList<Categoria> all_categ = new ArrayList<Categoria>();
	private ArrayList<Famiglia> famiglie = new ArrayList<Famiglia>();
	private Logger logger = LogManager.getLogger(this.getClass().getName());

	public String execute() {
		try {
			logger.debug("execute()");
			Prodotto new_prodotto=new Prodotto();
			//settaggio caratteristiche prodotto
			new_prodotto.setCategoria(FactoryDao.getIstance().getCategoriaDao().get(prodotto_Form.getCategoria().getId(), Categoria.class));
			new_prodotto.setDescrizione(prodotto_Form.getDescrizione());
			new_prodotto.setIngredienti(prodotto_Form.getIngredienti());
			new_prodotto.setNome(prodotto_Form.getNome());
			new_prodotto.setPreparazione(prodotto_Form.getPreparazione());
			new_prodotto.setPrezzo(new BigDecimal(prodotto_Form.getPrezzo()));
			new_prodotto.setScontoUtenti(BigDecimal.valueOf(Integer.parseInt(prodotto_Form.getSconto())).divide(BigDecimal.valueOf(100)));
			new_prodotto.setStabilimento(FactoryDao.getIstance().getStabilimentoDao().get(prodotto_Form.getStabilimento().getId(), Stabilimento.class));
			String noImagePath = "images/no_image.jpg";
			new_prodotto.setFoto(noImagePath);
			//inserimento nel DATABASE del prodotto
			new_prodotto.setId(FactoryDao.getIstance().getProdottoDao().set(new_prodotto));

			//settaggio delle famiglie e inserimento nel DATABASE
			prodotto_Form.setIDsfamiglie(famiglia_scelta);
			FamiglieProdotto fam_prod_obj = new FamiglieProdotto();
			for (int indexIdFamiglia=0; indexIdFamiglia<prodotto_Form.getIDsfamiglie().size();indexIdFamiglia++){
				fam_prod_obj.setFamiglia(FactoryDao.getIstance().getFamigliaDao().get(prodotto_Form.getIDsfamiglie().get(indexIdFamiglia), Famiglia.class));
				fam_prod_obj.setProdotto(new_prodotto);
				FactoryDao.getIstance().getFamiglieProdottoDao().set(fam_prod_obj);
			}
			ServletActionContext.getRequest().setAttribute("idNewProdotto", new_prodotto.getId());
			return SUCCESS;
			
		} catch ( HibernateException sqle) {
			logger.error("Impossibile registrare il nuovo Prodotto a causa di un errore nel salvataggio sul database",sqle);
			addActionError("Impossibile registrare il nuovo Dipendente a causa di un errore nel salvataggio. Controllare che i campi siano correttamente compilati e riprovare o contattare l'amministratore del sistema");
			return INPUT;
		} catch (Exception e) {
			logger.error("Impossibile registrare il nuovo Dipendente a causa di un errore non derivante dal database",e);
			return ERROR;
		}
	}

	public void validate(){
		logger.debug("validate()");
		if(famiglia_scelta.length()==0){
			logger.debug("AddProdotto: nessuna famiglia selezionata");
			addFieldError("nofamilyselected", "Selezionare ALMENO una famiglia di prodotti.");
		}
	}


	// getters and setters

	public String getFamiglia_scelta() {
		return famiglia_scelta;
	}

	public void setFamiglia_scelta(String famiglia_scelta) {
		this.famiglia_scelta = famiglia_scelta;
	}

	public ArrayList<Categoria> getAll_categ() {
		return all_categ;
	}

	public void setAll_categ(ArrayList<Categoria> all_categ) {
		this.all_categ = all_categ;
	}

	public ArrayList<Famiglia> getFamiglie() {
		return famiglie;
	}

	public void setFamiglie(ArrayList<Famiglia> famiglie) {
		this.famiglie = famiglie;
	}

	@Override
	public ProdottoModel getModel() {
		return prodotto_Form;
	}
}