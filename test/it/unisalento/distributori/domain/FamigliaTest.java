package it.unisalento.distributori.domain;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import it.unisalento.distributori.factory.FactoryDao;

public class FamigliaTest {

	@Test
	public void testFamigliaString() throws Exception {
		Famiglia famiglia = new Famiglia("Prova JUnit");
		
		assertNotNull(famiglia);
		assertEquals("Prova JUnit", famiglia.getNome());
	}

	@Test
	public void testFamigliaStringSet() throws Exception {
		Set<FamiglieProdotto> famiglieProdottos = new HashSet<FamiglieProdotto>();
		famiglieProdottos.add(FactoryDao.getIstance().getFamiglieProdottoDao().get(1, FamiglieProdotto.class));
		famiglieProdottos.add(FactoryDao.getIstance().getFamiglieProdottoDao().get(3, FamiglieProdotto.class));
		
		Famiglia famiglia = new Famiglia("Prova JUnit", famiglieProdottos);
		
		assertNotNull(famiglia);
		assertTrue(famiglia.getFamiglieProdottos().size()==2);
		assertEquals("Prova JUnit", famiglia.getNome());
	}

}
