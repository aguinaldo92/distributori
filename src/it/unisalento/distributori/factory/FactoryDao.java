/**
 * 
 */
package it.unisalento.distributori.factory;

import it.unisalento.distributori.dao.PersonaDao;
import it.unisalento.distributori.daoimpl.PersonaDaoImpl;

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
		System.out.println("FactoryDao: getPersonaDao");
		return new PersonaDaoImpl();
	}
	
}
