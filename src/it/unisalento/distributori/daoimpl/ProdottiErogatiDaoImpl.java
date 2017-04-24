/**
 * 
 */
package it.unisalento.distributori.daoimpl;

import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unisalento.distributori.dao.ProdottiErogatiDao;
import it.unisalento.distributori.domain.ProdottiErogati;

/**
 * @author aguinaldo
 * 
 *
 */
public class ProdottiErogatiDaoImpl extends BaseDaoImpl<ProdottiErogati> implements ProdottiErogatiDao{

	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<ProdottiErogati> getProdottiScarseggiantiByDistributore(Integer idDistributore) {
		Session session = null;
		try {
			// se cambi la quantita minima, cambiala anche nel trigger sul database.
			Integer quantitaMinima = 5;
			session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			String hql = "select PE from ProdottiErogati PE inner join PE.prodotto as P where PE.distributore.id = :idDistributore and P.nome != 'vuoto' and PE.quantita <= :quantitaMinima order by PE.quantita asc" ;
			Query query = session.createQuery(hql);
			query.setInteger("idDistributore", idDistributore);
			query.setInteger("quantitaMinima", quantitaMinima);
			ArrayList<ProdottiErogati> listProdottiErogatiScarseggianti = (ArrayList<ProdottiErogati>) query.list();
			tx.commit();
			
			return listProdottiErogatiScarseggianti;
			
		} finally{
			HibernateUtil.closeSession(session);
		}
	}

	@Override
	public ArrayList<ProdottiErogati> getProdottiErogatiByDistributoreSortedByScaffalePosto(Integer idDistributore) {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			String hql = "from ProdottiErogati PE where PE.distributore.id = :idDistributore order by PE.scaffale, PE.posto asc" ;
			Query query = session.createQuery(hql);
			query.setInteger("idDistributore", idDistributore);
			@SuppressWarnings("unchecked")
			ArrayList<ProdottiErogati> listProdottiErogati = (ArrayList<ProdottiErogati>) query.list();
			tx.commit();
			
			return listProdottiErogati;
			
		}   finally {
			HibernateUtil.closeSession(session);
		}
	}

}
