package it.unisalento.distributori.daoimpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import it.unisalento.distributori.dao.FeedbackDao;
import it.unisalento.distributori.domain.Feedback;
import it.unisalento.distributori.domain.Persona;
import it.unisalento.distributori.factory.FactoryDao;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FeedbackDaoImplTest {

	FeedbackDao dao = FactoryDao.getIstance().getFeedbackDao();
	
	@Test
	public void testCRUD() throws Exception {
		
		Integer id;
		Feedback feedback=new Feedback();
		
		//set
		Byte letto=0;
		Persona persona = FactoryDao.getIstance().getPersonaDao().get(10, Persona.class);
		feedback.setData(new Date());
		feedback.setLetto(letto);
		feedback.setTesto("testo di prova JUnit");
		feedback.setPersona(persona);
		feedback.setId(dao.set(feedback));
		
		id=feedback.getId();
		
		assertTrue(feedback.getId()>0);
		
		//get (by ID)
		feedback=dao.get(id, Feedback.class);
		
		assertNotNull(feedback);
		assertEquals(id, feedback.getId());
		
		//update
		feedback=dao.get(id, Feedback.class);
		feedback.setTesto("testo di prova updated JUnit");
		dao.update(feedback);
		feedback=dao.get(id, Feedback.class);
		
		assertEquals("testo di prova updated JUnit", feedback.getTesto());
		
		//delete
		feedback=dao.get(id, Feedback.class);
		dao.delete(feedback);
		feedback=dao.get(id, Feedback.class);
		
		assertEquals(null, feedback);
	}
	
	@Test
	public void testGetNumMessaggiNonLetti() throws Exception {
		Persona persona = FactoryDao.getIstance().getPersonaDao().get(10, Persona.class);
		Long n_msg1 = dao.getNumMessaggiNonLetti();
		
		Feedback new_msg = new Feedback();
		new_msg.setData(new Date());
		new_msg.setLetto((byte) 0);
		new_msg.setPersona(persona);
		new_msg.setTesto("testo di prova - JUnit Test");
		new_msg.setId(dao.set(new_msg));
		
		Long n_msg2 = FactoryDao.getIstance().getFeedbackDao().getNumMessaggiNonLetti();
		
		assertTrue((n_msg1+1)==n_msg2);
		
		dao.delete(new_msg);
	}

	@Test
	public void testGetAll() throws Exception {
		List<Feedback> feedbacks = dao.getAll(Feedback.class);
		
		assertNotNull(feedbacks);
		assertTrue(feedbacks.size()>0);
	}

	@Test
	public void testGetAllSortedBy() throws Exception {
		List<Feedback> feedbacks = dao.getAllSortedBy(Feedback.class, "letto");
		Byte letto1;
		Byte letto2;
		boolean error = false;
		for(int i=1; i<feedbacks.size() && !error; i++){
			letto1 = feedbacks.get(i-1).getLetto();
			letto2 = feedbacks.get(i).getLetto();
			if(letto1>letto2)
				error=true;
		}
		assertTrue(!error);
	}

}
