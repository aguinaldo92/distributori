package it.unisalento.distributori.action;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import it.unisalento.distributori.domain.Persona;
import it.unisalento.distributori.factory.FactoryDao;
import it.unisalento.distributori.util.PasswordUtils;

public class UpdatePassword extends ActionSupport implements SessionAware{
	private static final long serialVersionUID = 6731743316457056842L;
	private String confirmPassword, newPassword;
	private SessionMap<String, Object> personaSession;
	private Persona persona;
	private Logger logger = LogManager.getLogger(this.getClass().getName());


	public void validate() {
		try{
			logger.debug("validate()");
			persona = (Persona) personaSession.get("persona");
			if (!newPassword.equals(confirmPassword)){
				addFieldError("confirmPassword", "Le password non combaciano");
			}
			if(newPassword.equals(PasswordUtils.getSha256(persona.getPassword()))){
				addFieldError("newPassword", "La password scelta è la stessa già utilizzata, sceglierne una differente");
			}
			if (hasFieldErrors()) {
				addActionError("Sono presenti errori all'interno del form");
			}
		} catch (Exception e){
			logger.error("Impossibile completare la validazione della password, sospetto errore nell'ottenere la persona dalla sessione",e);
			addActionError("Impossibile caricare l'elenco dei Distributori");
		}
	}


	public String execute(){
		try{
			logger.debug("execute()");
			persona.setPassword(PasswordUtils.getSha256(newPassword));
			FactoryDao.getIstance().getPersonaDao().update(persona);
			personaSession.replace("persona",persona);
			addActionMessage("Password modificata con successo");
			return SUCCESS;

		} catch (Exception e){
			logger.error("Impossibile salvare la password dell'utente con id: "+ persona.getId(),e);
			addActionError("Impossibile salvare la nuova password");
			return ERROR;
		}
	}

	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}


	public void setSession(Map<String, Object> map) {
		this.personaSession = (SessionMap<String, Object>) map;	
	}
}
