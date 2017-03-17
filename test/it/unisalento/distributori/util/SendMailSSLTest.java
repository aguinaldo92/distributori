package it.unisalento.distributori.util;

import org.junit.Test;

public class SendMailSSLTest {

	SendMailSSL mail_sender = new SendMailSSL();
	
	@Test
	public void testSend() throws Exception {
		mail_sender.send("sato89@hotmail.it", "mail da JUnit Test", "Test di JUnit.");
	}

}
