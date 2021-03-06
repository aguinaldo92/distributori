/**
 * 
 */
package it.unisalento.distributori.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unisalento.distributori.dao.CategoriaDao;
import it.unisalento.distributori.domain.Categoria;
import it.unisalento.distributori.util.HibernateUtil;

/**
 * @author aguinaldo
 *
 */
public class CategoriaDaoImpl extends BaseDaoImpl<Categoria> implements CategoriaDao {

	@SuppressWarnings("unchecked")
	public List<Categoria> getAllCategorie(){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from Categoria as C where C.nome!=:generica order by C.nome");
			query.setString("generica", "Generica");

			List<Categoria> list_categorie = new ArrayList<Categoria>();
			list_categorie=query.list();
			tx.commit();
			return list_categorie;
		} finally{
			HibernateUtil.closeSession(session);
		}
	}

}
