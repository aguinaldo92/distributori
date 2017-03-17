package it.unisalento.distributori.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Test;

public class PermissionsHashMapTest {

	PermissionsHashMap permissions = new PermissionsHashMap();
	
	@Test
	public void testGetRuolo_namespace() throws Exception {
		HashMap<Integer, String> hashmap = permissions.getRuolo_namespace();
		
		assertNotNull(hashmap);
		
		String hashmap_obj;
		boolean error=false;
		for (int i=0; i<hashmap.size(); i++){
			hashmap_obj=hashmap.get(i);
			if((i==0 && !hashmap_obj.equals("/gestore")) || (i==1 && !hashmap_obj.equals("/dipendente")))
				error=true;
		}
		
		assertTrue(!error);
	}

}
