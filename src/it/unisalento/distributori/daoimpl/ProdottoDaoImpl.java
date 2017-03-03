/**
 * 
 */
package it.unisalento.distributori.daoimpl;

import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;

import it.unisalento.distributori.dao.ProdottoDao;
import it.unisalento.distributori.domain.ProdottiErogati;
import it.unisalento.distributori.domain.Prodotto;

/**
 * @author aguinaldo
 *
 */
public class ProdottoDaoImpl extends BaseDaoImpl<Prodotto> implements ProdottoDao {
	private ArrayList<Prodotto> listProdottiCompatibili;
	private Session session;
	private Transaction tx;
	
	@Override
	public ArrayList<Prodotto> getProdottiCompatibiliByDistributore(Integer idDistributore) {
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			String hql = "select P from Distributore as D inner join D.categorieFornites as CF inner join CF.categoria as C1 inner join C1.prodottos as P inner join P.categoria as C2 where D.id = :idDistributore and C1.id = C2.id  order by C1.nome, P.nome" ;
			Query query = session.createQuery(hql);
			query.setInteger("idDistributore", idDistributore);
			listProdottiCompatibili = (ArrayList<Prodotto>) query.list();
			tx.commit();
		}
		catch (Exception e) {
			System.out.println("Impossibile ottenere lista dei prodotti compatibili dato un distributore:");
			System.out.println(e.getLocalizedMessage());
			return null;
		}
		session.close();
		return listProdottiCompatibili;
	}

}
