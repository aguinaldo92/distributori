package it.unisalento.distributori.action;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import it.unisalento.distributori.domain.Dipendente;
import it.unisalento.distributori.domain.Persona;
import it.unisalento.distributori.factory.FactoryDao;
import it.unisalento.distributori.model.PersonaModel;

public class DipendenteDetail extends ActionSupport implements SessionAware{
	private static final long serialVersionUID = -7072020695958765813L;
	private int idDip;
	private PersonaModel dipendente = new PersonaModel();
	private SessionMap<String, Object> personaSession ;
	private Dipendente dip;
	private Logger logger = LogManager.getLogger(this.getClass().getName());


	public String execute () {
		try{
			logger.debug("execute()");
			if (((Persona)personaSession.get("persona")).getRuolo() != 0 ){
				// l'amministratore può visualizzare tutti i dipendenti
				this.idDip=((Persona)personaSession.get("persona")).getId();
			} 
			dip = FactoryDao.getIstance().getDipendenteDao().get(idDip, Dipendente.class);

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

	@Override
	public void setSession(Map<String, Object> map) {
		this.personaSession = (SessionMap<String,Object>) map;
		
	}
}