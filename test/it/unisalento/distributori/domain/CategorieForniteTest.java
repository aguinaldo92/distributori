package it.unisalento.distributori.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import it.unisalento.distributori.factory.FactoryDao;

public class CategorieForniteTest {

	@Test
	public void testCategorieForniteCategoriaDistributore() throws Exception {
		
		Categoria categoria=FactoryDao.getIstance().getCategoriaDao().get(1, Categoria.class);
		Distributore distributore=FactoryDao.getIstance().getDistributoreDao().get(1, Distributore.class);
		
		CategorieFornite categ_fornite = new CategorieFornite(categoria, distributore);
		
		assertEquals(FactoryDao.getIstance().getCategoriaDao().get(1, Categoria.class).getNome(), categ_fornite.getCategoria().getNome());
	}

}
