package it.unisalento.distributori.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class PasswordUtilsTest {

	@Test
	public void testGetSha256() throws Exception {
		String pwSHA256 = PasswordUtils.getSha256("password in chiaro");
		
		assertNotEquals("password in chiaro", pwSHA256);
		assertTrue(pwSHA256.length()==64);
	}

}
