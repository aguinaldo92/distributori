package it.unisalento.distributori.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import it.unisalento.distributori.domain.Categoria;
import it.unisalento.distributori.domain.Produttore;
import it.unisalento.distributori.domain.Stabilimento;
import it.unisalento.distributori.factory.FactoryDao;

public class ProdottoModelTest {

	ProdottoModel prodotto = new ProdottoModel();
	
	@Test
	public void testSetNome() throws Exception {
		prodotto.setNome("JUnit nome");
		assertEquals("JUnit nome", prodotto.getNome());
	}

	@Test
	public void testSetFoto() throws Exception {
		prodotto.setFoto("JUnit.jpg");
		assertEquals("JUnit.jpg", prodotto.getFoto());
	}

	@Test
	public void testSetDescrizione() throws Exception {
		prodotto.setDescrizione("JUnit descrizione");
		assertEquals("JUnit descrizione", prodotto.getDescrizione());
	}

	@Test
	public void testSetIngredienti() throws Exception {
		prodotto.setIngredienti("JUnit ingredienti");
		assertEquals("JUnit ingredienti", prodotto.getIngredienti());
	}

	@Test
	public void testSetPreparazione() throws Exception {
		prodotto.setPreparazione("JUnit Preparazione");
		assertEquals("JUnit Preparazione", prodotto.getPreparazione());
	}

	@Test
	public void testSetProduttore() throws Exception {
		prodotto.setProduttore(FactoryDao.getIstance().getProduttoreDao().get(1, Produttore.class));
		assertNotNull(prodotto.getProduttore());
		assertEquals("Ferrero", prodotto.getProduttore().getNome());
	}

	@Test
	public void testSetStabilimento() throws Exception {
		prodotto.setStabilimento(FactoryDao.getIstance().getStabilimentoDao().get(1, Stabilimento.class));
		assertNotNull(prodotto.getStabilimento());
		assertEquals("Civitavecchia", prodotto.getStabilimento().getCitta());
	}

	@Test
	public void testSetCategoria() throws Exception {
		prodotto.setCategoria(FactoryDao.getIstance().getCategoriaDao().get(1, Categoria.class));
		assertNotNull(prodotto.getCategoria());
		assertEquals("Bevande Calde", prodotto.getCategoria().getNome());
	}

	@Test
	public void testSetIDsfamiglieString() throws Exception {
		prodotto.setIDsfamiglie("3, 5, 7");
		assertTrue(prodotto.getIDsfamiglie().size()==3);
		assertEquals((Integer)3, prodotto.getIDsfamiglie().get(0));
		assertEquals((Integer)5, prodotto.getIDsfamiglie().get(1));
		assertEquals((Integer)7, prodotto.getIDsfamiglie().get(2));
	}
	
	@Test
	public void testSetIDsfamiglieListString() throws Exception {
		List<Integer> IDs = new ArrayList<Integer>();
		IDs.add(3); IDs.add(5); IDs.add(8);
		
		prodotto.setIDsfamiglie(IDs);
		assertTrue(prodotto.getIDsfamiglie().size()==3);
		assertEquals((Integer)3, prodotto.getIDsfamiglie().get(0));
		assertEquals((Integer)5, prodotto.getIDsfamiglie().get(1));
		assertEquals((Integer)8, prodotto.getIDsfamiglie().get(2));
	}

	@Test
	public void testSetPrezzo() throws Exception {
		prodotto.setPrezzo("4.80");;
		assertEquals("4.80", prodotto.getPrezzo());
	}

	@Test
	public void testSetSconto() throws Exception {
		prodotto.setSconto("20");
		assertEquals("20", prodotto.getSconto());
	}

}
