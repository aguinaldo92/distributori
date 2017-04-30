package it.unisalento.distributori.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class FCMSenderTest {
	
	FCMSender sendertest = new FCMSender();
	
	@Test
	public void testFCMSender() throws Exception {

		FCMSender sender = new FCMSender();
		
		assertNotNull(sender);
		assertTrue(sender.getContent_type().compareTo("application/json")==0);
		assertTrue(sender.getUrl().compareTo("https://fcm.googleapis.com/fcm/send")==0);
	}
	
	@Test
	public void testFCMSenderStringStringStringString() throws Exception {
		
		FCMSender sender = new FCMSender("topic", "testo JUnit", "Titolo JUnit");
		
		assertNotNull(sender);
		assertTrue(sender.getContent_type().compareTo("application/json")==0);
		assertTrue(sender.getUrl().compareTo("https://fcm.googleapis.com/fcm/send")==0);
		assertTrue(sender.getFCMtopic().compareTo("topic")==0);
		assertTrue(sender.getTesto().compareTo("testo JUnit")==0);
		assertTrue(sender.getTitolo().compareTo("Titolo JUnit")==0);
	}

	@Test
	public void testFCMSenderStringStringStringBoolean() throws Exception {
		
		FCMSender sender = new FCMSender("topic", "testo JUnit", "Titolo JUnit", true);
		
		assertNotNull(sender);
		assertTrue(sender.getContent_type().compareTo("application/json")==0);
		assertTrue(sender.getUrl().compareTo("https://fcm.googleapis.com/fcm/send")==0);
		assertTrue(sender.getFCMtopic().compareTo("topic")==0);
		assertTrue(sender.getTesto().compareTo("testo JUnit")==0);
		assertTrue(sender.getTitolo().compareTo("Titolo JUnit")==0);
		assertTrue(sender.isJUnit_test());
	}

	@Test
	public void testSendPOST_topic1() throws Exception {
		FCMSender sender = new FCMSender("JUnit1", 
										"Notifica di test JUnit sul topic JUnit1", "Notifica JUnit", true);
		sender.setServerKey("AIzaSyD4Mo-F-jgzBEkodP_pquU5z34DDw7ZWy4");
		String result = sender.sendPOST();
		
		assertTrue(result.compareTo("success")==0);
		
	}
	
	@Test
	public void testSendPOST_topic2() throws Exception {
		FCMSender sender = new FCMSender("JUnit2", 
										"Notifica di test JUnit sul topic JUnit2", "Notifica JUnit", true);
		sender.setServerKey("AIzaSyD4Mo-F-jgzBEkodP_pquU5z34DDw7ZWy4");
		String result = sender.sendPOST();
		
		assertTrue(result.compareTo("success")==0);
		
	}

	@Test
	public void testSetFCMtopic() throws Exception {
		sendertest.setFCMtopic("topic");
		assertTrue(sendertest.getFCMtopic().compareTo("topic")==0);
	}

	@Test
	public void testSetAPIkey() throws Exception {
		sendertest.setServerKey("key");
		assertTrue(sendertest.getServerKey().compareTo("key")==0);
	}

	@Test
	public void testSetTitolo() throws Exception {
		sendertest.setTitolo("titolo");
		assertTrue(sendertest.getTitolo().compareTo("titolo")==0);
	}

	@Test
	public void testSetTesto() throws Exception {
		sendertest.setTesto("testo");
		assertTrue(sendertest.getTesto().compareTo("testo")==0);
	}

	@Test
	public void testSetJUnit_test() throws Exception {
		sendertest.setJUnit_test(false);
		assertFalse(sendertest.isJUnit_test());
	}

}
