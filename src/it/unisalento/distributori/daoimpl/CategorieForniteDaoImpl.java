/**
 * 
 */
package it.unisalento.distributori.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unisalento.distributori.dao.CategorieForniteDao;
import it.unisalento.distributori.domain.CategorieFornite;
import it.unisalento.distributori.util.HibernateUtil;

/**
 * @author aguinaldo
 *
 */
public class CategorieForniteDaoImpl extends BaseDaoImpl<CategorieFornite> implements CategorieForniteDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<CategorieFornite> getCategorieForniteByDistributore  (Integer idDistributore) {
		List<CategorieFornite> listCategorieFornite;
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			String hql = "from CategorieFornite CF inner join CF.categoria C where CF.distributore.id = :idDistributore order by C.nome" ;
			Query query = session.createQuery(hql);
			query.setInteger("idDistributore", idDistributore);
			listCategorieFornite = (List<CategorieFornite>) query.list();
			tx.commit();
			return listCategorieFornite;
		} finally{
			HibernateUtil.closeSession(session);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getNomiCategorieForniteByDistributore(Integer idDistributore) {
		List<String> listNomiCategorieFornite;
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			String hql = "Select C.nome from CategorieFornite CF inner join CF.categoria C where CF.distributore.id = :idDistributore and C.nome != 'Generica' order by C.nome" ;
			Query query = session.createQuery(hql);
			query.setInteger("idDistributore", idDistributore);
			listNomiCategorieFornite = (List<String>) query.list();
			tx.commit();
			return listNomiCategorieFornite;
			
		} finally{
			HibernateUtil.closeSession(session);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getIdsCategorieForniteByDistributore(Integer idDistributore) {
		List<Integer> listIdsCategorieFornite;
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			String hql = "Select C.id from CategorieFornite CF inner join CF.categoria C where CF.distributore.id = :idDistributore and C.nome != 'Generica' order by C.nome" ;
			Query query = session.createQuery(hql);
			query.setInteger("idDistributore", idDistributore);
			listIdsCategorieFornite = (List<Integer>) query.list();
			tx.commit();
			return listIdsCategorieFornite;
			
		} finally{
			HibernateUtil.closeSession(session);
		}
	}


}
