package it.unisalento.distributori.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import it.unisalento.distributori.factory.FactoryDao;

public class ProdottiErogatiTest {

	@Test
	public void testProdottiErogatiDistributoreProdotto() throws Exception {
		ProdottiErogati prod_erog = new ProdottiErogati(FactoryDao.getIstance().getDistributoreDao().get(3, Distributore.class), 
				FactoryDao.getIstance().getProdottoDao().get(3, Prodotto.class));
		
		assertNotNull(prod_erog);
		assertEquals("Caffè", prod_erog.getProdotto().getNome());
		assertEquals((Integer)3, prod_erog.getDistributore().getId());
	}

	@Test
	public void testProdottiErogatiDistributoreProdottoIntegerIntegerInteger() throws Exception {
		ProdottiErogati prod_erog = new ProdottiErogati(FactoryDao.getIstance().getDistributoreDao().get(1, Distributore.class), 
				FactoryDao.getIstance().getProdottoDao().get(1, Prodotto.class), 1, 2, 30);
		
		assertNotNull(prod_erog);
		assertEquals("Dolce merendina per colazione energetica", prod_erog.getProdotto().getDescrizione());
		assertEquals((Integer)30, prod_erog.getQuantita());
	}

}
