/**
 * 
 */
package it.unisalento.distributori.interceptor;

import java.util.HashMap;

import org.apache.struts2.dispatcher.SessionMap;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import freemarker.core.Environment.Namespace;
import it.unisalento.distributori.domain.Persona;
import it.unisalento.distributori.util.PermissionsHashMap;

/**
 * @author aguinaldo
 *
 */
public class HomepageInterceptor implements Interceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4999562277550327131L;
	private SessionMap<String, Object> personaSession;
	private Integer ruolo;
	private PermissionsHashMap permissionsHashMap = new PermissionsHashMap();
	private HashMap<Integer, String> ruolo_namespace;
	private String namespaceAction;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		personaSession = (SessionMap<String, Object>) invocation.getInvocationContext().getSession();
		if (personaSession.containsKey("persona")) {
			ruolo_namespace = permissionsHashMap.getRuolo_namespace();
			System.out.println("chiamata action dashboard");
			ruolo = ((Persona) personaSession.get("persona")).getRuolo();
			return ruolo_namespace.get(ruolo);
		}
		return invocation.invoke();
		

	}

}
