package it.unisalento.distributori.daoimpl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import it.unisalento.distributori.dao.PersonaDao;
import it.unisalento.distributori.domain.Persona;
import it.unisalento.distributori.factory.FactoryDao;

public class PersonaDaoImplTest {
	
	PersonaDao dao=FactoryDao.getIstance().getPersonaDao();
	
	@Test
	public void testCRUD() throws Exception {
		
		Integer id;
		Persona persona=new Persona();
		
		//set
		persona.setCognome("Cesare");
		persona.setEmail("dd@gg.tt");
		persona.setNome("Giulio");
		persona.setPassword("password");
		persona.setRuolo(0);
		
		persona.setId(dao.set(persona));
		id=persona.getId();
		
		assertTrue(persona.getId()>0);
		
		//get (by ID)
		persona=dao.get(id, Persona.class);
		
		assertNotNull(persona);
		assertEquals(id, persona.getId());
		
		//update
		persona=dao.get(id, Persona.class);
		persona.setNome("Giulietto");
		dao.update(persona);
		persona=dao.get(id, Persona.class);
		
		assertEquals("Giulietto", persona.getNome());
		
		//delete
		persona=dao.get(id, Persona.class);
		dao.delete(persona);
		persona=dao.get(id, Persona.class);
		
		assertEquals(null, persona);
	}
	
	@Test
	public void testGetPersonaByCredentials() throws Exception {
		
		Persona persona=dao.getPersonaByCredentials("wifidrinksnacks@gmail.com", "admin");
		
		assertNotNull(persona);
		assertEquals("wifidrinksnacks@gmail.com", persona.getEmail());
		
		persona=dao.getPersonaByCredentials("email@inesistente.it", "pwd");
		
		assertNull(persona);
	}

	@Test
	public void testEmailExists() throws Exception {
		
		int id_test = (dao.getPersonaByCredentials("wifidrinksnacks@gmail.com", "admin")).getId();
		
		boolean mymailexist = dao.emailExists("wifidrinksnacks@gmail.com", id_test);
		boolean notmineexists = dao.emailExists("sato89@hotmail.it", id_test);
		boolean mailexists = dao.emailExists("sato89@hotmail.it", null);
		
		assertFalse(mymailexist);
		assertTrue(notmineexists);
		assertTrue(mailexists);
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

}
