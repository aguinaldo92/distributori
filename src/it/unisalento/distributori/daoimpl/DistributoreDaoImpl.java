/**
 * 
 */
package it.unisalento.distributori.daoimpl;

import java.util.ArrayList;

import org.hibernate.Query;

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
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			String hql = "from Distributore d where d.dipendente.id = :idDipendente";
			Query query = session.createQuery(hql);
			query.setInteger("idDipendente", idDipendente);
			ArrayList<Distributore> listDistributore = (ArrayList<Distributore>) query.list();
			tx.commit();
			return listDistributore;

		} finally {
			session.close();
		}

	}

	@Override
	public Long getNumDistributoriNonOk() {
		Long numDistributoriNonOk;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("select count(*) as num from Distributore as D where D.stato = 0 or D.stato = 1 or D.stato = 3");
			numDistributoriNonOk = (Long) query.uniqueResult();
			tx.commit();
			return numDistributoriNonOk;

		} finally {
			session.close();
		}
	}
}
