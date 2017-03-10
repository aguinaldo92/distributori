/**
 * 
 */
package it.unisalento.distributori.daoimpl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unisalento.distributori.dao.ProduttoreDao;
import it.unisalento.distributori.domain.Persona;
import it.unisalento.distributori.domain.Prodotto;
import it.unisalento.distributori.domain.Produttore;
import it.unisalento.distributori.domain.Stabilimento;

/**
 * @author aguinaldo
 *
 */
public class ProduttoreDaoImpl extends BaseDaoImpl<Produttore> implements ProduttoreDao {

	@Override
	public List<Stabilimento> getStabilimentiByProduttore(int id_produttore) {
		Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("select S from Stabilimento as S inner join S.produttore as P where P.id=:id_prod");
        query.setInteger("id_prod", id_produttore);
        
        List<Stabilimento> stabilimenti = new ArrayList<Stabilimento>();
        stabilimenti=query.list();
        
        tx.commit();
        session.close();
		return stabilimenti;
	}

	@Override
	public List<Produttore> getAllProduttori(){
		Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from Produttore as P where P.nome IS NOT NULL and nome != 'fittizio' order by P.nome");
        
        List<Produttore> list_prodotti = new ArrayList<Produttore>();
        list_prodotti=query.list();
        
        tx.commit();
        session.close();
		return list_prodotti;
	}
	
	

}
