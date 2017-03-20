package it.unisalento.distributori.daoimpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import it.unisalento.distributori.dao.StabilimentoDao;
import it.unisalento.distributori.domain.Produttore;
import it.unisalento.distributori.domain.Stabilimento;
import it.unisalento.distributori.factory.FactoryDao;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StabilimentoDaoImplTest {

	StabilimentoDao dao = FactoryDao.getIstance().getStabilimentoDao();
	
	@Test
	public void testCRUD() throws Exception {
		
		Integer id;
		Stabilimento stabilimento=new Stabilimento();
		
		//set
		stabilimento.setCitta("prova JUnit");
		stabilimento.setProvincia("PR");
		stabilimento.setProduttore(FactoryDao.getIstance().getProduttoreDao().get(1, Produttore.class));
		
		stabilimento.setId(dao.set(stabilimento));
		
		id=stabilimento.getId();
		
		assertTrue(stabilimento.getId()>0);
		
		//get (by ID)
		stabilimento=dao.get(id, Stabilimento.class);
		
		assertNotNull(stabilimento);
		assertEquals(id, stabilimento.getId());
		assertEquals("prova JUnit", stabilimento.getCitta());
		
		//update
		stabilimento=dao.get(id, Stabilimento.class);
		stabilimento.setCitta("prova JUnit updated");
		dao.update(stabilimento);
		stabilimento=dao.get(id, Stabilimento.class);
		
		assertEquals("prova JUnit updated", stabilimento.getCitta());
		
		//delete
		stabilimento=dao.get(id, Stabilimento.class);
		dao.delete(stabilimento);
		stabilimento=dao.get(id, Stabilimento.class);
		
		assertEquals(null, stabilimento);		
	}

	@Test
	public void testGetAll() throws Exception {
		List<Stabilimento> stabilimenti = dao.getAll(Stabilimento.class);
		
		assertNotNull(stabilimenti);
		assertTrue(stabilimenti.size()>0);
	}

	@Test
	public void testGetAllSortedBy() throws Exception {
		List<Stabilimento> stabilimenti = dao.getAllSortedBy(Stabilimento.class, "citta");
		String citta1;
		String citta2;
		boolean error = false;
		for(int i=1; i<stabilimenti.size() && !error; i++){
			citta1 = stabilimenti.get(i-1).getCitta();
			citta2 = stabilimenti.get(i).getCitta();
			if(citta1.compareToIgnoreCase(citta2)>0)
				error=true;
		}
		assertTrue(!error);
	}

}
