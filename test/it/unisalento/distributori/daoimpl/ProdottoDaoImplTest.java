package it.unisalento.distributori.daoimpl;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.junit.Test;

import it.unisalento.distributori.domain.CategorieFornite;
import it.unisalento.distributori.domain.Distributore;
import it.unisalento.distributori.domain.FamiglieProdotto;
import it.unisalento.distributori.domain.Prodotto;
import it.unisalento.distributori.factory.FactoryDao;

public class ProdottoDaoImplTest {

	@Test
	public void testGetAllProdotti() throws Exception {
		
		List<Prodotto> prodotti = FactoryDao.getIstance().getProdottoDao().getAllProdotti();
		
		boolean prodotto_vuoto = false;
		String nome1;
		String nome2;
		boolean sorted_prodotti = true;
		
		for(int i=1; i<prodotti.size() && sorted_prodotti && !prodotto_vuoto; i++){
			nome1 = prodotti.get(i-1).getNome();
			nome2 = prodotti.get(i).getNome();
			if(nome1.compareTo(nome2)>0)
				sorted_prodotti=false;
			if(nome1.compareToIgnoreCase("Vuoto")==0 || nome2.compareToIgnoreCase("Vuoto")==0)
				prodotto_vuoto=true;
		}
		
		assertTrue(sorted_prodotti);
		assertTrue(!prodotto_vuoto);
	}

	@Test
	public void testGetAllProdottiFiltrati() throws Exception {
		//primo test
		List<String> fam_IDs = Arrays.asList("1","2");
		List<String> categ_IDs = Arrays.asList("1", "3");
		
		List<Prodotto> prodotti = FactoryDao.getIstance().getProdottoDao().getAllProdottiFiltrati(fam_IDs, categ_IDs);
		
		boolean error_fam=false;
		boolean error_categ=false;
		FamiglieProdotto t_fam = new FamiglieProdotto();
		Iterator<FamiglieProdotto> iter_fams;
		
		for (int i=0; i<prodotti.size() && !error_categ && !error_fam; i++){
			if(prodotti.get(i).getCategoria().getId()!=1 && prodotti.get(i).getCategoria().getId()!=3)
				error_categ=true;
			iter_fams = prodotti.get(i).getFamiglieProdottos().iterator();
			while(iter_fams.hasNext() && !error_fam){
				t_fam = iter_fams.next();
				if(t_fam.getFamiglia().getId()!=1 && t_fam.getFamiglia().getId() != 2)
					error_fam=true;
			}
		}
		
		assertTrue(!error_fam);
		assertTrue(!error_categ);
	}

	@Test
	public void testGetProdottiCompatibiliByDistributore() throws Exception {
		
		Distributore distributore=FactoryDao.getIstance().getDistributoreDao().get(1, Distributore.class);
		
		List<Prodotto> prodotti_compatibili = FactoryDao.getIstance().getProdottoDao().getProdottiCompatibiliByDistributore(distributore.getId());
		
		boolean error=false;
		Prodotto prodotto = new Prodotto();
		CategorieFornite t_categ = new CategorieFornite();
		Iterator<CategorieFornite> iter_categs;
		
		for(int i=0; i<prodotti_compatibili.size() && !error; i++){
			prodotto=prodotti_compatibili.get(i);
			iter_categs = distributore.getCategorieFornites().iterator();
			error=true;
			while(iter_categs.hasNext() && error){
				t_categ = iter_categs.next();
				if(t_categ.getCategoria().getId()==prodotto.getCategoria().getId())
					error=false;
			}
		}
		
		assertTrue(!error);
	}

}
