/**
 * 
 */
package it.unisalento.distributori.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unisalento.distributori.dao.CategorieForniteDao;
import it.unisalento.distributori.domain.CategorieFornite;

/**
 * @author aguinaldo
 *
 */
public class CategorieForniteDaoImpl extends BaseDaoImpl<CategorieFornite> implements CategorieForniteDao{
	private ArrayList<CategorieFornite> listCategorieFornite;
	private ArrayList<String> listNomiCategorieFornite;
	
	private Session session;
	private Transaction tx;

	@Override
	public List<CategorieFornite> GetCategorieForniteByDistributore  (Integer idDistributore) {
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			String hql = "from CategorieFornite CF inner join CF.categoria C where CF.distributore.id = :idDistributore order by C.nome" ;
			Query query = session.createQuery(hql);
			query.setInteger("idDistributore", idDistributore);
			listCategorieFornite = (ArrayList<CategorieFornite>) query.list();
			tx.commit();
		}
		catch (Exception e) {
			System.out.println("Impossibile ottenere lista delle categorie fornite dato un distributore:");
			System.out.println(e.getLocalizedMessage());
			return null;
		}
		session.close();
		return listCategorieFornite;
	}

	@Override
	public ArrayList<String> GetNomiCategorieForniteByDistributore(Integer idDistributore) {
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			String hql = "Select C.nome from CategorieFornite CF inner join CF.categoria C where CF.distributore.id = :idDistributore and C.nome != 'Generica' order by C.nome" ;
			Query query = session.createQuery(hql);
			query.setInteger("idDistributore", idDistributore);
			listNomiCategorieFornite = (ArrayList<String>) query.list();
			tx.commit();
		}
		catch (Exception e) {
			System.out.println("Impossibile ottenere lista dei nomi delle categorie fornite dato un distributore:");
			System.out.println(e.getLocalizedMessage());
			return null;
		}
		session.close();
		return listNomiCategorieFornite;
	}


}
