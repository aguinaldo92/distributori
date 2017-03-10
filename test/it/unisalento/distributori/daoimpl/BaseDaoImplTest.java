package it.unisalento.distributori.daoimpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import it.unisalento.distributori.dao.PersonaDao;
import it.unisalento.distributori.domain.Persona;
import it.unisalento.distributori.factory.FactoryDao;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BaseDaoImplTest {
	
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
	public void test3() throws Exception {//update
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
		String nome1;
		String nome2;
		boolean error = false;
		for(int i=1; i<persone.size() && !error; i++){
			nome1 = persone.get(i-1).getNome();
			nome2 = persone.get(i).getNome();
			if(nome1.compareTo(nome2)>0)
				error=true;
		}
		assertTrue(!error);
	}

}
