/**
 * 
 */
package it.unisalento.distributori.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unisalento.distributori.dao.FamiglieProdottoDao;
import it.unisalento.distributori.domain.FamiglieProdotto;

/**
 * @author aguinaldo
 *
 */
public class FamiglieProdottoDaoImpl extends BaseDaoImpl<FamiglieProdotto> implements FamiglieProdottoDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<FamiglieProdotto> getFamiglieByProdotto(int prodotto_id) {
		try{
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("select FP from FamiglieProdotto as FP inner join FP.prodotto as P where P.id=:id_prod");
			query.setInteger("id_prod", prodotto_id);

			List<FamiglieProdotto> fams_prod = new ArrayList<FamiglieProdotto>();
			fams_prod=query.list();
			tx.commit();

			return fams_prod;
			
		} finally{
			session.close();
		}
	}

	@Override
	public int deleteProdottoFamilies(int prodotto_id) {
			List<FamiglieProdotto> list_fams = new ArrayList<FamiglieProdotto>();
			list_fams=getFamiglieByProdotto(prodotto_id);
			for (int i=0;i<list_fams.size();i++){
				delete(list_fams.get(i));
			}
			
			return list_fams.size();
		
	}
}
