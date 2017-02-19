/**
 * 
 */
package it.unisalento.distributori.action;

import org.apache.struts2.dispatcher.SessionMap;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author aguinaldo
 *
 */
public class Logout extends ActionSupport {
	private SessionMap<String, Object> personaSession ;

	@Override
	public String execute(){

		personaSession=(SessionMap<String,Object>)ActionContext.getContext().getSession();

		personaSession.invalidate();

		System.out.println("sessione invalidata");
		return SUCCESS;
	}

}


