package it.unisalento.distributori.action;

import java.util.Iterator;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import it.unisalento.distributori.domain.ProdottiErogati;
import it.unisalento.distributori.domain.Prodotto;
import it.unisalento.distributori.factory.FactoryDao;

public class UpdateProdottiErogati extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3190075446931346804L;
	private List<Integer> listIdsProdottiErogati;
	private Integer idDistributore;
	private List<Integer> listIdsProdotti;
	private ProdottiErogati prodottiErogatiUpdated;
	private Boolean updateSuccess = false;
	

	
	public Integer getIdDistributore() {
		return idDistributore;
	}



	public void setIdDistributore(Integer idDistributore) {
		this.idDistributore = idDistributore;
	}



	public List<Integer> getListIdsProdottiErogati() {
		return listIdsProdottiErogati;
	}



	public void setListIdsProdottiErogati(List<Integer> listIdsProdottiErogati) {
		this.listIdsProdottiErogati = listIdsProdottiErogati;
	}





	public List<Integer> getListIdsProdotti() {
		return listIdsProdotti;
	}



	public void setListIdsProdotti(List<Integer> listIdsProdotti) {
		this.listIdsProdotti = listIdsProdotti;
	}



	public String execute() {
		try {
			System.out.println("idDistributore: " + idDistributore);
			// iteratore sulla lista degli id dei prodotti intesi come Prodotto
			Iterator<Integer> idsProdottiIterator = listIdsProdotti.iterator();
			// iteratore sulla lista degli id dei prodotti intesi come ProdottoErogato, ossia quell'id è riferito all'id della relazione tra Distributore e Prodotto
			Iterator<Integer> idsProdottiErogatiIterator = listIdsProdottiErogati.iterator();
			if ( listIdsProdotti.size() == listIdsProdottiErogati.size() ) {
				while (idsProdottiErogatiIterator.hasNext()) {
					// seleziono un prodotto da erogare nella lista (potrebbe essere già in fase di erogazione)
					Integer newIdProdottoDaErogare = idsProdottiIterator.next();
					// ottengo il prodotto associato al prodotto erogato tramite l'id della relazione prodotti erogati
					prodottiErogatiUpdated = FactoryDao.getIstance().getProdottiErogatiDao().get(idsProdottiErogatiIterator.next(),ProdottiErogati.class);
					// se l'id del prodotto associato al prodotto erogato è diverso rispetto all'id del prodotto visualizzato nella selectBox..
					if (!prodottiErogatiUpdated.getProdotto().getId().equals(newIdProdottoDaErogare)) {
						// imposto il Prodotto associato al ProdottoErogato della macchinetta 
						prodottiErogatiUpdated.setProdotto(FactoryDao.getIstance().getProdottoDao().get(newIdProdottoDaErogare, Prodotto.class));
						// essendo un nuovo prodotto da erogare la sua quantità nella macchinetta sarà 0. Il dipendente incaricato vedrà un prodotto con zero
						// elementi ed andrà a controllare cosa deve inserire, rimuovendo i prodotti presenti del dipo di prodotto diverso.
						prodottiErogatiUpdated.setQuantita(0);
						// aggiorno il ProdottoErogato con il Prodotto scelto
						FactoryDao.getIstance().getProdottiErogatiDao().update(prodottiErogatiUpdated);
					}
				}
			} else {
				System.out.println("Errore listIdsProdotti.size diverso da listIdsProdottiErogati.size");
			}
			updateSuccess = true;
		} catch (Exception e) {
			System.out.println("ERRORE UpdateQuantitaprodottoByDistributore: " + e.getMessage());

		}
		
		
		return SUCCESS;
	}

}
