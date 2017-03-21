package it.unisalento.distributori.domain;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import it.unisalento.distributori.factory.FactoryDao;

public class PersonaTest {

	@Test
	public void testPersonaStringStringInt() throws Exception {
		Persona persona = new Persona("esempio@esempio.it", "pass", 0);
		
		assertNotNull(persona);
		assertEquals("pass", persona.getPassword());
	}

	@Test
	public void testPersonaStringStringStringStringIntSetSetDipendente() throws Exception {
		
		Set<Feedback> feedbacks = new HashSet<Feedback>();
		Set<Acquista> acquistas = new HashSet<Acquista>(0);
		feedbacks.add(FactoryDao.getIstance().getFeedbackDao().get(1, Feedback.class));
		
		Persona persona = new Persona("Tizio", "Caio", "esempio@mail.it", "password", 1,
				feedbacks, acquistas, FactoryDao.getIstance().getDipendenteDao().get(8, Dipendente.class));
		
		assertNotNull(persona);
		assertTrue(persona.getAcquistas().size()==0);
		assertTrue(persona.getFeedbacks().size()==1);
		assertTrue(persona.getRuolo()==1);
		assertEquals("Tizio", persona.getNome());
	}

}
