/**
 * 
 */
package it.unisalento.distributori.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.opensymphony.xwork2.ActionSupport;

import it.unisalento.distributori.dao.DistributoreDao;
import it.unisalento.distributori.domain.Distributore;
import it.unisalento.distributori.domain.ProdottiErogati;
import it.unisalento.distributori.domain.Prodotto;
import it.unisalento.distributori.factory.FactoryDao;
import it.unisalento.distributori.model.DettaglioDistributoreModel;

/**
 * @author aguinaldo
 *
 */
public class ProdottiDistributore extends ActionSupport {
	private static final long serialVersionUID = 3181980036211349123L;
	private Integer idDistributore;
	private Distributore distributore;
	private String indirizzoDistributore;
	private String posizioneEdificioDistributore;
	private Integer scaffali ;
	private Integer posti;
	private ArrayList<ProdottiErogati> listProdottiErogati;
	private ArrayList<String> listNomiCategorieFornite;
	private DettaglioDistributoreModel  currentDettaglioDistributoreModel;
	private ProdottiErogati  currentProdottiErogati;
	private ArrayList<ArrayList<DettaglioDistributoreModel>> listProdottiErogatixScaffale;
	private ArrayList<DettaglioDistributoreModel> listDettaglioDistributoreModel;
	private ArrayList<Prodotto> prodottiCompatibili; 

	// mi servono scaffale e posto, quantità , nome prodotto
	public String execute(){

		try{
			distributore = FactoryDao.getIstance().getDistributoreDao().get(idDistributore, Distributore.class);
			indirizzoDistributore = distributore.getIndirizzo();
			posizioneEdificioDistributore = distributore.getPosizioneEdificio();
			scaffali = distributore.getNumScaffali();
			posti = distributore.getNumPosti();
		} catch (Exception e){
			System.out.println("primo try " + e.getMessage());
			return ERROR;
		}
		try {
			prodottiCompatibili = FactoryDao.getIstance().getProdottoDao().getProdottiCompatibiliByDistributore(idDistributore);
		}catch (Exception e){
			System.out.println("prodottiCompatibili");
		}
		
		
		try{
			
			listProdottiErogati = FactoryDao.getIstance().getProdottiErogatiDao().getProdottiErogatiByDistributoreSortedByScaffalePosto(idDistributore);
			Iterator<ProdottiErogati> prodottiErogatiIterator = listProdottiErogati.iterator();
			listProdottiErogatixScaffale = new ArrayList<ArrayList<DettaglioDistributoreModel>>();
			Integer count=1;
			for (Integer j = 1; j <= scaffali; j++){	
				listDettaglioDistributoreModel = new ArrayList<DettaglioDistributoreModel>();
				
				for (Integer i = 1; i <= posti; i++){
					currentProdottiErogati = prodottiErogatiIterator.next();
					currentDettaglioDistributoreModel = new DettaglioDistributoreModel();
					currentDettaglioDistributoreModel.setIdProdottoErogato(currentProdottiErogati.getId());
					currentDettaglioDistributoreModel.setIdProdotto(currentProdottiErogati.getProdotto().getId());
					currentDettaglioDistributoreModel.setNomeProdottoErogato(currentProdottiErogati.getProdotto().getNome());
					currentDettaglioDistributoreModel.setQuantita(currentProdottiErogati.getQuantita());
					listDettaglioDistributoreModel.add(currentDettaglioDistributoreModel);
					count++;
				}
				
				listProdottiErogatixScaffale.add(listDettaglioDistributoreModel);
			}



		} catch (Exception e){
			System.out.println("secondo try " +e.getMessage());
			return ERROR;
		}

		return SUCCESS;


	}

	


	public ArrayList<Prodotto> getProdottiCompatibili() {
		return prodottiCompatibili;
	}




	public void setProdottiCompatibili(ArrayList<Prodotto> prodottiCompatibili) {
		this.prodottiCompatibili = prodottiCompatibili;
	}




	public String getIndirizzoDistributore() {
		return indirizzoDistributore;
	}




	public void setIndirizzoDistributore(String indirizzoDistributore) {
		this.indirizzoDistributore = indirizzoDistributore;
	}




	public String getPosizioneEdificioDistributore() {
		return posizioneEdificioDistributore;
	}




	public void setPosizioneEdificioDistributore(String posizioneEdificioDistributore) {
		this.posizioneEdificioDistributore = posizioneEdificioDistributore;
	}




	public ArrayList<DettaglioDistributoreModel> getListDettaglioDistributoreModel() {
		return listDettaglioDistributoreModel;
	}


	public void setListDettaglioDistributoreModel(ArrayList<DettaglioDistributoreModel> listDettaglioDistributoreModel) {
		this.listDettaglioDistributoreModel = listDettaglioDistributoreModel;
	}




	public ArrayList<ArrayList<DettaglioDistributoreModel>> getListProdottiErogatixScaffale() {
		return listProdottiErogatixScaffale;
	}


	public void setListProdottiErogatixScaffale(
			ArrayList<ArrayList<DettaglioDistributoreModel>> listProdottiErogatixScaffale) {
		this.listProdottiErogatixScaffale = listProdottiErogatixScaffale;
	}


	public Integer getIdDistributore() {
		return idDistributore;
	}
	public void setIdDistributore(Integer idDistributore) {
		this.idDistributore = idDistributore;
	}
	public Integer getScaffali() {
		return scaffali;
	}
	public void setScaffali(Integer scaffali) {
		this.scaffali = scaffali;
	}
	public Integer getPosti() {
		return posti;
	}
	public void setPosti(Integer posti) {
		this.posti = posti;
	}
	public ArrayList<ProdottiErogati> getListProdottiErogati() {
		return listProdottiErogati;
	}
	public void setListProdottiErogati(ArrayList<ProdottiErogati> listProdottiErogati) {
		this.listProdottiErogati = listProdottiErogati;
	}
	public ArrayList<String> getListNomiCategorieFornite() {
		return listNomiCategorieFornite;
	}
	public void setListNomiCategorieFornite(ArrayList<String> listNomiCategorieFornite) {
		this.listNomiCategorieFornite = listNomiCategorieFornite;
	}
}
