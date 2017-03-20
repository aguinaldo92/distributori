package it.unisalento.distributori.daoimpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import it.unisalento.distributori.dao.ProduttoreDao;
import it.unisalento.distributori.domain.Produttore;
import it.unisalento.distributori.domain.Stabilimento;
import it.unisalento.distributori.factory.FactoryDao;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProduttoreDaoImplTest {

	ProduttoreDao dao = FactoryDao.getIstance().getProduttoreDao();
	
	@Test
	public void testCRUD() throws Exception {
		
		Integer id;
		Produttore produttore=new Produttore();
		
		//set
		produttore.setNome("JUnit Test");
		produttore.setSede("prova");
		
		produttore.setId(dao.set(produttore));
		
		id=produttore.getId();
		
		assertTrue(produttore.getId()>0);
		
		//get (by ID)
		produttore=dao.get(id, Produttore.class);
		
		assertNotNull(produttore);
		assertEquals(id, produttore.getId());
		assertEquals("JUnit Test", produttore.getNome());
		
		//update
		produttore=dao.get(id, Produttore.class);
		produttore.setNome("JUnit Test updated");
		dao.update(produttore);
		produttore=dao.get(id, Produttore.class);
		
		assertEquals("JUnit Test updated", produttore.getNome());
		
		//delete
		produttore=dao.get(id, Produttore.class);
		dao.delete(produttore);
		produttore=dao.get(id, Produttore.class);
		
		assertEquals(null, produttore);		
	}
	
	@Test
	public void testGetStabilimentiByProduttore() throws Exception {
		List<Stabilimento> stabilimenti = dao.getStabilimentiByProduttore(1);
		
		assertNotNull(stabilimenti);
		
		boolean error=false;
		for (int i=0; i<stabilimenti.size(); i++){
			if(stabilimenti.get(i).getProduttore().getId()!=1 || stabilimenti.get(i).getCitta().compareTo("fittizia")==0)
				error=true;
		}
		
		assertTrue(!error);
	}

	@Test
	public void testGetAllProduttori() throws Exception {
		List<Produttore> produttori = dao.getAllProduttori();
		
		assertNotNull(produttori);
		
		boolean produttore_fittizio = false;
		String nome1;
		String nome2;
		boolean sorted_produttori = true;
		
		for(int i=1; i<produttori.size() && sorted_produttori && !produttore_fittizio; i++){
			nome1 = produttori.get(i-1).getNome();
			nome2 = produttori.get(i).getNome();
			if(nome1.compareToIgnoreCase(nome2)>0)
				sorted_produttori=false;
			if(nome1.compareToIgnoreCase("fittizio")==0 || nome2.compareToIgnoreCase("fittizio")==0)
				produttore_fittizio=true;
		}
		
		assertTrue(sorted_produttori);
		assertTrue(!produttore_fittizio);
	}

	@Test
	public void testGetAll() throws Exception {
		List<Produttore> produttori = dao.getAll(Produttore.class);
		
		assertNotNull(produttori);
		assertTrue(produttori.size()>0);
	}

	@Test
	public void testGetAllSortedBy() throws Exception {
		List<Produttore> produttori = dao.getAllSortedBy(Produttore.class, "sede");
		String sede1;
		String sede2;
		boolean error = false;
		for(int i=1; i<produttori.size() && !error; i++){
			sede1 = produttori.get(i-1).getSede();
			sede2 = produttori.get(i).getSede();
			if(sede1.compareToIgnoreCase(sede2)>0)
				error=true;
		}
		assertTrue(!error);
	}

}
