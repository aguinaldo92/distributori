package it.unisalento.distributori.daoimpl;

import it.unisalento.distributori.dao.BaseDao;
import it.unisalento.distributori.util.HibernateUtil;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class BaseDaoImpl<T> implements BaseDao<T> {


	@Override
	public int set(T entity) {
		Session session = null;
		Transaction tx = null;
		try{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction(); //inizio transazione
			int id=(int)session.save(entity);
			tx.commit(); //fine transazione
			//CHIUDERE LA SESSIONE
			return id;
		} catch (Exception e) {
			tx.rollback();
			throw e;
		} finally{
			HibernateUtil.closeSession(session);
		}
	}

	@Override
	public T get(int id, @SuppressWarnings("rawtypes") Class clazz){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			//Per prendere un oggetto tramite id utilizzare il metodo get di session 
			@SuppressWarnings("unchecked")
			T entity = (T)session.get(clazz, id);
			tx.commit();
			//CHIUDERE LA SESSIONE
			return entity;
		}finally{
			HibernateUtil.closeSession(session);
		}
	}

	@Override
	public List<T> getAll(@SuppressWarnings("rawtypes") Class clazz){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<T> list = (List<T>)session.createQuery("from "+clazz.getSimpleName()).list();
			tx.commit();
			return list;
		} finally{
			HibernateUtil.closeSession(session);
		}
	}

	@Override
	public List<T> getAllSortedBy(@SuppressWarnings("rawtypes") Class clazz, String column) {
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<T> list = (List<T>)session.createQuery("from "+clazz.getSimpleName()+" as T order by T."+column).list();
			tx.commit();
			return list;
		} finally{
			HibernateUtil.closeSession(session);
		}
	}

	@Override
	public void update(T entity) {
		Session session = null;
		Transaction tx = null;
		try{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			session.update(entity);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw e;
		} finally{
			HibernateUtil.closeSession(session);
		}
	}

	@Override
	public void delete(T entity) {
		Session session = null;
		Transaction tx = null;
		try{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			session.delete(entity);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw e;
		} finally{
			HibernateUtil.closeSession(session);
		}
	}
}