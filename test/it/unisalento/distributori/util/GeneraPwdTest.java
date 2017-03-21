package it.unisalento.distributori.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
		assertEquals((int)10, pwd_gen.getDim());
	}

	@Test
	public void testGetPWD() throws Exception {
		pwd_gen = new GeneraPwd(6);
		String pwd="";
		List<String> passwords = new ArrayList<String>();
		for (int i=0; i<50; i++){
			pwd=pwd_gen.getPWD();
			passwords.add(pwd);

			System.out.println("Password "+i+": "+pwd);
		}
		
		boolean error=false;
		for(int i=0; i<passwords.size() && !error; i++){
			for (int j=i+1; j<passwords.size() && !error; j++){
				assertNotEquals(passwords.get(i), passwords.get(j));
			}
		}
		
		assertFalse(error);
	}

}
