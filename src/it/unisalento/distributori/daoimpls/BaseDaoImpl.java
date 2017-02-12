package it.unisalento.distributori.daoimpls;

import it.unisalento.distributori.dao.BaseDao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class BaseDaoImpl<T> implements BaseDao<T> {

	protected SessionFactory sf;
	private ServletContext ctx;
	private Transaction tx;
	private int id;
	private Session session;

	public BaseDaoImpl() {
		this.ctx = ServletActionContext.getServletContext();
		this.sf = (SessionFactory) ctx.getAttribute("SessionFactory");
	}

	@Override
	public int set(T entity) {
		System.out.println("BaseDaoImpl: set()");
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			id = (int) session.save(entity);
			tx.commit();
			System.out.println("try");
			
		}
		catch (Exception e) {
			System.out.println("catch");
			if (tx != null) tx.rollback();
			throw e;
		}
		 finally {
				System.out.println("finally");
		     session.close();
		 }
		System.out.println("return");
		return id;
	}


	@Override
	public T get(int id, Class clazz){
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		T entity= (T)session.get(clazz, id);
		tx.commit();
		session.close();
		return entity;
	}

	@Override
	public List<T> getAll(Class clazz) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		List<T> list= (List<T>)session.createQuery("from "+clazz.getName()).list();
		tx.commit();
		session.close();
		return list;
	}

	@Override
	public void update(T entity) {
		System.out.println("BaseDaoImpl: update()");
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.update(entity);
		tx.commit();
		session.close();
	}
}