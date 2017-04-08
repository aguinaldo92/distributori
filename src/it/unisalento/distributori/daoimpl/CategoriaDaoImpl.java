/**
 * 
 */
package it.unisalento.distributori.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import it.unisalento.distributori.dao.CategoriaDao;
import it.unisalento.distributori.domain.Categoria;

/**
 * @author aguinaldo
 *
 */
public class CategoriaDaoImpl extends BaseDaoImpl<Categoria> implements CategoriaDao {

	@SuppressWarnings("unchecked")
	public List<Categoria> getAllCategorie(){
		try{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("from Categoria as C where C.nome!=:generica order by C.nome");
			query.setString("generica", "Generica");

			List<Categoria> list_categorie = new ArrayList<Categoria>();
			list_categorie=query.list();
			tx.commit();
			return list_categorie;
		} finally{
			session.close();
		}
	}

}
