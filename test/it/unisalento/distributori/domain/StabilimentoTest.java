package it.unisalento.distributori.domain;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import it.unisalento.distributori.factory.FactoryDao;

public class StabilimentoTest {

	@Test
	public void testStabilimentoProduttore() throws Exception {
		Stabilimento stabilimento = new Stabilimento(FactoryDao.getIstance().getProduttoreDao().get(1, Produttore.class));
		
		assertNotNull(stabilimento);
		assertEquals("Ferrero", stabilimento.getProduttore().getNome());
	}

	@Test
	public void testStabilimentoProduttoreStringStringSet() throws Exception {
		
		Set<Prodotto> prodottos = new HashSet<Prodotto>(0);
		
		Stabilimento stabilimento = new Stabilimento(FactoryDao.getIstance().getProduttoreDao().get(1, Produttore.class), 
				"JUnit City", "MM", prodottos);
		
		assertNotNull(stabilimento);
		assertEquals("Ferrero", stabilimento.getProduttore().getNome());
		assertEquals("JUnit City", stabilimento.getCitta());
		assertTrue(stabilimento.getProdottos().size()==0);
	}

}
