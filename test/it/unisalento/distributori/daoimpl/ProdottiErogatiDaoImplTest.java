package it.unisalento.distributori.daoimpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import it.unisalento.distributori.dao.ProdottiErogatiDao;
import it.unisalento.distributori.domain.Distributore;
import it.unisalento.distributori.domain.ProdottiErogati;
import it.unisalento.distributori.domain.Prodotto;
import it.unisalento.distributori.factory.FactoryDao;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProdottiErogatiDaoImplTest {

	ProdottiErogatiDao dao = FactoryDao.getIstance().getProdottiErogatiDao();
	
	@Test
	public void testCRUD() throws Exception {
		
		Integer id;
		ProdottiErogati prod_erogati=new ProdottiErogati();
		
		//set
		Prodotto prodotto = FactoryDao.getIstance().getProdottoDao().get(1, Prodotto.class);
		Distributore distributore=FactoryDao.getIstance().getDistributoreDao().get(1, Distributore.class);
		prod_erogati.setDistributore(distributore);
		prod_erogati.setProdotto(prodotto);
		prod_erogati.setPosto(3);
		prod_erogati.setQuantita(20);
		prod_erogati.setScaffale(2);
		
		prod_erogati.setId(dao.set(prod_erogati));
		id=prod_erogati.getId();
		
		assertTrue(prod_erogati.getId()>0);
		
		//get (by ID)
		prod_erogati=dao.get(id, ProdottiErogati.class);
		
		assertNotNull(prod_erogati);
		assertEquals(id, prod_erogati.getId());
		
		//update
		prod_erogati=dao.get(id, ProdottiErogati.class);
		prod_erogati.setQuantita(1);
		dao.update(prod_erogati);
		prod_erogati=dao.get(id, ProdottiErogati.class);
		
		assertEquals((Integer)1, prod_erogati.getQuantita());
		
		//delete
		prod_erogati=dao.get(id, ProdottiErogati.class);
		dao.delete(prod_erogati);
		prod_erogati=dao.get(id, ProdottiErogati.class);
		
		assertEquals(null, prod_erogati);
	}
	
	@Test
	public void testGetProdottiScarseggiantiByDistributore() throws Exception {
		List<ProdottiErogati> prodotti = dao.getProdottiScarseggiantiByDistributore(1);
		
		assertNotNull(prodotti);
		
		boolean error=false;
		for (int i=0; i<prodotti.size() && !error; i++){
			if(prodotti.get(i).getQuantita()>5 || prodotti.get(i).getDistributore().getId()!=1)
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

}
