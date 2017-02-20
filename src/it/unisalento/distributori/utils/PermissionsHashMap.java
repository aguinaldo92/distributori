/**
 * 
 */
package it.unisalento.distributori.utils;

import java.util.HashMap;

/**
 * @author aguinaldo
 *
 */
public class PermissionsHashMap {
	private HashMap<Integer, String> ruolo_namespace = new HashMap<Integer, String>();

	public HashMap<Integer, String> getRuolo_namespace() {
		ruolo_namespace.put(0, "/gestore");
		ruolo_namespace.put(1, "/dipendente");
		return ruolo_namespace;
	}

	
	
}
