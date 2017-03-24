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

	@Test
	public void testSetId() throws Exception {
		Manutiene manutiene = new Manutiene();
		
		manutiene.setId(3);
		
		assertEquals((Integer)3, manutiene.getId());
	}

	@Test
	public void testSetDipendente() throws Exception {
		Manutiene manutiene = new Manutiene();
		
		manutiene.setDipendente(FactoryDao.getIstance().getDipendenteDao().get(8, Dipendente.class));
		
		assertNotNull(manutiene.getDipendente());
		assertEquals("Salvatore", manutiene.getDipendente().getPersona().getNome());
	}

	@Test
	public void testSetDataInizio() throws Exception {
		Manutiene manutiene = new Manutiene();
		Date data = new Date();
		manutiene.setDataInizio(data);
		
		assertEquals(data, manutiene.getDataInizio());
	}

	@Test
	public void testSetDataFine() throws Exception {
		Manutiene manutiene = new Manutiene();
		Date data = new Date();
		manutiene.setDataFine(data);
		
		assertEquals(data, manutiene.getDataFine());
	}

	@Test
	public void testSetDistributore() throws Exception {
		Manutiene manutiene = new Manutiene();
		
		manutiene.setDistributore(FactoryDao.getIstance().getDistributoreDao().get(1, Distributore.class));
		
		assertNotNull(manutiene.getDistributore());
		assertEquals("Via Esempio", manutiene.getDistributore().getIndirizzo());
	}

}
