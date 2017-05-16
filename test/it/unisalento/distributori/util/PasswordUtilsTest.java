package it.unisalento.distributori.util;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class PasswordUtilsTest {

	@Test
	public void testGetSha256() throws Exception {
		String pwSHA256 = PasswordUtils.getSha256("password in chiaro");
		
		assertNotEquals("password in chiaro", pwSHA256);
		assertTrue(pwSHA256.length()==64);
	}

	@Test
	public void testGetPWD() throws Exception {
		String pwd="";
		List<String> passwords = new ArrayList<String>();
		for (int i=0; i<50; i++){
			pwd=PasswordUtils.getPWD(6);
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
