package it.unisalento.distributori.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class PersonaModelTest {

	PersonaModel persona = new PersonaModel();
	
	@Test
	public void testSetTelefono() throws Exception {
		persona.setTelefono("0000887766");
		assertEquals("0000887766", persona.getTelefono());
	}

	@Test
	public void testSetId() throws Exception {
		persona.setId(2);
		assertEquals((Integer)2, persona.getId());
	}

	@Test
	public void testSetNome() throws Exception {
		persona.setNome("JUnit");
		assertEquals("JUnit", persona.getNome());
	}

	@Test
	public void testSetCognome() throws Exception {
		persona.setCognome("JUnit cognome");
		assertEquals("JUnit cognome", persona.getCognome());
	}

	@Test
	public void testSetEmail() throws Exception {
		persona.setEmail("mail@junit.test");
		assertEquals("mail@junit.test", persona.getEmail());
	}

	@Test
	public void testSetRuolo() throws Exception {
		persona.setRuolo(1);
		assertTrue(persona.getRuolo()==1);
	}

}
