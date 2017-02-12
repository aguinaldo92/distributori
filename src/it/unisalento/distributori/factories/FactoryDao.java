/**
 * 
 */
package it.unisalento.distributori.factories;

import it.unisalento.distributori.dao.PersonaDao;
import it.unisalento.distributori.daoimpls.PersonaDaoImpl;

/**
 * @author aguinaldo
 *
 */
public class FactoryDao {
	
	static private class Holder{
		static final private FactoryDao ISTANCE=new FactoryDao();
	}
	
	public static FactoryDao getIstance(){
		System.out.println("FactoryDao: getIstance()");
		return Holder.ISTANCE;
	}
	
	public PersonaDao getPersonaDao(){
		System.out.println("FactoryDao: getUserDao");
		return new PersonaDaoImpl();
	}
	
}
