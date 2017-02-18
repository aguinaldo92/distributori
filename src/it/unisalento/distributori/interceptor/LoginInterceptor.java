package it.unisalento.distributori.interceptor;

import it.unisalento.distributori.domain.Persona;
import it.unisalento.distributori.utils.PermissionsHashMap;

import java.util.HashMap;

import org.apache.struts2.dispatcher.SessionMap;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import freemarker.core.Environment.Namespace;

public class LoginInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SessionMap<String, Object> personaSession;
	private  Integer ruolo;
	private Persona persona;
	private String namespaceAction;
	private String nameAction;
	private PermissionsHashMap permissionsHashMap;
	private HashMap<Integer, String> ruolo_namespace;
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
		namespaceAction = invocation.getProxy().getNamespace();
		nameAction = invocation.getProxy().getActionName();
		System.out.println(namespaceAction);
		System.out.println(nameAction);
		
		ruolo_namespace = permissionsHashMap.getRuolo_namespace();
		
			if (personaSession.containsKey("persona")) {
				persona = (Persona) personaSession.get("persona");
				ruolo = persona.getRuolo();
				if (ruolo_namespace.get(ruolo).equals(namespaceAction)) {
					System.out.println("permessi ok");
					System.out.println("sei nel namespace: " + namespaceAction + " action:  " + nameAction + " poichè hai il ruolo " + ruolo);
					return invocation.invoke();
				}
				else {
					System.out.println("permessi Insufficieneti ");
					System.out.println("hai provato ad entrare nel namespace " + namespaceAction + " action:  " + nameAction +  " poichè hai il ruolo " + ruolo);
					return "DENIED";
				}
			} else {
				System.out.println("L'utente deve essere loggato per arrivare alla pagina ");
				return Action.LOGIN;
			}



		}

	}
