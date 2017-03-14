package it.unisalento.distributori.daoimpl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import it.unisalento.distributori.domain.CategorieFornite;
import it.unisalento.distributori.factory.FactoryDao;

public class CategorieForniteDaoImplTest {
	
	@Test
	public void testGetCategorieForniteByDistributore() throws Exception {
		List<CategorieFornite> id_categs = FactoryDao.getIstance().getCategorieForniteDao().GetCategorieForniteByDistributore(1);
		
		assertNotNull(id_categs);
		assertTrue(id_categs.size()>0);
	}

	@Test
	public void testGetNomiCategorieForniteByDistributore() throws Exception {
		List<String> nomi_categs = FactoryDao.getIstance().getCategorieForniteDao().GetNomiCategorieForniteByDistributore(1);
		List<CategorieFornite> id_categs = FactoryDao.getIstance().getCategorieForniteDao().GetCategorieForniteByDistributore(1);
		
		assertNotNull(nomi_categs);
		assertTrue((nomi_categs.size() + 1)==id_categs.size()); //+1 per la categoria "Generica" che nei nomi è esclusa
	}

}
