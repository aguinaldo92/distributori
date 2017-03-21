package it.unisalento.distributori.domain;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import it.unisalento.distributori.factory.FactoryDao;

public class RifornisceTest {

	@Test
	public void testRifornisceDipendenteDistributoreDate() throws Exception {
		Rifornisce rifornisce = new Rifornisce(FactoryDao.getIstance().getDipendenteDao().get(8, Dipendente.class), 
				FactoryDao.getIstance().getDistributoreDao().get(1, Distributore.class), new Date());
		
		assertNotNull(rifornisce);
		assertTrue(rifornisce.getDipendente().getPersonaId()==8);
	}

}
