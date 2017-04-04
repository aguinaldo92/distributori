package it.unisalento.distributori.action;

import java.util.Iterator;
import java.util.List;
import com.opensymphony.xwork2.ActionSupport;

import it.unisalento.distributori.domain.ProdottiErogati;
import it.unisalento.distributori.factory.FactoryDao;
import it.unisalento.distributori.model.ProdottiDistributoreModel;

public class UpdateQuantitaProdottiByDistributore extends ActionSupport {
	private static final long serialVersionUID = 7327382666954167073L;

	private ProdottiDistributoreModel currentDettaglioDistributoreModel;
	private ProdottiErogati prodottiErogatiUpdated;

	private Boolean updateSuccess = false;
	private Integer idDistributore;
	private List<Integer> ids;
	private List<Integer> quantita;
	private List<Integer> oldQuantita;

	public Integer getIdDistributore() {
		return idDistributore;
	}

	public void setIdDistributore(Integer idDistributore) {
		this.idDistributore = idDistributore;
	}

	public List<Integer> getOldQuantita() {
		return oldQuantita;
	}

	public void setOldQuantita(List<Integer> oldQuantita) {
		this.oldQuantita = oldQuantita;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	public List<Integer> getQuantita() {
		return quantita;
	}

	public void setQuantita(List<Integer> quantita) {
		this.quantita = quantita;
	}

	public ProdottiDistributoreModel getCurrentDettaglioDistributoreModel() {
		return currentDettaglioDistributoreModel;
	}

	public void setCurrentDettaglioDistributoreModel(ProdottiDistributoreModel currentDettaglioDistributoreModel) {
		this.currentDettaglioDistributoreModel = currentDettaglioDistributoreModel;
	}


	public String execute() {
		try {
			System.out.println("idDistributore: " + idDistributore);
			Iterator<Integer> idsIterator = ids.iterator();
			Iterator<Integer> quantitaIterator = quantita.iterator();
			if ( ids.size() == quantita.size() ) {
				while (idsIterator.hasNext()) {
					Integer newQuantita = quantitaIterator.next();
					prodottiErogatiUpdated = FactoryDao.getIstance().getProdottiErogatiDao().get(idsIterator.next(),ProdottiErogati.class);
					if (!prodottiErogatiUpdated.getQuantita().equals(newQuantita)) {
						prodottiErogatiUpdated.setQuantita(newQuantita);
						FactoryDao.getIstance().getProdottiErogatiDao().update(prodottiErogatiUpdated);
					}
				}
			} else {
				System.out.println("Errore ids.size diverso da quantita.size");
			}
			updateSuccess = true;
		} catch (Exception e) {
			System.out.println("ERRORE UpdateQuantitaprodottoByDistributore: " + e.getMessage());

		}

		return SUCCESS;
	}

}
