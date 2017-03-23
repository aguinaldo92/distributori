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
	private Session session;
	private Transaction tx;

	@Override
	public List<CategorieFornite> getCategorieForniteByDistributore  (Integer idDistributore) {
		List<CategorieFornite> listCategorieFornite;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			String hql = "from CategorieFornite CF inner join CF.categoria C where CF.distributore.id = :idDistributore order by C.nome" ;
			Query query = session.createQuery(hql);
			query.setInteger("idDistributore", idDistributore);
			listCategorieFornite = (List<CategorieFornite>) query.list();
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
	public List<String> getNomiCategorieForniteByDistributore(Integer idDistributore) {
		List<String> listNomiCategorieFornite;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			String hql = "Select C.nome from CategorieFornite CF inner join CF.categoria C where CF.distributore.id = :idDistributore and C.nome != 'Generica' order by C.nome" ;
			Query query = session.createQuery(hql);
			query.setInteger("idDistributore", idDistributore);
			listNomiCategorieFornite = (List<String>) query.list();
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

	@Override
	public List<Integer> getIdsCategorieForniteByDistributore(Integer idDistributore) {
		List<Integer> listIdsCategorieFornite;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			String hql = "Select C.id from CategorieFornite CF inner join CF.categoria C where CF.distributore.id = :idDistributore and C.nome != 'Generica' order by C.nome" ;
			Query query = session.createQuery(hql);
			query.setInteger("idDistributore", idDistributore);
			listIdsCategorieFornite = (List<Integer>) query.list();
			tx.commit();
		}
		catch (Exception e) {
			System.out.println("Impossibile ottenere lista degli ids delle categorie fornite dato un distributore:");
			System.out.println(e.getLocalizedMessage());
			return null;
		} //TODO: finally session.close();
		session.close();
		return listIdsCategorieFornite;
	}


}
