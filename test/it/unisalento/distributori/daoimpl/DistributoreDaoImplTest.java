package it.unisalento.distributori.daoimpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import it.unisalento.distributori.dao.DistributoreDao;
import it.unisalento.distributori.domain.Distributore;
import it.unisalento.distributori.factory.FactoryDao;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DistributoreDaoImplTest {

	DistributoreDao dao=FactoryDao.getIstance().getDistributoreDao();
	
	@Test
	public void testGetDistributoriByIdDipendenteSortedByStato() throws Exception {
		List<Distributore> distributori = dao.getDistributoriByIdDipendenteSortedByStato(9);
		
		assertNotNull(distributori);
		
	}

	@Test
	public void testGetNumDistributoriNonOk() throws Exception {
		Long n_distrib_noOK=dao.getNumDistributoriNonOk();
		
		assertTrue(n_distrib_noOK>=0);
	}

	@Test
	public void test1() throws Exception {//set
		Distributore distributore=new Distributore();
		distributore.setIndirizzo("indirizzo test JUnit");
		distributore.setLat(BigDecimal.valueOf(0));
		distributore.setLon(BigDecimal.valueOf(0));
		distributore.setNumPosti(0);
		distributore.setNumScaffali(0);
		distributore.setPosizioneEdificio("posizione test JUnit");
		distributore.setStato(0);
		
		distributore.setId(dao.set(distributore));

		assertTrue(distributore.getId()>0);
	}

	@Test
	public void test2() throws Exception {//get by id
		
		Distributore distributore=dao.get(4, Distributore.class);
		
		assertNotNull(distributore);
		assertEquals("indirizzo test JUnit", distributore.getIndirizzo());
	}
	
	@Test
	public void test3() throws Exception {//update
		
		Distributore distributore=dao.get(4, Distributore.class);
		
		distributore.setPosizioneEdificio("posizione test JUnit updated");
		
		dao.update(distributore);
		
		distributore=dao.get(distributore.getId(), Distributore.class);
		
		assertEquals("posizione test JUnit updated", distributore.getPosizioneEdificio());
	}

	@Test
	public void test4() throws Exception {//delete
		
		Distributore distributore=dao.get(4, Distributore.class);
		dao.delete(distributore);
		
		distributore=dao.get(distributore.getId(), Distributore.class);
		
		assertEquals(null, distributore);
	}

	@Test
	public void testGetAll() throws Exception {
		List<Distributore> distributori = dao.getAll(Distributore.class);
		
		assertNotNull(distributori);
		assertTrue(distributori.size()>0);
	}

	@Test
	public void testGetAllSortedBy() throws Exception {
		List<Distributore> distributori = dao.getAllSortedBy(Distributore.class, "numPosti");
		Integer n_posti1;
		Integer n_posti2;
		boolean error = false;
		for(int i=1; i<distributori.size() && !error; i++){
			n_posti1 = distributori.get(i-1).getNumPosti();
			n_posti2 = distributori.get(i).getNumPosti();
			if(n_posti1>n_posti2)
				error=true;
		}
		assertTrue(!error);
	}

}
