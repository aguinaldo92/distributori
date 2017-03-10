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
	public void test1() throws Exception {//set
		Persona persona=new Persona();
		persona.setNome("Giacomo");
		persona.setPassword("password");
		persona.setCognome("Rossi");
		persona.setEmail("dd@ff.it");
		
		persona.setId(dao.set(persona));
		
		assertTrue(persona.getId()>0);
	}
	
	@Test
	public void test2() throws Exception {//get by id
		Persona persona=dao.get(1, Persona.class);
		
		assertNotNull(persona);
		assertEquals((Integer)1, persona.getId());
	}
	
	@Test
	public void test3() throws Exception {//get by credentials
		
		Persona persona=dao.getPersonaByCredentials("dd@ff.it", "password");
		
		assertNotNull(persona);
		assertEquals("dd@ff.it", persona.getEmail());
	}

	@Test
	public void test4() throws Exception {//email exists
		
		int id_test = (dao.getPersonaByCredentials("dd@ff.it", "password")).getId();
		
		boolean mymailexist = dao.emailExists("dd@ff.it", id_test);
		boolean notmineexists = dao.emailExists("wifidrinksnacks@gmail.com", id_test);
		
		assertTrue(!mymailexist);
		assertTrue(notmineexists);
	}
	
	@Test
	public void test5() throws Exception {//update
		
		Persona persona=dao.getPersonaByCredentials("dd@ff.it", "password");
		persona.setNome("Mario");
		dao.update(persona);
		persona=dao.getPersonaByCredentials("dd@ff.it", "password");
		
		assertEquals("Mario", persona.getNome());
	}

	@Test
	public void test6() throws Exception {//delete
		Persona persona=dao.getPersonaByCredentials("dd@ff.it", "password");
		dao.delete(persona);
		persona=dao.getPersonaByCredentials("dd@ff.it", "password");
		
		assertEquals(null, persona);
	}

	@Test
	public void test7() throws Exception {//get all
		List<Persona> persone = dao.getAll(Persona.class);
		
		assertNotNull(persone);
		assertTrue(persone.size()>0);
	}

	@Test
	public void test8() throws Exception {//get all sorted by
		List<Persona> persone = dao.getAllSortedBy(Persona.class, "nome");
		Persona persona1 = new Persona();
		Persona persona2 = new Persona();
		boolean error = false;
		for(int i=1; i<persone.size() && !error; i++){
			persona1 = persone.get(i-1);
			persona2 = persone.get(i);
			if(persona1.getNome().compareTo(persona2.getNome())>0)
				error=true;
		}
		assertTrue(error);
	}

}
