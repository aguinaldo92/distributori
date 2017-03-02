package it.unisalento.distributori.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import it.unisalento.distributori.domain.ProdottiErogati;
import it.unisalento.distributori.factory.FactoryDao;
import it.unisalento.distributori.model.DettaglioDistributoreModel;

public class UpdateQuantitaProdottiByDistributore extends ActionSupport {
	private static final long serialVersionUID = 7327382666954167073L;

	private DettaglioDistributoreModel currentDettaglioDistributoreModel;
	private ProdottiErogati prodottiErogatiUpdated;

	// private DettaglioDistributoreModel dettaglio = new
	// DettaglioDistributoreModel();
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

	public DettaglioDistributoreModel getCurrentDettaglioDistributoreModel() {
		return currentDettaglioDistributoreModel;
	}

	public void setCurrentDettaglioDistributoreModel(DettaglioDistributoreModel currentDettaglioDistributoreModel) {
		this.currentDettaglioDistributoreModel = currentDettaglioDistributoreModel;
	}

	public Boolean getUpdateSuccess() {
		return updateSuccess;
	}

	public void setUpdateSuccess(Boolean updateSuccess) {
		this.updateSuccess = updateSuccess;
	}

	public String execute() {
		updateSuccess = true;
		try {
			System.out.println("idDistributore: " + idDistributore);
			Iterator<Integer> idsIterator = ids.iterator();
			//			Iterator<Integer> oldQuantitaIterator = oldQuantita.iterator();
			Iterator<Integer> quantitaIterator = quantita.iterator();
			if ( ids.size() == quantita.size() ) {
				while (idsIterator.hasNext()) {
					Integer newQuantita = quantitaIterator.next();

					//						prodottiErogatiUpdated = new ProdottiErogati();
					prodottiErogatiUpdated = FactoryDao.getIstance().getProdottiErogatiDao().get(idsIterator.next(),ProdottiErogati.class);
					if (prodottiErogatiUpdated.getQuantita().equals(newQuantita)) {
						idsIterator.next();
					} else {
						prodottiErogatiUpdated.setQuantita(newQuantita);
						FactoryDao.getIstance().getProdottiErogatiDao().update(prodottiErogatiUpdated);
						idsIterator.next();
					}

				}
			} else {
				System.out.println("Errore ids.size diverso da quantita.size");
			}
		} catch (Exception e) {
			System.out.println("ERRORE UpdateQuantitaprodottoByDistributore: " + e.getMessage());

		}

		return SUCCESS;
	}

}
