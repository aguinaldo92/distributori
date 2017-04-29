package it.unisalento.distributori.domain;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;

import it.unisalento.distributori.factory.FactoryDao;

public class AcquistaTest {

	Acquista acq = new Acquista();

	@Test
	public void testAcquistaProdotto() throws Exception {
		Acquista acq = new Acquista(FactoryDao.getIstance().getProdottoDao().get(1, Prodotto.class));
		
		assertNotNull(acq);
		assertEquals((Integer)1, acq.getProdotto().getId());
	}

	@Test
	public void testAcquistaDistributorePersonaProdottoDateIntegerBigDecimal() throws Exception {
		Date data=new Date();
		Acquista acq = new Acquista(FactoryDao.getIstance().getDistributoreDao().get(3, Distributore.class), 
				FactoryDao.getIstance().getPersonaDao().get(1, Persona.class), 
				FactoryDao.getIstance().getProdottoDao().get(3, Prodotto.class), 
				data, 3, BigDecimal.valueOf(12.40));
		
		assertNotNull(acq);
		assertEquals((Integer)3, acq.getDistributore().getId());
		assertEquals((Integer)1, acq.getPersona().getId());
		assertEquals((Integer)3, acq.getProdotto().getId());
		assertEquals((Integer)3, acq.getQuantita());
		assertEquals(BigDecimal.valueOf(12.40), acq.getTotaleSpesa());
		assertEquals(data, acq.getData());
	}

	@Test
	public void testSetId() throws Exception {
		acq.setId(2);
		assertEquals((Integer)2, acq.getId());
	}

	@Test
	public void testSetDistributore() throws Exception {
		acq.setDistributore(FactoryDao.getIstance().getDistributoreDao().get(3, Distributore.class));
		assertEquals((Integer)3, acq.getDistributore().getId());
		
	}

	@Test
	public void testSetPersona() throws Exception {
		acq.setPersona(FactoryDao.getIstance().getPersonaDao().get(1, Persona.class));
		assertEquals((Integer)1, acq.getPersona().getId());
	}

	@Test
	public void testSetProdotto() throws Exception {
		acq.setProdotto(FactoryDao.getIstance().getProdottoDao().get(3, Prodotto.class));
		assertEquals((Integer)3, acq.getProdotto().getId());
	}

	@Test
	public void testSetData() throws Exception {
		Date data=new Date();
		acq.setData(data);
		assertEquals(data, acq.getData());
	}

	@Test
	public void testSetQuantita() throws Exception {
		acq.setQuantita(50);
		assertEquals((Integer)50, acq.getQuantita());
	}

	@Test
	public void testSetTotaleSpesa() throws Exception {
		acq.setTotaleSpesa(BigDecimal.valueOf(9.20));
		assertEquals(BigDecimal.valueOf(9.20), acq.getTotaleSpesa());
	}

}
