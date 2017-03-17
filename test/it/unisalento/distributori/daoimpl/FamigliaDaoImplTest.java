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
	public void test1() throws Exception {//set
		Famiglia famiglia=new Famiglia();
		famiglia.setNome("Test JUnit");
		
		famiglia.setId(dao.set(famiglia));
		
		assertTrue(famiglia.getId()>0);
	}

	@Test
	public void test2() throws Exception {//get by id
		Famiglia famiglia=dao.get(8, Famiglia.class);
		
		assertNotNull(famiglia);
		assertEquals((Integer)8, famiglia.getId());
	}
	
	@Test
	public void test3() throws Exception {//update
		Famiglia famiglia=dao.get(8, Famiglia.class);
		famiglia.setNome("Test update JUnit");
		dao.update(famiglia);
		famiglia=dao.get(8, Famiglia.class);
		
		assertEquals("Test update JUnit", famiglia.getNome());
	}

	@Test
	public void test4() throws Exception {//delete
		Famiglia famiglia=dao.get(8, Famiglia.class);
		dao.delete(famiglia);
		famiglia=dao.get(8, Famiglia.class);
		
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
