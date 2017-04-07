/**
 * 
 */
package it.unisalento.distributori.daoimpl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unisalento.distributori.dao.ManutieneDao;
import it.unisalento.distributori.domain.Manutiene;

/**
 * @author aguinaldo
 *
 */
public class ManutieneDaoImpl extends BaseDaoImpl<Manutiene> implements ManutieneDao {
	private Session session;
	private Transaction tx;
	
	@Override
	public Manutiene getManutenzionePendenteByDistributore(int idDistributore) {
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("select M	from Manutiene as M inner join M.distributore as D where D.id=:idDistributore and M.dataFine IS NULL");
			query.setInteger("idDistributore", idDistributore);
			Manutiene manut = (Manutiene) query.uniqueResult();
			tx.commit();
			return manut;
		}finally {
			session.close();
		}
	}

}
