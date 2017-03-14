package it.unisalento.distributori.daoimpl;

import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import it.unisalento.distributori.domain.Distributore;
import it.unisalento.distributori.factory.FactoryDao;

public class DistributoreDaoImplTest {

	@Test
	public void testGetDistributoriByIdDipendenteSortedByStato() throws Exception {
		List<Distributore> distributori = FactoryDao.getIstance().getDistributoreDao().getDistributoriByIdDipendenteSortedByStato(9);
		
		assertNotNull(distributori);
		
	}

	@Test
	public void testGetNumDistributoriNonOk() throws Exception {
		Long n_distrib_noOK=FactoryDao.getIstance().getDistributoreDao().getNumDistributoriNonOk();
		
		assertTrue(n_distrib_noOK>=0);
	}

}
