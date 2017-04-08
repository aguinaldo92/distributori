/**
 * 
 */
package it.unisalento.distributori.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.dispatcher.SessionMap;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author aguinaldo
 *
 */
public class Logout extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5590904666971082265L;
	private SessionMap<String, Object> personaSession ;
	private Logger logger = LogManager.getLogger(this.getClass().getName());

	@Override
	public String execute(){
		try{
			logger.debug("execute()");
			personaSession=(SessionMap<String,Object>)ActionContext.getContext().getSession();
			personaSession.invalidate();
			return SUCCESS;
		} catch (Exception e){
			logger.error("Impossibile efettuare il logout ed invalidare la sessione dell'utente: ",e);
			addActionError("Impossibile effettuare il logout, riprovare più tardi");
			return ERROR;
		}
	}

}


