package it.unisalento.distributori.interceptor;

import it.unisalento.distributori.domain.Persona;

import org.apache.struts2.dispatcher.SessionMap;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoginInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SessionMap<String, Object> personaSession;
	private  int role;
	private Persona persona;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {

	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		personaSession = (SessionMap<String, Object>)invocation.getInvocationContext().getSession();	
			if (personaSession.containsKey("persona")) {
				persona = (Persona) personaSession.get("persona");
				role = persona.getRuolo();
				if (role < 2) {
					System.out.println("permessi ok");
					return invocation.invoke();
				}
				else {
					System.out.println("permessi Insufficieneti ");
					return "DENIED";
				}
			} else {
				System.out.println("L'utente deve essere loggato per arrivare alla pagina ");
				return Action.LOGIN;
			}



		}

	}
