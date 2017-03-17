package it.unisalento.distributori.daoimpl;

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
	public void testGetNumMessaggiNonLetti() throws Exception {
		Persona persona = FactoryDao.getIstance().getPersonaDao().get(10, Persona.class);
		Long n_msg1 = dao.getNumMessaggiNonLetti();
		
		Feedback new_msg = new Feedback();
		new_msg.setData(new Date());
		new_msg.setLetto((byte) 0);
		new_msg.setPersona(persona);
		new_msg.setTesto("testo di prova - JUnit Test");
		FactoryDao.getIstance().getFeedbackDao().set(new_msg);
		
		Long n_msg2 = FactoryDao.getIstance().getFeedbackDao().getNumMessaggiNonLetti();
		
		assertTrue((n_msg1+1)==n_msg2);
	}

	@Test
	public void testSet() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testGet() throws Exception {
		throw new RuntimeException("not yet implemented");
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
	
	@Test
	public void testUpdate() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testDelete() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

}
