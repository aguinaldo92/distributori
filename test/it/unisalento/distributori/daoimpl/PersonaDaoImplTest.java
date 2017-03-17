package it.unisalento.distributori.daoimpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import it.unisalento.distributori.dao.PersonaDao;
import it.unisalento.distributori.domain.Persona;
import it.unisalento.distributori.factory.FactoryDao;

public class PersonaDaoImplTest {
	
	PersonaDao dao=FactoryDao.getIstance().getPersonaDao();
	
	@Test
	public void testGetPersonaByCredentials() throws Exception {
		
		Persona persona=dao.getPersonaByCredentials("wifidrinksnacks@gmail.com", "admin");
		
		assertNotNull(persona);
		assertEquals("wifidrinksnacks@gmail.com", persona.getEmail());
	}

	@Test
	public void testEmailExists() throws Exception {
		
		int id_test = (dao.getPersonaByCredentials("wifidrinksnacks@gmail.com", "admin")).getId();
		
		boolean mymailexist = dao.emailExists("wifidrinksnacks@gmail.com", id_test);
		boolean notmineexists = dao.emailExists("sato89@hotmail.it", id_test);
		
		assertTrue(!mymailexist);
		assertTrue(notmineexists);
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
		List<Persona> persone = dao.getAll(Persona.class);
		
		assertNotNull(persone);
		assertTrue(persone.size()>0);
	}

	@Test
	public void testGetAllSortedBy() throws Exception {
		List<Persona> persone = dao.getAllSortedBy(Persona.class, "nome");
		String nome1;
		String nome2;
		boolean error = false;
		for(int i=1; i<persone.size() && !error; i++){
			nome1 = persone.get(i-1).getNome();
			nome2 = persone.get(i).getNome();
			if(nome1.compareToIgnoreCase(nome2)>0)
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
