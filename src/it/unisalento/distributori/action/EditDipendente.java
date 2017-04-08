package it.unisalento.distributori.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import it.unisalento.distributori.domain.Dipendente;
import it.unisalento.distributori.factory.FactoryDao;
import it.unisalento.distributori.model.PersonaModel;

public class EditDipendente extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6387264238919678425L;
	private int idDip;
	private Dipendente dip;
	private PersonaModel dipendente = new PersonaModel();
	private Logger logger = LogManager.getLogger(this.getClass().getName());


	public String execute ()  {
		try{
			logger.debug("execute()");
			dip=FactoryDao.getIstance().getDipendenteDao().get(idDip, Dipendente.class);

			dipendente.setCognome(dip.getPersona().getCognome());
			dipendente.setNome(dip.getPersona().getNome());
			dipendente.setEmail(dip.getPersona().getEmail());
			dipendente.setId(dip.getPersonaId());
			dipendente.setTelefono(dip.getTelefono());
			logger.debug("Dipendente ottenuto: "+dipendente.getNome()+" "+dipendente.getCognome());

			return SUCCESS;

		}catch (NullPointerException ne){
			logger.error("Impossibile visualizzare il profilo del dipendente scelto",ne);
			addActionError("Impossibile caricare i dati del dipendente scelto");
			return ERROR;
		} catch (Exception e){
			logger.error("Impossibile visualizzare il profilo del dipendente con ID: "+dip.getPersonaId(),e);
			addActionError("Impossibile caricare i dati del del dipendente avente numero di BADGE: "+dip.getPersonaId());
			return ERROR;
		}
	}

	public PersonaModel getDipendente() {
		return dipendente;
	}
	public void setDipendente(PersonaModel dipendente) {
		this.dipendente = dipendente;
	}
	public int getIdDip() {
		return idDip;
	}
	public void setIdDip(int idDip) {
		this.idDip = idDip;
	}
}