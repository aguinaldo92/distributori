/**
 * 
 */
package it.unisalento.distributori.daoimpl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unisalento.distributori.dao.DistributoreDao;
import it.unisalento.distributori.domain.Distributore;

/**
 * @author aguinaldo
 *
 */
public class DistributoreDaoImpl extends BaseDaoImpl<Distributore> implements DistributoreDao {


	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Distributore> getDistributoriByIdDipendenteSortedByStato(Integer idDipendente) {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			String hql = "from Distributore d where d.dipendente.id = :idDipendente";
			Query query = session.createQuery(hql);
			query.setInteger("idDipendente", idDipendente);
			ArrayList<Distributore> listDistributore = (ArrayList<Distributore>) query.list();
			tx.commit();
			return listDistributore;

		} finally {
			HibernateUtil.closeSession(session);
		}

	}

	@Override
	public Long getNumDistributoriNonOk() {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("select count(*) as num from Distributore as D where D.stato = 0 or D.stato = 1 or D.stato = 3");
			Long numDistributoriNonOk = (Long) query.uniqueResult();
			tx.commit();
			return numDistributoriNonOk;

		} finally {
			HibernateUtil.closeSession(session);
		}
	}
}
