package it.unisalento.distributori.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import com.opensymphony.xwork2.ActionSupport;

import it.unisalento.distributori.domain.Distributore;
import it.unisalento.distributori.domain.ProdottiErogati;
import it.unisalento.distributori.factory.FactoryDao;
import it.unisalento.distributori.model.DistributoreModel;

public class ListDistributoriGestore extends ActionSupport{

	private static final long serialVersionUID = -746929828565605153L;
	private List<Distributore> listDistributore ;
	// elenco delle possibili categorie che un distributore può fornire
	// (anche se non è detto che lo faccia)	
	private ArrayList<String> listNomiCategorieFornite;
	// elenco dei prodotti forniti da un solo distributore
	private ArrayList<ProdottiErogati> listProdottiErogati;
	private ArrayList<String> listNomiQuantitaProdottiErogati;
	// elenco dei distributori contentneti i prodotti in fine e le categorie che possono erogare
	private List<DistributoreModel> listDistributoreModel ;
	private DistributoreModel currentDistributoreModel;
	private Distributore currentDistributore;
	private ProdottiErogati currentProdottoErogato;

	
	public List<DistributoreModel> getListDistributoreModel() {
		return listDistributoreModel;
	}

	public void setListDistributoreModel(List<DistributoreModel> listDistributoreModel) {
		this.listDistributoreModel = listDistributoreModel;
	}
	
	public String execute() {
		System.out.println("ListDistributoriDipendente.action:");
		
		listDistributoreModel = new ArrayList<DistributoreModel>();
		// TODO: quantità minima da settare diversamente (così mostriamo tutti i prodotti erogati diversi da vuoto)
		Integer quantitaMinima = 10100;

			try {
			listDistributore = FactoryDao.getIstance().getDistributoreDao().getAll(Distributore.class);
			} catch (Exception e) {
				System.out.println("eccezione" + e.getLocalizedMessage());
				return ERROR;
			}
			// iteratore su tutti i distributori
			Iterator<Distributore> distributoriIterator = listDistributore.iterator();
			
			System.out.println("numero di iterazioni da effettuare: "+ listDistributore.size());
			while (distributoriIterator.hasNext()) {
				// se non venisse ricreato ogni iterazione, verrebbe aggiunto più volte lo stesso model alla lista inserendo duplicati
				listNomiQuantitaProdottiErogati = new ArrayList<String>();
				currentDistributoreModel  = new DistributoreModel();
				//System.out.println("iterazione: " + count);
				//System.out.println("numero di iterazioni rimanenti: "+ (listDistributore.size() - count));
				currentDistributore =  distributoriIterator.next();
				//System.out.println("Ditributore: " + currentDistributore.getId() + " stato " + currentDistributore.getStato());
				
				try {
					listNomiCategorieFornite = FactoryDao.getIstance().getCategorieForniteDao().GetNomiCategorieForniteByDistributore(currentDistributore.getId());
				} catch (Exception e) {
					System.out.println("eccezione" + e.getLocalizedMessage());
					
					e.printStackTrace();
				}
				
				//System.out.println("Categorie fornite dal Distributore: " + listNomiCategorieFornite.size());
				
				listProdottiErogati = FactoryDao.getIstance().getProdottiErogatiDao().getProdottiScarseggiantiByDistributore(currentDistributore.getId(), quantitaMinima);
				
				Iterator<ProdottiErogati> prodottiErogatiIterator = listProdottiErogati.iterator();
				//System.out.println("Inizio iterator su prodotti");
				while (prodottiErogatiIterator.hasNext()) {
					currentProdottoErogato = prodottiErogatiIterator.next();
					
					try {
						listNomiQuantitaProdottiErogati.add(currentProdottoErogato.getProdotto().getNome() + ": " + currentProdottoErogato.getQuantita());
					} catch (Exception e) {
						//System.out.println("eccezione in while prodotti erogoati iterator" + e.getLocalizedMessage());
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//System.out.println("Aggiunto : " + listNomiQuantitaProdottiErogati.get((listNomiQuantitaProdottiErogati.size() -1)));
					
				}
				
				//System.out.println("prodotti erogati dal Distributore: " + listNomiQuantitaProdottiErogati.size());			
				currentDistributoreModel.setId(currentDistributore.getId());
				currentDistributoreModel.setIndirizzo(currentDistributore.getIndirizzo());
				currentDistributoreModel.setStato(currentDistributore.getStato());
				currentDistributoreModel.setPosizioneEdificio(currentDistributore.getPosizioneEdificio());
				currentDistributoreModel.setDipendente(currentDistributore.getDipendente().getPersona());
				currentDistributoreModel.setCategorieFornite((ArrayList<String>) listNomiCategorieFornite);
				currentDistributoreModel.setProdottiForniti((ArrayList<String>) listNomiQuantitaProdottiErogati);
				//System.out.println("popolato il DistributoreModel numero: " + count);
				listDistributoreModel.add(currentDistributoreModel);
				System.out.println("aggiunto elemento alla lista dei models: " + currentDistributoreModel);
				
				}
			
		
		return SUCCESS;

	}
}
