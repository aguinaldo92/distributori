package it.unisalento.distributori.interceptor;

import it.unisalento.distributori.domain.Persona;
import it.unisalento.distributori.util.PermissionsHashMap;

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
	private Integer ruolo;
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
		permissionsHashMap = new PermissionsHashMap();
		ruolo_namespace = permissionsHashMap.getRuolo_namespace();
		

	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		personaSession = (SessionMap<String, Object>) invocation.getInvocationContext().getSession();
		System.out.println("LoginInterceptor: intercept");

		if (personaSession.containsKey("persona")) {
			namespaceAction = invocation.getProxy().getNamespace();
			nameAction = invocation.getProxy().getActionName();
			System.out.println("LoginInterceptor: Namespace: " + namespaceAction);
			System.out.println("LoginInterceptor: action name:" + nameAction);

			
			ruolo = ((Persona) personaSession.get("persona")).getRuolo();
			if (ruolo_namespace.get(ruolo).equals(namespaceAction)) {
				System.out.println("LoginInterceptor: permessi ok");
				System.out.println("LoginInterceptor: sei nel namespace: " + namespaceAction + " action:  " + nameAction
						+ " poichè hai il ruolo " + ruolo);
				return invocation.invoke();
			} else {
				System.out.println("LoginInterceptor: permessi Insufficienti ");
				System.out.println(" LoginInterceptor: hai provato ad entrare nel namespace " + namespaceAction + " action:  " + nameAction
						+ " poichè hai il ruolo " + ruolo);
				return Action.LOGIN;
			}
		} else {
			System.out.println("LoginInterceptor: L'utente deve essere loggato per arrivare alla pagina ");
			return Action.LOGIN;
		}

	}

}
