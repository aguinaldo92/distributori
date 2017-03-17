package it.unisalento.distributori.daoimpl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import it.unisalento.distributori.dao.ProdottiErogatiDao;
import it.unisalento.distributori.domain.ProdottiErogati;
import it.unisalento.distributori.factory.FactoryDao;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProdottiErogatiDaoImplTest {

	ProdottiErogatiDao dao = FactoryDao.getIstance().getProdottiErogatiDao();
	
	@Test
	public void testGetProdottiScarseggiantiByDistributore() throws Exception {
		List<ProdottiErogati> prodotti = dao.getProdottiScarseggiantiByDistributore(1, 10);
		
		assertNotNull(prodotti);
		
		boolean error=false;
		for (int i=0; i<prodotti.size() && !error; i++){
			if(prodotti.get(i).getQuantita()>10 || prodotti.get(i).getDistributore().getId()!=1)
				error=true;
		}
		
		assertTrue(!error);
	}

	@Test
	public void testGetProdottiErogatiByDistributoreSortedByScaffalePosto() throws Exception {
		List<ProdottiErogati> prodotti = dao.getProdottiErogatiByDistributoreSortedByScaffalePosto(3);
		
		assertNotNull(prodotti);
		
		boolean error=false;
		for (int i=1; i<prodotti.size(); i++){
			if((prodotti.get(i).getScaffale()==prodotti.get(i-1).getScaffale() &&
					prodotti.get(i).getPosto()<prodotti.get(i-1).getPosto()) || 
					prodotti.get(i).getScaffale()<prodotti.get(i-1).getScaffale())
				error=true;
		}
		
		assertTrue(!error);
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
		List<ProdottiErogati> prodotti = dao.getAll(ProdottiErogati.class);
		
		assertNotNull(prodotti);
		assertTrue(prodotti.size()>0);
	}

	@Test
	public void testGetAllSortedBy() throws Exception {
		List<ProdottiErogati> prodotti = dao.getAllSortedBy(ProdottiErogati.class, "quantita");
		Integer quantita1;
		Integer quantita2;
		boolean error = false;
		for(int i=1; i<prodotti.size() && !error; i++){
			quantita1 = prodotti.get(i-1).getQuantita();
			quantita2 = prodotti.get(i).getQuantita();
			if(quantita1>quantita2)
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
