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
		Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from Persona where email=:email and password=:password");
        query.setString("email", email); 
        query.setString("password", password);
        Persona persona = (Persona) query.uniqueResult();
        if(persona != null){
            System.out.println("Persona Retrieved from DB::"+persona);
        } else {
        	System.out.println("Persona non esistente");
        }
        tx.commit();
        session.close();
        return persona;
	}

	
}
