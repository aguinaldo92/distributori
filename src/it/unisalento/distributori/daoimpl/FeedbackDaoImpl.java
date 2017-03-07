/**
 * 
 */
package it.unisalento.distributori.daoimpl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unisalento.distributori.dao.FeedbackDao;
import it.unisalento.distributori.domain.Feedback;
import it.unisalento.distributori.domain.Persona;

/**
 * @author aguinaldo
 *
 */
public class FeedbackDaoImpl extends BaseDaoImpl<Feedback> implements FeedbackDao {
	private Session session;
	private Transaction tx;

	@Override
	public Long getNumMessaggiNonLetti() {
		Long numMessaggiNonLetti;
		try{
			session = sf.openSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("select count(*) from Feedback as F where F.letto = 0");
			numMessaggiNonLetti = (Long) query.uniqueResult();
			tx.commit();
			session.close();
			return numMessaggiNonLetti;
		} catch(Exception e){
			System.out.println("IMpossibile ottenre numero messaggi non letti: "+e.getMessage());
			return null;
		}

	}

}
