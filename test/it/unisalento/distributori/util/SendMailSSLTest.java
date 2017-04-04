package it.unisalento.distributori.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class SendMailSSLTest {

	SendMailSSL mail_sender = new SendMailSSL();
	
	@Test
	public void testSend() throws Exception {
		int result=mail_sender.send("wifidrinksnacks@gmail.com", "mail da JUnit Test", "Test di JUnit.");
		
		assertTrue(result==0);
	}

}
