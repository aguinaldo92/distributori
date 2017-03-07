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
	private ArrayList<ProdottiErogati> listProdottiErogatiScarseggianti;
	private ArrayList<ProdottiErogati> listProdottiErogati;
	private Session session;
	private Transaction tx;

	/*
	 * Il metodo fornisce l'elenco dei prodotti erogati da un singolo distributore (che appartiene al dipendente che lo sta visualizzando)
	 * che sono sotto la soglia minima di quantit� da erogare, ordianti per la quantit� stessa. Questa lista servir� a comporre la lista
	 * di tutti i distributori con informazioni sul distributore e sui prodotti che esso eroga che stanno terminando (per la "dashboard" del dipendente)
	 * (non-Javadoc)
	 * @see it.unisalento.distributori.dao.ProdottiErogatiDao#GetProdottiScarseggiantiByDistributore(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public ArrayList<ProdottiErogati> getProdottiScarseggiantiByDistributore(Integer idDistributore,
			Integer quantitaMinima) {
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			String hql = "select PE from ProdottiErogati PE inner join PE.prodotto as P where PE.distributore.id = :idDistributore and P.nome != 'vuoto' and PE.quantita < :quantitaMinima order by PE.quantita asc" ;
			Query query = session.createQuery(hql);
			query.setInteger("idDistributore", idDistributore);
			query.setInteger("quantitaMinima", quantitaMinima);
			listProdottiErogatiScarseggianti = (ArrayList<ProdottiErogati>) query.list();
			tx.commit();
		}
		catch (Exception e) {
			System.out.println("Impossibile ottenere lista dei prodotti dato un distributore:");
			System.out.println(e.getLocalizedMessage());
			return null;
		}
		session.close();
		return listProdottiErogatiScarseggianti;
	}

	@Override
	public ArrayList<String> getNomiProdottiScarseggiantiByDistributore(Integer idDistributore,
			Integer quantitaMinima) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ProdottiErogati> getProdottiErogatiByDistributoreSortedByScaffalePosto(Integer idDistributore) {
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			String hql = "from ProdottiErogati PE where PE.distributore.id = :idDistributore order by PE.scaffale, PE.posto asc" ;
			Query query = session.createQuery(hql);
			query.setInteger("idDistributore", idDistributore);
			listProdottiErogati = (ArrayList<ProdottiErogati>) query.list();
			tx.commit();
		}
		catch (Exception e) {
			System.out.println("Impossibile ottenere lista dei prodotti dato un distributore:");
			System.out.println(e.getLocalizedMessage());
			return null;
		}
		session.close();
		return listProdottiErogati;
	}

}
