/**
 * 
 */
package it.unisalento.distributori.daoimpl;

import it.unisalento.distributori.dao.PersonaDao;
import it.unisalento.distributori.domain.Persona;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author aguinaldo
 *
 */
public class PersonaDaoImpl extends BaseDaoImpl<Persona> implements PersonaDao {

	@Override
	public Persona getPersonaByCredentials(String email, String password) {
		try{
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Persona where email=:email and password=:password");
		query.setString("email", email); 
		query.setString("password", password);
		Persona persona = (Persona) query.uniqueResult();
		tx.commit();
		return persona;
		
		} finally{
			session.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see it.unisalento.distributori.dao.PersonaDao#emailExists(java.lang.String, java.lang.Integer)
	 */
	@Override
	public boolean emailExists(String email, Integer my_ID) {
		try{
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Query query;
			query = session.createQuery("from Persona where email=:email and id!=:id");
			query.setString("id", my_ID.toString());
			query.setString("email", email);
			Persona persona = (Persona) query.uniqueResult();
			tx.commit();
			if(persona != null){
				return true;
			} else {
				return false;
			}
		} finally{
			session.close();
		}
	}

	@Override
	public boolean emailExists(String email) {
		try{
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Query query;
			query = session.createQuery("from Persona where email=:email");
			query.setString("email", email);
			Persona persona = (Persona) query.uniqueResult();
			tx.commit();
			if(persona != null){
				return true;
			} else {
				return false;
			}
		} finally{
			session.close();
		}
	}

}
