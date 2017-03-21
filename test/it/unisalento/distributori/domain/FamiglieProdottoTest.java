package it.unisalento.distributori.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import it.unisalento.distributori.factory.FactoryDao;

public class FamiglieProdottoTest {

	@Test
	public void testFamiglieProdottoFamigliaProdotto() throws Exception {
		FamiglieProdotto fam_prod = new FamiglieProdotto(FactoryDao.getIstance().getFamigliaDao().get(1, Famiglia.class), 
				FactoryDao.getIstance().getProdottoDao().get(1, Prodotto.class));
		
		assertNotNull(fam_prod);
		assertEquals("Prodotti normali", fam_prod.getFamiglia().getNome());
		assertEquals((Integer)1, fam_prod.getProdotto().getId());
	}

}
