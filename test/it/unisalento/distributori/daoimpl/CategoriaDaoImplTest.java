package it.unisalento.distributori.daoimpl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import it.unisalento.distributori.dao.CategoriaDao;
import it.unisalento.distributori.domain.Categoria;
import it.unisalento.distributori.factory.FactoryDao;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CategoriaDaoImplTest {
	
	CategoriaDao dao=FactoryDao.getIstance().getCategoriaDao();
	
	@Test
	public void testCRUD() throws Exception {
		
		Integer id;
		Categoria categoria=new Categoria();
		
		//set
		categoria.setNome("Test JUnit");
		
		categoria.setId(dao.set(categoria));
		
		id=categoria.getId();
		
		assertTrue(categoria.getId()>0);
		
		//get (by ID)
		categoria=dao.get(id, Categoria.class);
		
		assertNotNull(categoria);
		assertEquals(id, categoria.getId());
		assertEquals("Test JUnit", categoria.getNome());
		
		//update
		categoria=dao.get(id, Categoria.class);
		categoria.setNome("Test update JUnit");
		dao.update(categoria);
		categoria=dao.get(id, Categoria.class);
		
		assertEquals("Test update JUnit", categoria.getNome());
		
		//delete
		categoria=dao.get(id, Categoria.class);
		dao.delete(categoria);
		categoria=dao.get(id, Categoria.class);
		
		assertEquals(null, categoria);		
	}
	
	@Test
	public void testGetAll() throws Exception {
		List<Categoria> categorie = dao.getAll(Categoria.class);
		
		assertNotNull(categorie);
		assertTrue(categorie.size()>0);
	}

	@Test
	public void testGetAllSortedBy() throws Exception {//get all sorted by
		List<Categoria> categorie = dao.getAllSortedBy(Categoria.class, "nome");
		String nome1;
		String nome2;
		boolean error = false;
		for(int i=1; i<categorie.size() && !error; i++){
			nome1 = categorie.get(i-1).getNome();
			nome2 = categorie.get(i).getNome();
			if(nome1.compareTo(nome2)>0)
				error=true;
		}
		assertTrue(!error);
	}
	
	@Test
	public void testGetAllCategorie() throws Exception {
		List<Categoria> categorie = FactoryDao.getIstance().getCategoriaDao().getAllCategorie();
		
		boolean categ_Generica = false;
		String nome1;
		String nome2;
		boolean sorted_categ = true;
		
		for(int i=1; i<categorie.size() && sorted_categ && !categ_Generica; i++){
			nome1 = categorie.get(i-1).getNome();
			nome2 = categorie.get(i).getNome();
			if(nome1.compareTo(nome2)>0)
				sorted_categ=false;
			if(nome1.compareTo("Generica")==0 || nome2.compareTo("Generica")==0)
				categ_Generica=true;
		}
		
		assertTrue(sorted_categ);
		assertTrue(!categ_Generica);
	}

}
