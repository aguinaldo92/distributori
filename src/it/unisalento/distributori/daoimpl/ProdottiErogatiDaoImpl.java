/**
 * 
 */
package it.unisalento.distributori.daoimpl;

import java.util.ArrayList;
import org.hibernate.Query;

import it.unisalento.distributori.dao.ProdottiErogatiDao;
import it.unisalento.distributori.domain.ProdottiErogati;

/**
 * @author aguinaldo
 * 
 *
 */
public class ProdottiErogatiDaoImpl extends BaseDaoImpl<ProdottiErogati> implements ProdottiErogatiDao{

	/*
	 * Il metodo fornisce l'elenco dei prodotti erogati da un singolo distributore (che appartiene al dipendente che lo sta visualizzando)
	 * che sono sotto la soglia minima di quantità da erogare, ordinati per la quantità stessa. Questa lista servirà a comporre la lista
	 * di tutti i distributori con informazioni sul distributore e sui prodotti che esso eroga che stanno terminando (per la "dashboard" del dipendente)
	 * (non-Javadoc)
	 * @see it.unisalento.distributori.dao.ProdottiErogatiDao#GetProdottiScarseggiantiByDistributore(java.lang.Integer, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<ProdottiErogati> getProdottiScarseggiantiByDistributore(Integer idDistributore,
			Integer quantitaMinima) {
		try {
			ArrayList<ProdottiErogati> listProdottiErogatiScarseggianti;
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			String hql = "select PE from ProdottiErogati PE inner join PE.prodotto as P where PE.distributore.id = :idDistributore and P.nome != 'vuoto' and PE.quantita < :quantitaMinima order by PE.quantita asc" ;
			Query query = session.createQuery(hql);
			query.setInteger("idDistributore", idDistributore);
			query.setInteger("quantitaMinima", quantitaMinima);
			listProdottiErogatiScarseggianti = (ArrayList<ProdottiErogati>) query.list();
			tx.commit();

			return listProdottiErogatiScarseggianti;
		} finally{
			session.close();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<ProdottiErogati> getProdottiScarseggiantiByDistributore(Integer idDistributore) {
		try {
			Integer quantitaMinima = 5;
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			String hql = "select PE from ProdottiErogati PE inner join PE.prodotto as P where PE.distributore.id = :idDistributore and P.nome != 'vuoto' and PE.quantita < :quantitaMinima order by PE.quantita asc" ;
			Query query = session.createQuery(hql);
			query.setInteger("idDistributore", idDistributore);
			query.setInteger("quantitaMinima", quantitaMinima);
			ArrayList<ProdottiErogati> listProdottiErogatiScarseggianti = (ArrayList<ProdottiErogati>) query.list();
			tx.commit();
			
			return listProdottiErogatiScarseggianti;
			
		} finally{
			session.close();
		}
	}

	@Override
	public ArrayList<ProdottiErogati> getProdottiErogatiByDistributoreSortedByScaffalePosto(Integer idDistributore) {
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			String hql = "from ProdottiErogati PE where PE.distributore.id = :idDistributore order by PE.scaffale, PE.posto asc" ;
			Query query = session.createQuery(hql);
			query.setInteger("idDistributore", idDistributore);
			@SuppressWarnings("unchecked")
			ArrayList<ProdottiErogati> listProdottiErogati = (ArrayList<ProdottiErogati>) query.list();
			tx.commit();
			
			return listProdottiErogati;
			
		}   finally {
			session.close();
		}
	}

}
