package it.unisalento.distributori.daoimpl;

import it.unisalento.distributori.dao.BaseDao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class BaseDaoImpl<T> implements BaseDao<T> {
	@Override
	public int set(T entity) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction(); //inizio transazione
		int id=(int)session.save(entity);
		tx.commit(); //fine transazione
		//CHIUDERE LA SESSIONE
		HibernateUtil.closeSession(session);
		return id;
	}

	@Override
	public T get(int id, Class clazz){
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		//Per prendere un oggetto tramite id utilizzare il metodo get di session 
		T entity = (T)session.get(clazz, id);
		tx.commit();
		//CHIUDERE LA SESSIONE
		HibernateUtil.closeSession(session);
		return entity;
	}
	
	@Override
	public List<T> getAll(Class clazz){
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>)session.createQuery("from "+clazz.getSimpleName()).list();
		
		tx.commit();
		session.close();
		return list;
	}

	@Override
	public List<T> getAllSortedBy(Class clazz, String column) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>)session.createQuery("from "+clazz.getSimpleName()+" as T order by T."+column).list();
		
		tx.commit();
		session.close();
		return list;
	}

	@Override
	public void update(T entity) throws Exception {
		System.out.println("BaseDaoImpl: update()");
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.update(entity);
		tx.commit();
		session.close();
	}
	
	@Override
	public void delete(T entity) {
		System.out.println("BaseDaoImpl: delete()");
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.delete(entity);
		tx.commit();
		session.close();
	}
}