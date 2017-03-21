package it.unisalento.distributori.domain;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import it.unisalento.distributori.factory.FactoryDao;

public class DistributoreTest {

	@Test
	public void testDistributoreBigDecimalBigDecimal() throws Exception {
		Distributore distributore = new Distributore(BigDecimal.valueOf(90), BigDecimal.valueOf(80));
		assertNotNull(distributore);
		assertEquals(BigDecimal.valueOf(90), distributore.getLat());
		assertEquals(BigDecimal.valueOf(80), distributore.getLon());
	}

	@Test
	public void testDistributoreDipendenteBigDecimalBigDecimalStringStringIntegerIntegerIntegerSetSetSetSetSet()
			throws Exception {
		
		Set<Acquista> acquistas = new HashSet<Acquista>();
		Set<CategorieFornite> categorieFornites = new HashSet<CategorieFornite>();
		Set<ProdottiErogati> prodottiErogatis = new HashSet<ProdottiErogati>();
		Set<Manutiene> manutienes = new HashSet<Manutiene>(0);
		Set<Rifornisce> rifornisces = new HashSet<Rifornisce>(0);
		
		acquistas.add(new Acquista(FactoryDao.getIstance().getProdottoDao().get(1, Prodotto.class)));
		categorieFornites.add(FactoryDao.getIstance().getCategorieForniteDao().get(1, CategorieFornite.class));
		categorieFornites.add(FactoryDao.getIstance().getCategorieForniteDao().get(2, CategorieFornite.class));
		prodottiErogatis.add(FactoryDao.getIstance().getProdottiErogatiDao().get(1, ProdottiErogati.class));
		prodottiErogatis.add(FactoryDao.getIstance().getProdottiErogatiDao().get(5, ProdottiErogati.class));
		
		Distributore distributore = new Distributore(FactoryDao.getIstance().getDipendenteDao().get(8, Dipendente.class), 
				BigDecimal.valueOf(80), BigDecimal.valueOf(90), "Indirizzo di prova JUnit", "Piano 3", 
				0, 10, 5, acquistas, categorieFornites, prodottiErogatis, manutienes, rifornisces);
		
		assertNotNull(distributore);
		assertEquals("Salvatore", distributore.getDipendente().getPersona().getNome());
		assertTrue(distributore.getAcquistas().size()==1);
		assertTrue(distributore.getCategorieFornites().size()==2);
		assertTrue(distributore.getProdottiErogatis().size()==2);
		assertEquals((Integer)10, distributore.getNumScaffali());
		assertEquals(BigDecimal.valueOf(90), distributore.getLon());
	}

}
