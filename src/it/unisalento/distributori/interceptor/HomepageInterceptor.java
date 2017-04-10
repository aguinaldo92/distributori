/**
 * 
 */
package it.unisalento.distributori.interceptor;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.dispatcher.SessionMap;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import it.unisalento.distributori.domain.Persona;
import it.unisalento.distributori.util.PermissionsHashMap;

/**
 * @author aguinaldo
 *
 */
public class HomepageInterceptor implements Interceptor {
	private static final long serialVersionUID = 1158515817809753825L;
	private SessionMap<String, Object> personaSession;
	private Integer ruolo;
	private PermissionsHashMap permissionsHashMap = new PermissionsHashMap();
	private HashMap<Integer, String> ruolo_namespace;
	private Logger logger = LogManager.getLogger(HomepageInterceptor.class.getName());

	@Override
	public void destroy() {

	}

	@Override
	public void init() {

	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		logger.debug("intercept");
		personaSession = (SessionMap<String, Object>) invocation.getInvocationContext().getSession();
		if (personaSession.containsKey("persona")) {
			ruolo_namespace = permissionsHashMap.getRuolo_namespace();
			logger.debug("chiamata action dashboard");
			ruolo = ((Persona) personaSession.get("persona")).getRuolo();
			return ruolo_namespace.get(ruolo);
		}
		return invocation.invoke();
		

	}

}
