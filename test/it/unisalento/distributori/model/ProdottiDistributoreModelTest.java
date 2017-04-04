package it.unisalento.distributori.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProdottiDistributoreModelTest {

	ProdottiDistributoreModel distrib_detail = new ProdottiDistributoreModel();
	
	@Test
	public void testSetIdProdotto() throws Exception {
		distrib_detail.setIdProdotto(2);
		assertEquals((Integer)2, distrib_detail.getIdProdotto());
	}

	@Test
	public void testSetIdProdottoErogato() throws Exception {
		distrib_detail.setIdProdottoErogato(2);
		assertEquals((Integer)2, distrib_detail.getIdProdottoErogato());
	}

	@Test
	public void testSetNomeProdottoErogato() throws Exception {
		distrib_detail.setNomeProdottoErogato("JUnit");
		assertEquals("JUnit", distrib_detail.getNomeProdottoErogato());
	}

	@Test
	public void testSetQuantita() throws Exception {
		distrib_detail.setQuantita(10);
		assertEquals((Integer)10, distrib_detail.getQuantita());
	}

}
