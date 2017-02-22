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
	private List<CategorieFornite> listCategorieFornite;
	// elenco dei prodotti forniti da un solo distributore
	private List<ProdottiErogati> listProdottiErogati ;
	// elenco dei distributori contentneti i prodotti in fine e le categorie che possono erogare
	private List<DistributoreModel> listDistributoreModel ;
	private DistributoreModel currentDistributoreModel;
	private SessionMap<String, Object> personaSession;
	private Distributore currentDistributore;
	
	

	public List<DistributoreModel> getListDistributoreModel() {
		return listDistributoreModel;
	}

	public void setListDistributoreModel(List<DistributoreModel> listDistributoreModel) {
		this.listDistributoreModel = listDistributoreModel;
	}

	public String execute() {
		Integer idDipendente = ((Persona) personaSession.get("persona")).getId();
		currentDistributoreModel  = new DistributoreModel();
		listDistributoreModel = new ArrayList<DistributoreModel>();
		// TODO: quantità minima da settare diversamente
		Integer quantitaMinima = 10100;

			try {
			listDistributore = FactoryDao.getIstance().getDistributoreDao().getDistributoriByIdDipendenteSortedByStato(idDipendente);
			
			// iteratore su tutti i distributori
			Iterator<Distributore> distributoriIterator = listDistributore.iterator();
			Integer count=1;
			System.out.println("numero di iterazioni da effettuare: "+ listDistributore.size());
			while (distributoriIterator.hasNext()) {
				System.out.println("iterazione: " + count);
				System.out.println("numero di iterazioni rimanenti: "+ (listDistributore.size() - count));
				currentDistributore =  distributoriIterator.next();
				System.out.println("Ditributore: " + currentDistributore.getId() + " stato " + currentDistributore.getStato());
				listCategorieFornite = FactoryDao.getIstance().getCategorieForniteDao().GetCategorieForniteByDistributore(currentDistributore.getId());
				System.out.println("Categorie fornite dal Distributore: " + listCategorieFornite.size());
				listProdottiErogati = FactoryDao.getIstance().getProdottiErogatiDao().GetProdottiScarseggiantiByDistributore(currentDistributore.getId(), quantitaMinima);
				System.out.println("prodotti erogati dal Distributore: " + listProdottiErogati.size());			
				currentDistributoreModel.setId(currentDistributore.getId());
				currentDistributoreModel.setIndirizzo(currentDistributore.getIndirizzo());
				currentDistributoreModel.setStato(currentDistributore.getStato());
				currentDistributoreModel.setPosizioneEdificio(currentDistributore.getPosizioneEdificio());
				currentDistributoreModel.setCategorieFornite((ArrayList<CategorieFornite>) listCategorieFornite);
				currentDistributoreModel.setProdottiForniti((ArrayList<ProdottiErogati>) listProdottiErogati);
				System.out.println("popolato il DistributoreModel numero: " + count);
				listDistributoreModel.add(currentDistributoreModel);
				System.out.println("aggiunto elemento alla lista dei models: " + currentDistributoreModel);
				
				}
			System.out.println("la lista dei DistributoreModel contiene: " + listDistributoreModel.size() + " dopo "+ count +  " iterazioni ." );
			ServletActionContext.getRequest().setAttribute("listDistributoreModel", listDistributoreModel);
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
				return ERROR;
			}
		return SUCCESS;

	}

	@Override
	public void setSession(Map<String, Object> map) {
		this.personaSession = (SessionMap<String, Object>) map;

	}



//	@Override
//	public Object getModel() {
//		// TODO Auto-generated method stub
//		if (listDistributoreModel.size() != 0) {
//			System.out.println("get model listDistributoreModel.size(): " + listDistributoreModel.size());
//		return listDistributoreModel;
//		}
//		else {
//			System.out.println("get model else: ");
//			return null;
//		}
//	}

//	@Override
//	public void prepare() throws Exception {
//
//
//
//	
//	}
}
