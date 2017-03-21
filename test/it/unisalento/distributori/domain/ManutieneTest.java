package it.unisalento.distributori.domain;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import it.unisalento.distributori.factory.FactoryDao;

public class ManutieneTest {

	@Test
	public void testManutieneDistributore() throws Exception {
		Manutiene manutiene = new Manutiene(FactoryDao.getIstance().getDistributoreDao().get(1, Distributore.class));
		
		assertNotNull(manutiene);
		assertEquals((Integer)1, manutiene.getDistributore().getId());
	}

	@Test
	public void testManutieneDipendenteDistributoreDateDate() throws Exception {
		Manutiene manutiene = new Manutiene(FactoryDao.getIstance().getDipendenteDao().get(9, Dipendente.class), 
				FactoryDao.getIstance().getDistributoreDao().get(1, Distributore.class), 
				new Date(), null);
		
		assertNotNull(manutiene);
		assertEquals("Licastro",manutiene.getDipendente().getPersona().getCognome());
		assertEquals((Integer)1, manutiene.getDistributore().getId());
	}

}
