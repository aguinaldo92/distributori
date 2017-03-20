package it.unisalento.distributori.daoimpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import it.unisalento.distributori.dao.FamigliaDao;
import it.unisalento.distributori.domain.Famiglia;
import it.unisalento.distributori.factory.FactoryDao;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FamigliaDaoImplTest {
	
	FamigliaDao dao=FactoryDao.getIstance().getFamigliaDao();
	
	@Test
	public void testCRUD() throws Exception {
		
		Integer id;
		Famiglia famiglia=new Famiglia();
		
		//set
		famiglia.setNome("Test JUnit");
		
		famiglia.setId(dao.set(famiglia));
		
		id=famiglia.getId();
		
		assertTrue(famiglia.getId()>0);
		
		//get (by ID)
		famiglia=dao.get(id, Famiglia.class);
		
		assertNotNull(famiglia);
		assertEquals(id, famiglia.getId());
		
		//update
		famiglia=dao.get(id, Famiglia.class);
		famiglia.setNome("Test update JUnit");
		dao.update(famiglia);
		famiglia=dao.get(id, Famiglia.class);
		
		assertEquals("Test update JUnit", famiglia.getNome());
		
		//delete
		famiglia=dao.get(id, Famiglia.class);
		dao.delete(famiglia);
		famiglia=dao.get(id, Famiglia.class);
		
		assertEquals(null, famiglia);
	}

	@Test
	public void testGetAll() throws Exception {
		List<Famiglia> famiglie = dao.getAll(Famiglia.class);
		
		assertNotNull(famiglie);
		assertTrue(famiglie.size()>0);
	}

	@Test
	public void testGetAllSortedBy() throws Exception {//get all sorted by
		List<Famiglia> famiglie = dao.getAllSortedBy(Famiglia.class, "nome");
		String nome1;
		String nome2;
		boolean error = false;
		for(int i=1; i<famiglie.size() && !error; i++){
			nome1 = famiglie.get(i-1).getNome();
			nome2 = famiglie.get(i).getNome();
			if(nome1.compareTo(nome2)>0)
				error=true;
		}
		assertTrue(!error);
	}

}
