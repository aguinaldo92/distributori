package it.unisalento.distributori.model;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Test;

import it.unisalento.distributori.domain.CategorieFornite;
import it.unisalento.distributori.domain.Persona;
import it.unisalento.distributori.domain.ProdottiErogati;
import it.unisalento.distributori.factory.FactoryDao;

public class DistributoreModelTest {

	DistributoreModel distributore = new DistributoreModel();
	
	@Test
	public void testSetId() throws Exception {
		distributore.setId(1);
		assertEquals((Integer)1, distributore.getId());
	}

	@Test
	public void testSetStato() throws Exception {
		distributore.setStato(0);
		assertEquals((Integer)0, distributore.getStato());
	}

	@Test
	public void testSetIndirizzo() throws Exception {
		distributore.setIndirizzo("Via JUnit, Test");
		assertEquals("Via JUnit, Test", distributore.getIndirizzo());
	}

	@Test
	public void testSetVia() throws Exception {
		distributore.setVia("Via JUnit");
		assertEquals("Via JUnit", distributore.getVia());
	}

	@Test
	public void testSetCivico() throws Exception {
		distributore.setCivico("10");
		assertEquals((String)"10", distributore.getCivico());
	}

	@Test
	public void testSetCitta() throws Exception {
		distributore.setCitta("JUnit City");
		assertEquals("JUnit City", distributore.getCitta());
	}

	@Test
	public void testSetProvincia() throws Exception {
		distributore.setProvincia("JU");
		assertEquals("JU", distributore.getProvincia());
	}

	@Test
	public void testSetPosizioneEdificio() throws Exception {
		distributore.setPosizioneEdificio("PosizioneJUnit");
		assertEquals("PosizioneJUnit", distributore.getPosizioneEdificio());
	}

	@Test
	public void testSetDipendente() throws Exception {
		distributore.setDipendente(FactoryDao.getIstance().getPersonaDao().get(8, Persona.class));
		
		assertNotNull(distributore.getDipendente());
		assertEquals("Salvatore", distributore.getDipendente().getNome());
	}

	@Test
	public void testSetCategorieFornite() throws Exception {
		ArrayList<CategorieFornite> categs = new ArrayList<CategorieFornite>();
		
		categs.add(FactoryDao.getIstance().getCategorieForniteDao().get(1, CategorieFornite.class));
		
		distributore.setCategorieFornite(categs);
		assertNotNull(distributore.getCategorieFornite());
		assertTrue(distributore.getCategorieFornite().size()==1);
	}

	@Test
	public void testSetProdottiForniti() throws Exception {
		ArrayList<ProdottiErogati> prods = new ArrayList<ProdottiErogati>();
		
		prods.add(FactoryDao.getIstance().getProdottiErogatiDao().get(1, ProdottiErogati.class));
		
		distributore.setProdottiForniti(prods);
		assertNotNull(distributore.getProdottiForniti());
		assertTrue(distributore.getProdottiForniti().size()==1);
	}

	@Test
	public void testSetNumScaffali() throws Exception {
		distributore.setNumScaffali("10");
		assertEquals((String)"10", distributore.getNumScaffali());
	}

	@Test
	public void testSetNumPosti() throws Exception {
		distributore.setNumPosti("10");
		assertEquals((String)"10", distributore.getNumPosti());
	}

	@Test
	public void testSetLat() throws Exception {
		distributore.setLat(BigDecimal.valueOf(90));
		assertEquals(BigDecimal.valueOf(90), distributore.getLat());
	}

	@Test
	public void testSetLon() throws Exception {
		distributore.setLon(BigDecimal.valueOf(70));
		assertEquals(BigDecimal.valueOf(70), distributore.getLon());
	}

	@Test
	public void testSetIdDipendente() throws Exception {
		distributore.setIdDipendente(1);
		assertEquals((Integer)1, distributore.getIdDipendente());
	}

	

}
