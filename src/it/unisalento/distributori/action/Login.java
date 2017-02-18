/**
 * 
 */
package it.unisalento.distributori.action;

import it.unisalento.distributori.domain.Persona;
import it.unisalento.distributori.factory.FactoryDao;
import it.unisalento.distributori.model.PersonaModel;

import java.util.Map;

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

	private static final long serialVersionUID = 1L;
	private Persona persona= new Persona();
	private Persona personaBySession;
	private SessionMap<String, Object> personaSession ;

	private String email;
	private String password;

	public String execute() {
		
		System.out.println("Sono entrato nel metodo execute della action Login");
		
		personaBySession = (Persona) personaSession.get("persona");
		System.out.println("email: " + persona.getEmail());
		System.out.println("email già salvata: " + personaBySession.getEmail());
		System.out.println("ruolo: " + persona.getRuolo());
		System.out.println("ruolo già salvato: " + personaBySession.getRuolo());
		
		switch (personaBySession.getRuolo()) {
			case 0:	return "gestore";
			case 1: return "dipendente";
			default: return SUCCESS;
		} 
		
	}
	
	public void validate() {
		boolean errors = false;
		System.out.println("Sono entrato nel metodo validate della Login");
		if (!(personaSession.containsKey("persona"))){
			System.out.println("sono nell if persona: nessuna persona in personaSession");
			//controllo se la userSession è stata impostata
			try {	 
				//ottengo anagrafica e indirizzi salvati dallo user loggato
				persona=FactoryDao.getIstance().getPersonaDao().getPersonaByCredentials(email, password);
				
				if (persona == null ){
					errors = true;
					addFieldError("email", "Utente non presente nel sistema");
					System.out.println("email non presente: " + persona.getEmail());
				} else {
					personaSession.put("persona", persona);
					System.out.println("salvo persona nella sessione " + persona.getEmail());
				}
				
			} catch (Exception e){
				System.out.println(e.getLocalizedMessage());
				errors = true;
			}
		} 		
		if (errors) {
			addActionError("Email o Password errati");
			System.out.println("aggiungo errori");
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
