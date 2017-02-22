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
public class DistributoreDaoImpl extends BaseDaoImpl<Distributore> implements DistributoreDao{
	private ArrayList<Distributore> listDistributore;
	private Session session;
	private Transaction tx;


	@Override
	public ArrayList<Distributore> getDistributoriByIdDipendenteSortedByStato(Integer idDipendente) {
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			String hql = "from Distributore d where d.dipendente.id = :idDipendente" ;
			Query query = session.createQuery(hql);
			query.setInteger("idDipendente", idDipendente);
			listDistributore = (ArrayList<Distributore>) query.list();
			tx.commit();
		}
		catch (Exception e) {
			System.out.println("Impossibile ottenere lista dei distributori dato un dipendente:");
			System.out.println(e.getLocalizedMessage());
			return null;
		}
		session.close();
		return listDistributore;


	}
}
