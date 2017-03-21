package it.unisalento.distributori.domain;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import it.unisalento.distributori.factory.FactoryDao;

public class ProduttoreTest {

	@Test
	public void testProduttoreStringStringSet() throws Exception {
		
		Set<Stabilimento> stabilimentos = new HashSet<Stabilimento>();
		
		stabilimentos.add(FactoryDao.getIstance().getStabilimentoDao().get(1, Stabilimento.class));
		
		Produttore produttore = new Produttore("JUnit test", "sede prova", stabilimentos);
		
		assertNotNull(produttore);
		assertTrue(produttore.getStabilimentos().size()==1);
		assertEquals("JUnit test", produttore.getNome());
	}

}
