package it.unisalento.distributori.daoimpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.sun.jmx.remote.security.NotificationAccessController;

import it.unisalento.distributori.dao.PersonaDao;
import it.unisalento.distributori.domain.Persona;
import it.unisalento.distributori.factory.FactoryDao;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonaDaoImplTest {
	
	PersonaDao dao=FactoryDao.getIstance().getPersonaDao();
	
	@Test
	public void test1() throws Exception {//get by credentials
		
		Persona persona=dao.getPersonaByCredentials("wifidrinksnacks@gmail.com", "admin");
		
		assertNotNull(persona);
		assertEquals("wifidrinksnacks@gmail.com", persona.getEmail());
	}

	@Test
	public void test2() throws Exception {//email exists
		
		int id_test = (dao.getPersonaByCredentials("wifidrinksnacks@gmail.com", "admin")).getId();
		
		boolean mymailexist = dao.emailExists("wifidrinksnacks@gmail.com", id_test);
		boolean notmineexists = dao.emailExists("sato89@hotmail.it", id_test);
		
		assertTrue(!mymailexist);
		assertTrue(notmineexists);
	}

}
