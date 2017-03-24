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
	
	@Test
	public void testSetId() throws Exception {
		Rifornisce rifornisce = new Rifornisce();
		
		rifornisce.setId(2);
		
		assertEquals((Integer)2, rifornisce.getId());
	}

	@Test
	public void testSetDipendente() throws Exception {
		Rifornisce rifornisce = new Rifornisce();
		
		rifornisce.setDipendente(FactoryDao.getIstance().getDipendenteDao().get(8, Dipendente.class));
		
		assertNotNull(rifornisce.getDipendente());
		assertEquals("Salvatore", rifornisce.getDipendente().getPersona().getNome());
	}

	@Test
	public void testSetDistributore() throws Exception {
		Rifornisce rifornisce = new Rifornisce();
		
		rifornisce.setDistributore(FactoryDao.getIstance().getDistributoreDao().get(1, Distributore.class));
		
		assertNotNull(rifornisce.getDistributore());
		assertEquals((Integer)2, rifornisce.getDistributore().getNumScaffali());
	}

	@Test
	public void testSetData() throws Exception {
		Rifornisce rifornisce = new Rifornisce();
		
		Date data = new Date();
		
		rifornisce.setData(data);
		
		assertEquals(data, rifornisce.getData());
	}

	@Test
	public void testRifornisceDistributore() throws Exception {
		Rifornisce rifornisce = new Rifornisce(FactoryDao.getIstance().getDistributoreDao().get(1, Distributore.class));
		
		assertNotNull(rifornisce);
		assertNotNull(rifornisce.getDistributore());
		assertEquals((Integer)2, rifornisce.getDistributore().getNumScaffali());
	}

}
