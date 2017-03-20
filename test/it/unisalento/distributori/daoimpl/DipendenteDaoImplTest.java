package it.unisalento.distributori.daoimpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import it.unisalento.distributori.dao.DipendenteDao;
import it.unisalento.distributori.domain.Dipendente;
import it.unisalento.distributori.domain.Persona;
import it.unisalento.distributori.factory.FactoryDao;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DipendenteDaoImplTest {

	DipendenteDao dao=FactoryDao.getIstance().getDipendenteDao();
	
	@Test
	public void testCRUD() throws Exception {
		
		Integer id;
		Dipendente dipendente=new Dipendente();
		
		//set
		dipendente.setTelefono("0000000000");
		Persona persona = new Persona();
		persona.setNome("Giacomo");
		persona.setPassword("password");
		persona.setCognome("Rossi");
		persona.setEmail("dd@ff.it");
		persona.setId(FactoryDao.getIstance().getPersonaDao().set(persona));
		
		dipendente.setPersona(persona);
		dipendente.setPersonaId(dao.set(dipendente));
		
		id=dipendente.getPersonaId();

		assertTrue(dipendente.getPersonaId()>0);
		assertTrue(dipendente.getPersonaId()==persona.getId());
		
		//get (by ID)
		persona=FactoryDao.getIstance().getPersonaDao().getPersonaByCredentials("dd@ff.it", "password");
		
		dipendente=dao.get(persona.getId(), Dipendente.class);
		
		assertNotNull(dipendente);
		assertTrue(persona.getId()==dipendente.getPersonaId());
		
		//update
		dipendente=dao.get(id, Dipendente.class);
		dipendente.setTelefono("0836333333");
		dao.update(dipendente);
		
		dipendente=dao.get(id, Dipendente.class);
		
		assertEquals("0836333333", dipendente.getTelefono());
		
		//delete
		dipendente=dao.get(id, Dipendente.class);
		dao.delete(dipendente);
		
		FactoryDao.getIstance().getPersonaDao().delete(persona);
		
		dipendente=dao.get(id, Dipendente.class);
		
		assertEquals(null, dipendente);		
	}

	@Test
	public void testGetAll() throws Exception {
		List<Dipendente> dipendenti = dao.getAll(Dipendente.class);
		
		assertNotNull(dipendenti);
		assertTrue(dipendenti.size()>0);
	}

	@Test
	public void testGetAllSortedBy() throws Exception {
		List<Dipendente> dipendenti = dao.getAllSortedBy(Dipendente.class, "telefono");
		String tel1;
		String tel2;
		boolean error = false;
		for(int i=1; i<dipendenti.size() && !error; i++){
			tel1 = dipendenti.get(i-1).getTelefono();
			tel2 = dipendenti.get(i).getTelefono();
			if(tel1.compareToIgnoreCase(tel2)>0)
				error=true;
		}
		assertTrue(!error);
	}

}
