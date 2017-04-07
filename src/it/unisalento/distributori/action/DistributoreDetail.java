/**
 * 
 */
package it.unisalento.distributori.action;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import it.unisalento.distributori.domain.Distributore;
import it.unisalento.distributori.factory.FactoryDao;
import it.unisalento.distributori.model.DistributoreModel;

/**
 * @author aguinaldo
 */
public class DistributoreDetail extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -911336399712351020L;
	private Integer idDistributore;
	private DistributoreModel distributoreModel = new DistributoreModel();
	private Logger logger = LogManager.getLogger(this.getClass().getName());
	
	public String execute() {

		try {
			logger.trace("execute()");
			Distributore distributore = FactoryDao.getIstance().getDistributoreDao().get(idDistributore, Distributore.class);
			String[] st = distributore.getIndirizzo().split(",");
			
			distributoreModel.setIdDipendente(distributore.getDipendente().getPersonaId());
			distributoreModel.setProvincia(st[3].trim());
			distributoreModel.setPosizioneEdificio(distributore.getPosizioneEdificio());
			distributoreModel.setNumPosti(distributore.getNumPosti().toString());
			distributoreModel.setNumScaffali(distributore.getNumScaffali().toString());
			distributoreModel.setStato(distributore.getStato());
			distributoreModel.setCitta(st[2].trim());
			distributoreModel.setVia(st[0].trim());
			distributoreModel.setLat(distributore.getLat());
			distributoreModel.setLon(distributore.getLon());
			distributoreModel.setCivico(st[1]);
			distributoreModel.setCategorieFornite((ArrayList<Integer>) FactoryDao.getIstance().getCategorieForniteDao().getIdsCategorieForniteByDistributore(idDistributore));
			return SUCCESS;
		} catch (Exception e) {
			logger.error("Impossibile caricare DistributoreModel (e il form) con i dati presenti nel database", e);
			addActionError("Impossibile caricare i dati del distributore selezionato");
			return ERROR;
		}
	}
	

	public Integer getIdDistributore() {
		return idDistributore;
	}
	public void setIdDistributore(Integer idDistributore) {
		this.idDistributore = idDistributore;
	}
	public void setDistributoreModel(DistributoreModel distributoreModel) {
		this.distributoreModel = distributoreModel;
	}
	public DistributoreModel getDistributoreModel() {
		return distributoreModel;
	}
	
	






}
