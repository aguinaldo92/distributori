package it.unisalento.distributori.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GeneraPwdTest {

	GeneraPwd pwd_gen;
	
	@Test
	public void testGeneraPwd() throws Exception {
		pwd_gen = new GeneraPwd(10);
		
		assertNotNull(pwd_gen);
	}

	@Test
	public void testGetPWD() throws Exception {
		pwd_gen = new GeneraPwd(10);
		String pwd=pwd_gen.getPWD();
		assertEquals((int)10, pwd.length());
	}

}
