/**
 * 
 */
package it.unisalento.distributori.daoimpl;

import it.unisalento.distributori.dao.PersonaDao;
import it.unisalento.distributori.domain.Dipendente;
import it.unisalento.distributori.domain.Persona;
import it.unisalento.distributori.model.PersonaModel;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author aguinaldo
 *
 */
public class PersonaDaoImpl extends BaseDaoImpl<Persona> implements PersonaDao {
	Session session ;
	Transaction tx;

	@Override
	public Persona getPersonaByCredentials(String email, String password) {
		session = sf.openSession();
		tx = session.beginTransaction();
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

	@Override
	public boolean emailExists(String email, Integer my_ID) {
		session = sf.openSession();
		tx = session.beginTransaction();
		Query query;
		if(my_ID != null){
			query = session.createQuery("from Persona where email=:email and id!=:id");
			query.setString("id", my_ID.toString());
		}else
			query = session.createQuery("from Persona where email=:email");
		query.setString("email", email);
		Persona persona = (Persona) query.uniqueResult();
		tx.commit();
		session.close();
		if(persona != null){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<PersonaModel> getAllDipendenti() {
		try {
		session = sf.openSession();
		tx = session.beginTransaction();
		List<PersonaModel> listDipendenti;
		Query query = session.createQuery("from Persona as P inner join P.dipendente");
				
			listDipendenti = (List<PersonaModel>) query.list();
			tx.commit();
			session.close();
			return listDipendenti;			
		} catch (ClassCastException ce){
			System.err.println(ce.getLocalizedMessage());
			ce.printStackTrace();
		} catch (Exception e){
			System.err.println(e.getLocalizedMessage());
			e.printStackTrace();

		}
		return null;
	}

}
