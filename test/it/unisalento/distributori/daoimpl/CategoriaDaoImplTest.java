package it.unisalento.distributori.daoimpl;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import it.unisalento.distributori.domain.Categoria;
import it.unisalento.distributori.factory.FactoryDao;

public class CategoriaDaoImplTest {

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
