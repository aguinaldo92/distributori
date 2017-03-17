package it.unisalento.distributori.daoimpl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import it.unisalento.distributori.dao.FamiglieProdottoDao;
import it.unisalento.distributori.domain.FamiglieProdotto;
import it.unisalento.distributori.domain.Prodotto;
import it.unisalento.distributori.factory.FactoryDao;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FamiglieProdottoDaoImplTest {

	FamiglieProdottoDao dao = FactoryDao.getIstance().getFamiglieProdottoDao();
	
	@Test
	public void testGetFamiglieByProdotto() throws Exception {
		Prodotto prodotto=FactoryDao.getIstance().getProdottoDao().get(1, Prodotto.class);
		
		List<FamiglieProdotto> prod_families = dao.getFamiglieByProdotto(prodotto.getId());
		
		assertNotNull(prod_families);
		
		boolean error=false;
		for (int i=0; i<prod_families.size() && !error; i++){
			if (prod_families.get(i).getProdotto().getId()!=prodotto.getId())
				error=true;
		}
		
		assertTrue(!error);
	}

	@Test
	public void testDeleteProdottoFamilies() throws Exception {
		Prodotto prodotto=FactoryDao.getIstance().getProdottoDao().get(1, Prodotto.class);
		
		List<FamiglieProdotto> prod_families1 = dao.getFamiglieByProdotto(prodotto.getId());
		assertTrue(prod_families1.size()>0);
		
		FactoryDao.getIstance().getFamiglieProdottoDao().deleteProdottoFamilies(prodotto.getId());
		
		List<FamiglieProdotto> prod_families2 = dao.getFamiglieByProdotto(prodotto.getId());
		assertTrue(prod_families2.size()==0);
		
		//reset delle info testate
		FamiglieProdotto famiglia_prod = new FamiglieProdotto();
		for (int i=0; i<prod_families1.size(); i++){
			famiglia_prod=prod_families1.get(i);
			dao.set(famiglia_prod);
		}
	}
	@Test
	public void test1() throws Exception {//set
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void test2() throws Exception {//get by id
		throw new RuntimeException("not yet implemented");
	}
	
	@Test
	public void test3() throws Exception {//update
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void test4() throws Exception {//delete
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testGetAll() throws Exception {
		List<FamiglieProdotto> prod_families = dao.getAll(FamiglieProdotto.class);
		
		assertNotNull(prod_families);
		assertTrue(prod_families.size()>0);
	}

	@Test
	public void testGetAllSortedBy() throws Exception {
		List<FamiglieProdotto> prod_families = dao.getAllSortedBy(FamiglieProdotto.class, "id");
		Integer id1;
		Integer id2;
		boolean error = false;
		for(int i=1; i<prod_families.size() && !error; i++){
			id1 = prod_families.get(i-1).getId();
			id2 = prod_families.get(i).getId();
			if(id1>id2)
				error=true;
		}
		assertTrue(!error);
	}

}
