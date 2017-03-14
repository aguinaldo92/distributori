package it.unisalento.distributori.daoimpl;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

import it.unisalento.distributori.domain.Feedback;
import it.unisalento.distributori.domain.Persona;
import it.unisalento.distributori.factory.FactoryDao;

public class FeedbackDaoImplTest {

	@Test
	public void testGetNumMessaggiNonLetti() throws Exception {
		Persona persona = FactoryDao.getIstance().getPersonaDao().get(10, Persona.class);
		Long n_msg1 = FactoryDao.getIstance().getFeedbackDao().getNumMessaggiNonLetti();
		
		Feedback new_msg = new Feedback();
		new_msg.setData(new Date());
		new_msg.setLetto((byte) 0);
		new_msg.setPersona(persona);
		new_msg.setTesto("testo di prova - JUnit Test");
		FactoryDao.getIstance().getFeedbackDao().set(new_msg);
		
		Long n_msg2 = FactoryDao.getIstance().getFeedbackDao().getNumMessaggiNonLetti();
		
		assertTrue((n_msg1+1)==n_msg2);
	}

}
