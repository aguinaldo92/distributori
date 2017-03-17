package it.unisalento.distributori.daoimpl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import it.unisalento.distributori.dao.StabilimentoDao;
import it.unisalento.distributori.domain.Stabilimento;
import it.unisalento.distributori.factory.FactoryDao;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StabilimentoDaoImplTest {

	StabilimentoDao dao = FactoryDao.getIstance().getStabilimentoDao();
	
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

	@Test
	public void testUpdate() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testDelete() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

}
