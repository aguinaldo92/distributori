package it.unisalento.distributori.daoimpl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import it.unisalento.distributori.domain.FamiglieProdotto;
import it.unisalento.distributori.domain.Prodotto;
import it.unisalento.distributori.factory.FactoryDao;

public class FamiglieProdottoDaoImplTest {

	@Test
	public void testGetFamiglieByProdotto() throws Exception {
		Prodotto prodotto=FactoryDao.getIstance().getProdottoDao().get(1, Prodotto.class);
		
		List<FamiglieProdotto> prod_families = FactoryDao.getIstance().getFamiglieProdottoDao().getFamiglieByProdotto(prodotto.getId());
		
		assertNotNull(prod_families);
		
		boolean error=false;
		for (int i=0; i<prod_families.size() && !error; i++){
			if (prod_families.get(i).getProdotto().getId()!=prodotto.getId())
				error=true;
		}
		
		assertTrue(!error);
	}

	@Test
	public void testDeleteProdottoFamilies() throws Exception {
		Prodotto prodotto=FactoryDao.getIstance().getProdottoDao().get(1, Prodotto.class);
		
		List<FamiglieProdotto> prod_families1 = FactoryDao.getIstance().getFamiglieProdottoDao().getFamiglieByProdotto(prodotto.getId());
		assertTrue(prod_families1.size()>0);
		
		FactoryDao.getIstance().getFamiglieProdottoDao().deleteProdottoFamilies(prodotto.getId());
		
		List<FamiglieProdotto> prod_families2 = FactoryDao.getIstance().getFamiglieProdottoDao().getFamiglieByProdotto(prodotto.getId());
		assertTrue(prod_families2.size()==0);
		
		//reset delle info testate
		FamiglieProdotto famiglia_prod = new FamiglieProdotto();
		for (int i=0; i<prod_families1.size(); i++){
			famiglia_prod=prod_families1.get(i);
			FactoryDao.getIstance().getFamiglieProdottoDao().set(famiglia_prod);
		}
	}

}
