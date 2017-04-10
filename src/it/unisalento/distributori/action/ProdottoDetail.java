package it.unisalento.distributori.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import it.unisalento.distributori.domain.FamiglieProdotto;
import it.unisalento.distributori.domain.Prodotto;
import it.unisalento.distributori.factory.FactoryDao;
import it.unisalento.distributori.model.ProdottoModel;

public class ProdottoDetail extends ActionSupport {
	private static final long serialVersionUID = 1207581611533617L;
	private int idProdotto;
	private ProdottoModel prodotto = new ProdottoModel();
	private Logger logger = LogManager.getLogger(this.getClass().getName());

	public String execute () {
		try{
			logger.debug("execute()");		
			Prodotto prod_scelto=FactoryDao.getIstance().getProdottoDao().get(idProdotto, Prodotto.class);
			prodotto.setDescrizione(prod_scelto.getDescrizione());
			prodotto.setFoto(prod_scelto.getFoto());
			prodotto.setCategoria(prod_scelto.getCategoria());
			prodotto.setProduttore(prod_scelto.getStabilimento().getProduttore());
			prodotto.setStabilimento(prod_scelto.getStabilimento());
			prodotto.setIngredienti(prod_scelto.getIngredienti());
			prodotto.setNome(prod_scelto.getNome());
			prodotto.setPreparazione(prod_scelto.getPreparazione());
			prodotto.setPrezzo(prod_scelto.getPrezzo().toString());
			prodotto.setSconto(Integer.toString(prod_scelto.getScontoUtenti().multiply(new BigDecimal("100")).intValue()));

			List<FamiglieProdotto> listFamiglieProdotto = new ArrayList<FamiglieProdotto>();
			listFamiglieProdotto.addAll(prod_scelto.getFamiglieProdottos());
			List<Integer> idFamiglieScelte = new ArrayList<Integer>();
			for (int indexFamigliaProdotto = 0;indexFamigliaProdotto < listFamiglieProdotto.size() ; indexFamigliaProdotto++){
				idFamiglieScelte.add(listFamiglieProdotto.get(indexFamigliaProdotto).getFamiglia().getId());
			}
			prodotto.setIDsfamiglie(idFamiglieScelte);

			

			return SUCCESS;
			
		} catch (Exception e){
			logger.error("Impossibile caricare le informazioni di dettaglio del prodotto con ID: "+ idProdotto,e);
			addActionError("Impossibile caricare le informazioni di dettaglio del prodotto scelto");
			return ERROR;
		}
	}


	public ProdottoModel getProdotto() {
		return prodotto;
	}

	public void setProdotto(ProdottoModel prodotto) {
		this.prodotto = prodotto;
	}

	public int getIdProdotto() {
		return idProdotto;
	}


	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}
}