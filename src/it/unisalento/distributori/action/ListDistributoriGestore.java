package it.unisalento.distributori.action;

import java.util.ArrayList;
import java.util.Iterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import it.unisalento.distributori.domain.Distributore;
import it.unisalento.distributori.domain.ProdottiErogati;
import it.unisalento.distributori.factory.FactoryDao;
import it.unisalento.distributori.model.DistributoreModel;

public class ListDistributoriGestore extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6667941537054693949L;
	private ArrayList<Distributore> listDistributore ;
	// elenco delle possibili categorie che un distributore può fornire
	// (anche se non è detto che lo faccia)	
	private ArrayList<String> listNomiCategorieFornite;
	// elenco dei prodotti forniti da un solo distributore
	private ArrayList<ProdottiErogati> listProdottiErogati;
	private ArrayList<String> listNomiQuantitaProdottiErogati;
	// elenco dei distributori contenenti i prodotti in fine e le categorie che possono erogare
	private ArrayList<DistributoreModel> listDistributoreModel = new ArrayList<DistributoreModel>();
	private Logger logger = LogManager.getLogger(this.getClass().getName());

	public String execute() {
		logger.debug("execute()");

		try {
			listDistributore = (ArrayList<Distributore>) FactoryDao.getIstance().getDistributoreDao().getAllSortedBy(Distributore.class, "stato");
			// iteratore su tutti i distributori
			for (Iterator<Distributore> distributoriIterator = listDistributore.iterator(); distributoriIterator.hasNext();) {
				Distributore currentDistributore =  distributoriIterator.next();
				// se non venisse ricreato ogni iterazione, verrebbe aggiunto più volte lo stesso model alla lista inserendo duplicati
				DistributoreModel currentDistributoreModel  = new DistributoreModel();
				currentDistributoreModel.setId(currentDistributore.getId());
				currentDistributoreModel.setIndirizzo(currentDistributore.getIndirizzo());
				currentDistributoreModel.setStato(currentDistributore.getStato());
				currentDistributoreModel.setPosizioneEdificio(currentDistributore.getPosizioneEdificio());
				currentDistributoreModel.setDipendente(currentDistributore.getDipendente().getPersona());
				listNomiCategorieFornite = (ArrayList<String>) FactoryDao.getIstance().getCategorieForniteDao().getNomiCategorieForniteByDistributore(currentDistributore.getId());
				currentDistributoreModel.setCategorieFornite(listNomiCategorieFornite);

				listProdottiErogati = FactoryDao.getIstance().getProdottiErogatiDao().getProdottiScarseggiantiByDistributore(currentDistributore.getId());
				listNomiQuantitaProdottiErogati = new ArrayList<String>();
				// iteratore su tutti i prodotti erogati da ogni distributore
				for (Iterator<ProdottiErogati> prodottiErogatiIterator = listProdottiErogati.iterator(); prodottiErogatiIterator.hasNext();) {
					ProdottiErogati currentProdottoErogato = prodottiErogatiIterator.next();
					listNomiQuantitaProdottiErogati.add(currentProdottoErogato.getProdotto().getNome() + ": " + currentProdottoErogato.getQuantita());
				}
				currentDistributoreModel.setProdottiForniti(listNomiQuantitaProdottiErogati);
				listDistributoreModel.add(currentDistributoreModel);
			}
			return SUCCESS;
			
		} catch (Exception e){
			logger.error("Impossibile caricare l'elenco dei Distributori da parte del gestore",e);
			addActionError("Impossibile caricare l'elenco dei Distributori");
			return ERROR;
		}

	}


	public ArrayList<DistributoreModel> getListDistributoreModel() {
		return listDistributoreModel;
	}
	public void setListDistributoreModel(ArrayList<DistributoreModel> listDistributoreModel) {
		this.listDistributoreModel = listDistributoreModel;
	}

}
