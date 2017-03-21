package it.unisalento.distributori.domain;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import it.unisalento.distributori.factory.FactoryDao;

public class FeedbackTest {

	Feedback emptyfeedback = new Feedback();
	
	@Test
	public void testFeedbackString() throws Exception {
		Feedback feedback = new Feedback("testo JUnit");
		
		assertNotNull(feedback);
		assertEquals("testo JUnit", feedback.getTesto());
	}

	@Test
	public void testFeedbackPersonaStringDateByte() throws Exception {
		Byte letto=0;
		Feedback feedback = new Feedback(FactoryDao.getIstance().getPersonaDao().get(1, Persona.class), 
				"testo JUnit", new Date(), letto);
		assertNotNull(feedback);
		assertEquals("wifidrinksnacks@gmail.com",feedback.getPersona().getEmail());
	}

	@Test
	public void testSetId() throws Exception {
		emptyfeedback.setId(10);
		assertEquals((Integer)10, emptyfeedback.getId());
	}

	@Test
	public void testSetPersona() throws Exception {
		emptyfeedback.setPersona(FactoryDao.getIstance().getPersonaDao().get(1, Persona.class));
		assertEquals("Giovanni", emptyfeedback.getPersona().getNome());
	}

	@Test
	public void testSetTesto() throws Exception {
		emptyfeedback.setTesto("Prova setter");
		assertEquals("Prova setter", emptyfeedback.getTesto());
	}

	@Test
	public void testSetData() throws Exception {
		Date data = new Date();
		emptyfeedback.setData(data);
		assertEquals(data, emptyfeedback.getData());
	}

	@Test
	public void testSetLetto() throws Exception {
		emptyfeedback.setLetto(Byte.valueOf((byte) 1));
		assertEquals(Byte.valueOf("1"), emptyfeedback.getLetto());
	}

}
