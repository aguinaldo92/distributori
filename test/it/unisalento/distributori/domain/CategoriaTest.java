package it.unisalento.distributori.domain;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import it.unisalento.distributori.factory.FactoryDao;

public class CategoriaTest {

	@Test
	public void testCategoriaString() throws Exception {
		Categoria categ = new Categoria("prova JUnit");
		assertEquals("prova JUnit",categ.getNome());
	}

	@Test
	public void testCategoriaStringSetSet() throws Exception {
		Set<Prodotto> prodottos = new HashSet<Prodotto>();
		Set<CategorieFornite> categorieFornites = new HashSet<CategorieFornite>();
		
		prodottos.add(FactoryDao.getIstance().getProdottoDao().get(1, Prodotto.class));
		prodottos.add(FactoryDao.getIstance().getProdottoDao().get(2, Prodotto.class));
		categorieFornites.add(FactoryDao.getIstance().getCategorieForniteDao().get(1, CategorieFornite.class));
		
		Categoria categ = new Categoria("prova JUnit",prodottos, categorieFornites);
		assertEquals("prova JUnit",categ.getNome());
		assertTrue(categ.getProdottos().size()==2);
		assertTrue(categ.getCategorieFornites().size()==1);
	}

}
