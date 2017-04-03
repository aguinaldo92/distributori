/**
 * 
 */
package it.unisalento.distributori.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import it.unisalento.distributori.domain.CategorieFornite;
import it.unisalento.distributori.domain.Distributore;
import it.unisalento.distributori.factory.FactoryDao;
import it.unisalento.distributori.model.DistributoreModel;
import it.unisalento.distributori.domain.Persona;
import it.unisalento.distributori.domain.ProdottiErogati;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author aguinaldo
 *
 */
public class ListDistributoriDipendente extends ActionSupport implements SessionAware{
	private static final long serialVersionUID = 9135964785938261849L;
	private List<Distributore> listDistributore ;
	// elenco delle possibili categorie che un distributore può fornire
	// (anche se non è detto che lo faccia)	
	private List<String> listNomiCategorieFornite;
	// elenco dei prodotti forniti da un solo distributore
	private ArrayList<ProdottiErogati> listProdottiErogati;
	private ArrayList<String> listNomiQuantitaProdottiErogati;
	// elenco dei distributori contentneti i prodotti in fine e le categorie che possono erogare
	private List<DistributoreModel> listDistributoreModel ;
	private DistributoreModel currentDistributoreModel;
	private SessionMap<String, Object> personaSession;
	private Distributore currentDistributore;
	private ProdottiErogati currentProdottoErogato;
	private Integer quantitaMinima = 5;
	
	

	public List<DistributoreModel> getListDistributoreModel() {
		return listDistributoreModel;
	}

	public void setListDistributoreModel(List<DistributoreModel> listDistributoreModel) {
		this.listDistributoreModel = listDistributoreModel;
	}

	public String execute() {
		System.out.println("ListDistributoriDipendente.action:");
		Integer idDipendente = ((Persona) personaSession.get("persona")).getId();
		listDistributoreModel = new ArrayList<DistributoreModel>();
		// TODO: quantità minima da settare diversamente


			try {
			
			listDistributore = FactoryDao.getIstance().getDistributoreDao().getDistributoriByIdDipendenteSortedByStato(idDipendente);
			} catch (Exception e) {
				//System.out.println("eccezione" + e.getLocalizedMessage());
				return ERROR;
			}
			// iteratore su tutti i distributori
			Iterator<Distributore> distributoriIterator = listDistributore.iterator();
			
			//System.out.println("numero di iterazioni da effettuare: "+ listDistributore.size());
			while (distributoriIterator.hasNext()) {
				// se non venisse ricreato ogni iterazione, verrebbe aggiunto più volte lo stesso model alla lista inserendo duplicati
				currentDistributoreModel  = new DistributoreModel();
				listNomiQuantitaProdottiErogati = new ArrayList<String>();
				//System.out.println("iterazione: " + count);
				//System.out.println("numero di iterazioni rimanenti: "+ (listDistributore.size() - count));
				currentDistributore =  distributoriIterator.next();
				//System.out.println("Ditributore: " + currentDistributore.getId() + " stato " + currentDistributore.getStato());
				
				try {
					listNomiCategorieFornite = FactoryDao.getIstance().getCategorieForniteDao().getNomiCategorieForniteByDistributore(currentDistributore.getId());
				} catch (Exception e) {
					//System.out.println("eccezione" + e.getLocalizedMessage());
					// TODO Auto-generated catch block
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
				currentDistributoreModel.setCategorieFornite((ArrayList<String>) listNomiCategorieFornite);
				currentDistributoreModel.setProdottiForniti((ArrayList<String>) listNomiQuantitaProdottiErogati);
				//System.out.println("popolato il DistributoreModel numero: " + count);
				listDistributoreModel.add(currentDistributoreModel);
				//System.out.println("aggiunto elemento alla lista dei models: " + currentDistributoreModel);
				
				}
			//System.out.println("la lista dei DistributoreModel contiene: " + listDistributoreModel.size() + " dopo "+ count +  " iterazioni ." );
			ServletActionContext.getRequest().setAttribute("listDistributoreModel", listDistributoreModel);
		
		return SUCCESS;

	}

	@Override
	public void setSession(Map<String, Object> map) {
		this.personaSession = (SessionMap<String, Object>) map;

	}
	

}
