/**
 * 
 */
package it.unisalento.distributori.factory;

import it.unisalento.distributori.dao.CategoriaDao;
import it.unisalento.distributori.dao.DipendenteDao;
import it.unisalento.distributori.dao.FamigliaDao;
import it.unisalento.distributori.dao.PersonaDao;
import it.unisalento.distributori.dao.ProdottoDao;
import it.unisalento.distributori.dao.ProduttoreDao;
import it.unisalento.distributori.daoimpl.CategoriaDaoImpl;
import it.unisalento.distributori.daoimpl.DipendenteDaoImpl;
import it.unisalento.distributori.daoimpl.FamigliaDaoImpl;
import it.unisalento.distributori.daoimpl.PersonaDaoImpl;
import it.unisalento.distributori.daoimpl.ProdottoDaoImpl;
import it.unisalento.distributori.daoimpl.ProduttoreDaoImpl;

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
	
	public DipendenteDao getDipendenteDao(){
		System.out.println("FactoryDao: getDipendenteDao");
		return new DipendenteDaoImpl();
	}
	
	public ProdottoDao getProdottoDao(){
		System.out.println("FactoryDao: getProdottoDao");
		return new ProdottoDaoImpl();
	}
	
	public CategoriaDao getCategoriaDao(){
		System.out.println("FactoryDao: getCategoriaDao");
		return new CategoriaDaoImpl();
	}
	
	public FamigliaDao getFamigliaDao(){
		System.out.println("FactoryDao: FamigliaDaoDao");
		return new FamigliaDaoImpl();
	}
	
	public ProduttoreDao getProduttoreDao(){
		System.out.println("FactoryDao: ProduttoreDaoDao");
		return new ProduttoreDaoImpl();
	}
	
}
