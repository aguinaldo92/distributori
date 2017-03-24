package it.unisalento.distributori.util;

import org.junit.Test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

public class AddressTranslationTest {

	@Test
	public void testGetLatLonFromAddress() throws Exception {
		List<BigDecimal> coord = AddressTranslation.getLatLonFromAddress("Campus Universitario Ecotekne, Via Lecce-Monteroni, Monteroni di Lecce, LE");
		
		assertNotNull(coord);
		assertTrue(coord.size()==2);
		assertEquals(BigDecimal.valueOf(40.3349463), coord.get(0));
		assertEquals(BigDecimal.valueOf(18.1189842), coord.get(1));
	}

}
