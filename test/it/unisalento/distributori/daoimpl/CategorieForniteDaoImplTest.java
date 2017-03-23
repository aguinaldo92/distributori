package it.unisalento.distributori.daoimpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import it.unisalento.distributori.dao.CategorieForniteDao;
import it.unisalento.distributori.domain.Categoria;
import it.unisalento.distributori.domain.CategorieFornite;
import it.unisalento.distributori.domain.Distributore;
import it.unisalento.distributori.factory.FactoryDao;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CategorieForniteDaoImplTest {
	
	CategorieForniteDao dao=FactoryDao.getIstance().getCategorieForniteDao();
	
	@Test
	public void testCRUD() throws Exception {
		
		Integer id;
		CategorieFornite categ_fornita=new CategorieFornite();
		
		//set
		Categoria categ = FactoryDao.getIstance().getCategoriaDao().get(1, Categoria.class);
		Distributore distributore = FactoryDao.getIstance().getDistributoreDao().get(1, Distributore.class);
		categ_fornita.setCategoria(categ);
		categ_fornita.setDistributore(distributore);
		categ_fornita.setId(dao.set(categ_fornita));
		
		id=categ_fornita.getId();
		
		assertTrue(categ_fornita.getId()>0);
		
		//get (by ID)
		categ_fornita=dao.get(id, CategorieFornite.class);
		
		assertNotNull(categ_fornita);
		assertEquals(id, categ_fornita.getId());
		
		//update
		categ_fornita=dao.get(id, CategorieFornite.class);
		categ=FactoryDao.getIstance().getCategoriaDao().get(2, Categoria.class);
		categ_fornita.setCategoria(categ);
		dao.update(categ_fornita);
		categ_fornita=dao.get(id, CategorieFornite.class);
		
		assertEquals((Integer)2, categ_fornita.getCategoria().getId());
		
		//delete
		categ_fornita=dao.get(id, CategorieFornite.class);
		dao.delete(categ_fornita);
		categ_fornita=dao.get(id, CategorieFornite.class);
		
		assertEquals(null, categ_fornita);		
	}

	@Test
	public void testGetAll() throws Exception {
		List<CategorieFornite> categ_fornite = dao.getAll(CategorieFornite.class);
		
		assertNotNull(categ_fornite);
		assertTrue(categ_fornite.size()>0);
	}

	@Test
	public void testGetAllSortedBy() throws Exception {//get all sorted by
		List<CategorieFornite> categ_fornite = dao.getAllSortedBy(CategorieFornite.class, "id");
		Integer id1;
		Integer id2;
		boolean error = false;
		for(int i=1; i<categ_fornite.size() && !error; i++){
			id1 = categ_fornite.get(i-1).getId();
			id2 = categ_fornite.get(i).getId();
			if(id1>id2)
				error=true;
		}
		assertTrue(!error);
	}
	
	@Test
	public void testGetCategorieForniteByDistributore() throws Exception {
		List<CategorieFornite> id_categs = FactoryDao.getIstance().getCategorieForniteDao().getCategorieForniteByDistributore(1);
		
		assertNotNull(id_categs);
		assertTrue(id_categs.size()>0);
	}

	@Test
	public void testGetNomiCategorieForniteByDistributore() throws Exception {
		List<String> nomi_categs = FactoryDao.getIstance().getCategorieForniteDao().getNomiCategorieForniteByDistributore(1);
		List<CategorieFornite> id_categs = FactoryDao.getIstance().getCategorieForniteDao().getCategorieForniteByDistributore(1);
		
		assertNotNull(nomi_categs);
		assertTrue((nomi_categs.size() + 1)==id_categs.size()); //+1 per la categoria "Generica" che nei nomi è esclusa
	}

}
