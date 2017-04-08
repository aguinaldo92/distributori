/**
 * 
 */
package it.unisalento.distributori.action;

import it.unisalento.distributori.domain.Persona;
import it.unisalento.distributori.factory.FactoryDao;
import it.unisalento.distributori.model.PersonaModel;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.ParameterNameAware;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;


/**
 * @author aguinaldo
 *
 */
public class Login extends ActionSupport implements SessionAware, ParameterNameAware {

	private SessionMap<String, Object> personaSession ;
	private String email;
	private String password;
	private Logger logger = LogManager.getLogger(this.getClass().getName());

	public String execute() {

		logger.trace("execute()");
		Persona personaBySession = (Persona) personaSession.get("persona");
		logger.debug("personaBySession.getNome(): "+personaBySession.getNome());
		switch (personaBySession.getRuolo()) {
		case 0:	return "gestore";
		case 1: return "dipendente";

		} 
		return SUCCESS;
	}

	public void validate() {
		try {
			logger.trace("validate()");
			boolean errors = false;
			System.out.println("Sono entrato nel metodo validate della Login");
			if (!(personaSession.containsKey("persona"))){
				logger.trace("sono nell if persona: nessuna persona in personaSession");
				//controllo se la userSession è stata impostata

				//ottengo anagrafica e indirizzi salvati dallo user loggato
				Persona persona = FactoryDao.getIstance().getPersonaDao().getPersonaByCredentials(email, password);

				if (persona != null ){
					personaSession.put("persona", persona);
					logger.trace("salvo persona nella sessione " + persona.getEmail());
				} else {
					errors = true;
					addFieldError("email", "Utente non presente nel sistema");
					logger.trace("email non presente: " + persona.getEmail());

				}
				if (errors) {
					addActionError("Email o Password errati");
					logger.trace("aggiungo errori");
				}

			} 		

		} catch (Exception e){
			logger.error("Eccezione non prevista nella validate del login",e);
			addActionError("Eccezione non prevista nella fase di login.");
		}
	}

	public String getEmail() {
		return email;
	}
	@RequiredStringValidator(message = "Username richiesto")
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	@RequiredStringValidator(message = "Password richiesta")
	public void setPassword(String password) {
		this.password = password;
	}



	@Override
	public boolean acceptableParameterName(String parameterName) {
		boolean allowedParameterName = true ;

		if ( parameterName.contains("session")  || parameterName.contains("request") ) {

			allowedParameterName = false ;

		} 

		return allowedParameterName;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		this.personaSession = (SessionMap<String,Object>)map;		
	}

}
