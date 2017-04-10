/**
 * 
 */
package it.unisalento.distributori.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;
import it.unisalento.distributori.domain.Distributore;
import it.unisalento.distributori.domain.Persona;
import it.unisalento.distributori.domain.ProdottiErogati;
import it.unisalento.distributori.factory.FactoryDao;
import it.unisalento.distributori.model.DistributoreModel;

/**
 * @author aguinaldo
 *
 */
public class ListDistributoriDipendente extends ActionSupport implements SessionAware{
	private static final long serialVersionUID = 4059544076845030328L;
	private List<DistributoreModel> listDistributoreModel = new ArrayList<DistributoreModel>() ;
	private SessionMap<String, Object> personaSession;
	private Logger logger = LogManager.getLogger(this.getClass().getName());

	public String execute() {
		try {
			logger.debug("execute()");
			Integer idDipendente = ((Persona) personaSession.get("persona")).getId();
			ArrayList<Distributore> listDistributore = FactoryDao.getIstance().getDistributoreDao().getDistributoriByIdDipendenteSortedByStato(idDipendente);
			// iteratore su tutti i distributori
			for (Iterator<Distributore> distributoriIterator = listDistributore.iterator(); distributoriIterator.hasNext();) {
				Distributore currentDistributore =  distributoriIterator.next();
				ArrayList<String> listNomiCategorieFornite = (ArrayList<String>) FactoryDao.getIstance().getCategorieForniteDao().getNomiCategorieForniteByDistributore(currentDistributore.getId());
				ArrayList<ProdottiErogati>  listProdottiErogati = FactoryDao.getIstance().getProdottiErogatiDao().getProdottiScarseggiantiByDistributore(currentDistributore.getId());
				ArrayList<String> listNomiQuantitaProdottiErogati = new ArrayList<String>();
				for (Iterator<ProdottiErogati> prodottiErogatiIterator = listProdottiErogati.iterator(); prodottiErogatiIterator.hasNext();) {
					ProdottiErogati currentProdottoErogato = prodottiErogatiIterator.next();
					listNomiQuantitaProdottiErogati.add(currentProdottoErogato.getProdotto().getNome() + ": " + currentProdottoErogato.getQuantita());
				}
				DistributoreModel currentDistributoreModel  = new DistributoreModel();
				currentDistributoreModel.setId(currentDistributore.getId());
				currentDistributoreModel.setIndirizzo(currentDistributore.getIndirizzo());
				currentDistributoreModel.setStato(currentDistributore.getStato());
				currentDistributoreModel.setPosizioneEdificio(currentDistributore.getPosizioneEdificio());
				currentDistributoreModel.setCategorieFornite(listNomiCategorieFornite);
				currentDistributoreModel.setProdottiForniti(listNomiQuantitaProdottiErogati);
				listDistributoreModel.add(currentDistributoreModel);
			}
			ServletActionContext.getRequest().setAttribute("listDistributoreModel", listDistributoreModel);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("Impossibile visualizzare elenco dei distributori di un dipendente con prodotti scarseggianti",e);
			return ERROR;
		}
	}

	@Override
	public void setSession(Map<String, Object> map) {
		this.personaSession = (SessionMap<String, Object>) map;
	}
	public List<DistributoreModel> getListDistributoreModel() {
		return listDistributoreModel;
	}
	public void setListDistributoreModel(List<DistributoreModel> listDistributoreModel) {
		this.listDistributoreModel = listDistributoreModel;
	}

}
