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
	private SessionMap<String, Object> personaSession ;

	private String email;
	private String password;

	public String execute() {

		System.out.println("Sono entrato nella action Login");
		personaSession.put("persona", persona);
		System.out.println("email: " + persona.getEmail());

		System.out.println("ruolo: " + persona.getRuolo());
		System.out.println("success final");
		return SUCCESS;
	}
	
	public void validate() {
		boolean errors = false;

		if (!(personaSession.containsKey("persona"))){//controllo se la userSession è stata impostata
			try {	 
				//ottengo anagrafica e indirizzi salvati dallo user loggato
				persona=FactoryDao.getIstance().getPersonaDao().getPersonaByCredentials(email, password);
				
				if (persona == null ){
					errors = true;
					addFieldError("email", "Utente non presente nel sistema");
				}

			} catch (Exception e){
				System.out.println(e.getLocalizedMessage());
				errors = true;
			}
		}
		if (errors) {
			addActionError("Email o Password errati");
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

/*

public class Login extends ActionSupport implements SessionAware, ParameterNameAware  {

	private static final long serialVersionUID = 1L;
	private User user= new User();
	private SessionMap<String, Object> userSession ;

	private String username;
	private String password;

	public String execute() {

		System.out.println("Sono entrato nella action Login");
		userSession.put("user", user);
		System.out.println("username: "+user.getUsername());

		System.out.println("role: "+user.getRole());
		System.out.println("success final");
		return SUCCESS;
	}

	public void validate() {
		boolean errors = false;

		if (!(userSession.containsKey("user"))){//controllo se la userSession è stata impostata
			try {	 
				//ottengo anagrafica e indirizzi salvati dallo user loggato
				user=FactoryDao.getIstance().getUserDao().getUserByCredentials(username, password);
				
				if (user == null ){
					errors = true;
					addFieldError("username", "Utente non presente nel sistema");
				}

			} catch (Exception e){
				System.out.println(e.getLocalizedMessage());
				errors = true;
			}
		}
		if (errors) {
			addActionError("Username o Password errati");
		}
	}
	
	@Override
	public void setSession(Map<String,Object> map) {  		 
		this.userSession = (SessionMap<String,Object>)map;

	}  

	@Override
	public boolean acceptableParameterName(String parameterName) {

		boolean allowedParameterName = true ;

		if ( parameterName.contains("session")  || parameterName.contains("request") ) {

			allowedParameterName = false ;

		} 

		return allowedParameterName;
	}

	public String getUsername() {
		return username;
	}
	@RequiredStringValidator(message = "Username richiesto")
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	@RequiredStringValidator(message = "Password richiesta")
	public void setPassword(String password) {
		this.password = password;
	}


}
*/