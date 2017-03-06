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

	@Override
	public List<Prodotto> getAllProdotti() {
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

	@Override
	public List<Prodotto> getAllProdottiFiltrati(List<String> list_fam_IDs, List<String> list_categ_IDs) {
		Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        String querystring="select distinct P from Prodotto as P inner join P.categoria as C inner join C.prodottos as P inner join P.famiglieProdottos as F where ";
        
        //aggiunta delle condizioni di filtraggio alla query
        String chiusura_cond_categ="";
        if(list_categ_IDs.size()>0 && list_fam_IDs.size()>0)
        	chiusura_cond_categ=") and ";
        for(int i=0; i<list_categ_IDs.size(); i++){
        	if (i==0){
        		querystring=querystring.concat("( C.id="+list_categ_IDs.get(i)+" ");
        	}else
        		querystring=querystring.concat("or C.id="+list_categ_IDs.get(i)+" ");
        }
        querystring=querystring.concat(chiusura_cond_categ);
        for(int i=0; i<list_fam_IDs.size(); i++){
        	if (i==0)
                querystring=querystring.concat("( F.famiglia.id="+list_fam_IDs.get(i)+" ");
        	else
        		querystring=querystring.concat("or F.famiglia.id="+list_fam_IDs.get(i)+" ");
        }
        querystring=querystring.concat(") order by P.nome");
        
        Query query = session.createQuery(querystring);
        
        List<Prodotto> list_prodotti = new ArrayList<Prodotto>();
        list_prodotti=query.list();
        
        tx.commit();
        session.close();
		return list_prodotti;
	}
	
}
