/**
 * 
 */
package it.unisalento.distributori.action;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import it.unisalento.distributori.domain.Dipendente;
import it.unisalento.distributori.domain.Distributore;
import it.unisalento.distributori.factory.FactoryDao;
import it.unisalento.distributori.model.DistributoreModel;
import it.unisalento.distributori.util.AddressTranslation;

/**
 * @author aguinaldo
 */
public class UpdateDistributore extends ActionSupport {
	private static final long serialVersionUID = 6133180504241137029L;
	private Integer idDistributore;
	private DistributoreModel distributoreModel = new DistributoreModel();
	Logger logger = LogManager.getLogger(this.getClass().getName());

	public String execute() {
		try {
			logger.debug("execute()");
			Distributore distributore = FactoryDao.getIstance().getDistributoreDao().get(idDistributore, Distributore.class);
			String indirizzo = distributoreModel.getVia() + ", " + distributoreModel.getCivico() + ", " + distributoreModel.getCitta() + ", " + distributoreModel.getProvincia();
			distributore.setIndirizzo(indirizzo);
			distributore.setLat(AddressTranslation.getLatLonFromAddress(distributore.getIndirizzo()).get(0));
			distributore.setLon(AddressTranslation.getLatLonFromAddress(distributore.getIndirizzo()).get(1));
			distributore.setDipendente(FactoryDao.getIstance().getDipendenteDao().get(distributoreModel.getIdDipendente(), Dipendente.class));
			distributore.setPosizioneEdificio(distributoreModel.getPosizioneEdificio());
			//distributore.setStato(distributoreModel.getStato());
			FactoryDao.getIstance().getDistributoreDao().update(distributore);

		} catch (Exception e) {
			logger.error("Distributore non aggiornato a causa di un errore",e);
			addActionError("Distributore non aggiornato a causa di un errore");
		}
		return SUCCESS;
	}
	
	
	public void validate(){
		logger.debug("validate()");
		ServletActionContext.getRequest().setAttribute("distributoreModel", distributoreModel);
		if (hasFieldErrors()){
			addActionError("Ci sono degli errori nell'aggiornamento delle info");
		}
	}
	
	public Integer getIdDistributore() {
		return idDistributore;
	}

	public void setIdDistributore(Integer idDistributore) {
		this.idDistributore = idDistributore;
	}
	public DistributoreModel getDistributoreModel() {
		return distributoreModel;
	}
	public void setDistributoreModel(DistributoreModel distributoreModel) {
		this.distributoreModel = distributoreModel;
	}








	
	
	



	


}
