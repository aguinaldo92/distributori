/**
 * 
 */
package it.unisalento.distributori.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unisalento.distributori.dao.ProdottoDao;
import it.unisalento.distributori.domain.Prodotto;
import it.unisalento.distributori.domain.Stabilimento;

/**
 * @author aguinaldo
 *
 */
public class ProdottoDaoImpl extends BaseDaoImpl<Prodotto> implements ProdottoDao {

	public List<Prodotto> getAllProdotti(){
		Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from Prodotto as P where P.nome!=:vuoto order by P.nome");
        query.setString("vuoto", "Vuoto");
        
        List<Prodotto> list_prodotti = new ArrayList<Prodotto>();
        list_prodotti=query.list();
        
        tx.commit();
        session.close();
		return list_prodotti;
	}
	
}
