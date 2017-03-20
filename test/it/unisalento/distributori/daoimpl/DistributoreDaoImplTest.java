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
	public void testCRUD() throws Exception {
		
		Integer id;
		Distributore distributore=new Distributore();
		
		//set
		distributore.setIndirizzo("indirizzo test JUnit");
		distributore.setLat(BigDecimal.valueOf(0));
		distributore.setLon(BigDecimal.valueOf(0));
		distributore.setNumPosti(0);
		distributore.setNumScaffali(0);
		distributore.setPosizioneEdificio("posizione test JUnit");
		distributore.setStato(0);
		
		distributore.setId(dao.set(distributore));
		
		id=distributore.getId();

		assertTrue(distributore.getId()>0);
		
		//get (by ID)
		distributore=dao.get(id, Distributore.class);
		
		assertNotNull(distributore);
		assertEquals("indirizzo test JUnit", distributore.getIndirizzo());
		
		//update
		distributore=dao.get(id, Distributore.class);
		
		distributore.setPosizioneEdificio("posizione test JUnit updated");
		
		dao.update(distributore);
		
		distributore=dao.get(id, Distributore.class);
		
		assertEquals("posizione test JUnit updated", distributore.getPosizioneEdificio());
		
		//delete
		distributore=dao.get(id, Distributore.class);
		dao.delete(distributore);
		
		distributore=dao.get(id, Distributore.class);
		
		assertEquals(null, distributore);	
	}
	
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
