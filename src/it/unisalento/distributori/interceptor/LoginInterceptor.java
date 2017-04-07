package it.unisalento.distributori.interceptor;

import it.unisalento.distributori.domain.Persona;
import it.unisalento.distributori.util.PermissionsHashMap;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.dispatcher.SessionMap;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;


public class LoginInterceptor implements Interceptor {

	private static final long serialVersionUID = -1301649202050082833L;
	private SessionMap<String, Object> personaSession;
	private Integer ruolo;
	private String namespaceAction;
	private String nameAction;
	private PermissionsHashMap permissionsHashMap;
	private HashMap<Integer, String> ruolo_namespace;
	private Logger logger = LogManager.getLogger(LoginInterceptor.class.getName());

	@Override
	public void destroy() {
	}

	@Override
	public void init() {
		permissionsHashMap = new PermissionsHashMap();
		ruolo_namespace = permissionsHashMap.getRuolo_namespace();
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		personaSession = (SessionMap<String, Object>) invocation.getInvocationContext().getSession();
		logger.trace("LoginInterceptor: intercept");

		if (personaSession.containsKey("persona")) {
			namespaceAction = invocation.getProxy().getNamespace();
			nameAction = invocation.getProxy().getActionName();
			logger.trace("LoginInterceptor: Namespace: " + namespaceAction + "; Action name:" + nameAction);
			
			ruolo = ((Persona) personaSession.get("persona")).getRuolo();
			if (ruolo_namespace.get(ruolo).equals(namespaceAction)) {
				logger.trace("Permessi OK: sei nel namespace: " + namespaceAction + " action:  " + nameAction + " poichè hai il ruolo " + ruolo);
				return invocation.invoke();
			} else {
				logger.trace("LoginInterceptor: permessi Insufficienti: hai provato ad entrare nel namespace " + namespaceAction + " action:  " + nameAction + " ma il tuo ruolo è" + ruolo);
				return Action.LOGIN;
			}
		} else {
			logger.trace("L'utente deve essere loggato per arrivare alla pagina ");
			return Action.LOGIN;
		}

	}

}
