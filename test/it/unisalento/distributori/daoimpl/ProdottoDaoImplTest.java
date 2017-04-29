package it.unisalento.distributori.daoimpl;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import it.unisalento.distributori.dao.ProdottoDao;
import it.unisalento.distributori.domain.Categoria;
import it.unisalento.distributori.domain.CategorieFornite;
import it.unisalento.distributori.domain.Distributore;
import it.unisalento.distributori.domain.FamiglieProdotto;
import it.unisalento.distributori.domain.Prodotto;
import it.unisalento.distributori.domain.Stabilimento;
import it.unisalento.distributori.factory.FactoryDao;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProdottoDaoImplTest {

	ProdottoDao dao = FactoryDao.getIstance().getProdottoDao();
	
	@Test
	public void testCRUD() throws Exception {
		
		Integer id;
		Prodotto prodotto=new Prodotto();
		
		//set
		prodotto.setFoto("foto JUnit");
		prodotto.setIngredienti("basta un poco di zucchero");
		prodotto.setNome("prova");
		prodotto.setPreparazione("preparazione JUnit");
		prodotto.setPrezzo(BigDecimal.valueOf(0));
		prodotto.setScontoUtenti(BigDecimal.valueOf(0.10));
		prodotto.setStabilimento(FactoryDao.getIstance().getStabilimentoDao().get(1, Stabilimento.class));
		prodotto.setCategoria(FactoryDao.getIstance().getCategoriaDao().get(1, Categoria.class));
		
		prodotto.setId(dao.set(prodotto));
		id=prodotto.getId();
		
		assertTrue(prodotto.getId()>0);
		
		//get (by ID)
		prodotto=dao.get(id, Prodotto.class);
		
		assertNotNull(prodotto);
		assertEquals(id, prodotto.getId());
		
		//update
		prodotto=dao.get(id, Prodotto.class);
		prodotto.setNome("prova updated");
		dao.update(prodotto);
		prodotto=dao.get(id, Prodotto.class);
		
		assertEquals("prova updated", prodotto.getNome());
		
		//delete
		prodotto=dao.get(id, Prodotto.class);
		dao.delete(prodotto);
		prodotto=dao.get(id, Prodotto.class);
		
		assertEquals(null, prodotto);
	}
	
	@Test
	public void testGetAllProdotti() throws Exception {
		
		List<Prodotto> prodotti = dao.getAllProdotti();
		
		boolean prodotto_vuoto = false;
		String nome1;
		String nome2;
		boolean sorted_prodotti = true;
		
		for(int i=1; i<prodotti.size() && sorted_prodotti && !prodotto_vuoto; i++){
			nome1 = prodotti.get(i-1).getNome();
			nome2 = prodotti.get(i).getNome();
			if(nome1.compareToIgnoreCase(nome2)>0)
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
		List<String> categ_IDs = Arrays.asList("1","3");
		
		List<Prodotto> prodotti = dao.getAllProdottiFiltrati(fam_IDs, categ_IDs);
		
		boolean error_fam=true;
		boolean error_categ=false;
		FamiglieProdotto t_fam = new FamiglieProdotto();
		Iterator<FamiglieProdotto> iter_fams;
		
		for (int i=0; i<prodotti.size() && !error_categ; i++){
			if(prodotti.get(i).getCategoria().getId()!=1 && prodotti.get(i).getCategoria().getId()!=3)
				error_categ=true;
			
			iter_fams = prodotti.get(i).getFamiglieProdottos().iterator();
			while(iter_fams.hasNext()){
				t_fam = iter_fams.next();
				if(t_fam.getFamiglia().getId()==1 || t_fam.getFamiglia().getId() == 2)
					error_fam=false;
			}
			if(error_fam)
				break;
		}
		
		assertFalse(error_fam);
		assertFalse(error_categ);
	}

	@Test
	public void testGetProdottiCompatibiliByDistributore() throws Exception {
		
		Distributore distributore=FactoryDao.getIstance().getDistributoreDao().get(3, Distributore.class);
		
		List<Prodotto> prodotti_compatibili = dao.getProdottiCompatibiliByDistributore(distributore.getId());
		
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

	@Test
	public void testGetAll() throws Exception {
		List<Prodotto> prodotti = dao.getAll(Prodotto.class);
		
		assertNotNull(prodotti);
		assertTrue(prodotti.size()>0);
	}

	@Test
	public void testGetAllSortedBy() throws Exception {
		List<Prodotto> prodotti = dao.getAllSortedBy(Prodotto.class, "descrizione");
		String descr1;
		String descr2;
		boolean error = false;
		for(int i=1; i<prodotti.size() && !error; i++){
			descr1 = prodotti.get(i-1).getDescrizione();
			descr2 = prodotti.get(i).getDescrizione();
			if(descr1.compareToIgnoreCase(descr2)>0)
				error=true;
		}
		assertTrue(!error);
	}
	
	@Test
	public void testGetProdottoVuoto() throws Exception{
		Prodotto prodotto=dao.getProdottoVuoto();
		
		assertNotNull(prodotto);
		assertEquals("vuoto", prodotto.getNome());
	}

}
