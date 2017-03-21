package it.unisalento.distributori.domain;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import it.unisalento.distributori.factory.FactoryDao;

public class ProdottoTest {

	@Test
	public void testProdottoCategoriaStabilimentoStringBigDecimal() throws Exception {
		Prodotto prodotto = new Prodotto(FactoryDao.getIstance().getCategoriaDao().get(1, Categoria.class), 
				FactoryDao.getIstance().getStabilimentoDao().get(1, Stabilimento.class), 
				"Prodotto JUnit", BigDecimal.valueOf(3.20));
		
		assertNotNull(prodotto);
		assertEquals("Prodotto JUnit", prodotto.getNome());
		assertEquals("Civitavecchia", prodotto.getStabilimento().getCitta());
	}

	@Test
	public void testProdottoCategoriaStabilimentoStringStringBigDecimalBigDecimalStringStringStringSetSetSet()
			throws Exception {
		Set<FamiglieProdotto> famiglieProdottos = new HashSet<FamiglieProdotto>(0);
		Set<ProdottiErogati> prodottiErogatis = new HashSet<ProdottiErogati>();
		Set<Acquista> acquistas = new HashSet<Acquista>(0);
		
		prodottiErogatis.add(FactoryDao.getIstance().getProdottiErogatiDao().get(1, ProdottiErogati.class));
		
		Prodotto prodotto = new Prodotto(FactoryDao.getIstance().getCategoriaDao().get(1, Categoria.class), 
				FactoryDao.getIstance().getStabilimentoDao().get(1, Stabilimento.class), 
				"Prodotto JUnit", "Prova JUnit", BigDecimal.valueOf(3.20), BigDecimal.valueOf(0.30), 
				"Foto JUnit", null, null, famiglieProdottos, prodottiErogatis, acquistas);
		
		assertNotNull(prodotto);
		assertTrue(prodotto.getFamiglieProdottos().size()==0);
		assertTrue(prodotto.getProdottiErogatis().size()==1);
		assertEquals("Prodotto JUnit", prodotto.getNome());
	
	}

}
