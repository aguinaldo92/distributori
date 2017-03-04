package it.unisalento.distributori.action;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import it.unisalento.distributori.domain.Persona;
import it.unisalento.distributori.factory.FactoryDao;

public class UpdatePassword extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6555023403138012964L;
	private String confirmPassword, newPassword;
	private SessionMap<String, Object> personaSession;
	private Persona persona;



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

	public void validate() {
		persona = (Persona) personaSession.get("persona");
		boolean errors = false;
		System.out.println("UpdatePassword: validate()");
		if (!newPassword.equals(confirmPassword)){
			System.out.println("UpdatePassword: password inserite diverse");
			addFieldError("confirmPassword", "Le password non combaciano");
			errors = true;
		}
		if(newPassword.equals(persona.getPassword())){
			System.out.println("UpdatePassword: password non cambiata");
			addFieldError("password", "La password scelta è la stessa già utilizzata, sceglierne una differente");
			errors = true;
		}
		System.out.println("UpdatePassword: fine validate");
		if (errors) {
			System.out.println("UpdatePassword: presenti alcuni errori");
			addActionError("Sono presenti errori all'interno del form");
		}
	}

	public String execute(){
		persona.setPassword(newPassword);
		try{
			FactoryDao.getIstance().getPersonaDao().update(persona);
			personaSession.replace("persona",persona);
		}catch (Exception e){
			System.out.println("Impossibile salvare la password nel DB: " + e.getLocalizedMessage());
		}

		System.out.println("PAssword Modificata");
		return SUCCESS;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		this.personaSession = (SessionMap<String, Object>) map;		
	}

}
