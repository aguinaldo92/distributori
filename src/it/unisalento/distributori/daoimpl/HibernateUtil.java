package it.unisalento.distributori.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;



public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	/*
	 * per inizializzare le variabili statiche si utilizza il blocco static che viene
	 * eseguito prima del costruttore. In questa maniera siamo sicuri che sessionFactory
	 * anzichè essere ricreato a ogni connessione al DB , sarà unico in tutta l'app
	 */
	
	static{
		Configuration configuration = new Configuration();
		configuration.configure();
		StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
		serviceRegistryBuilder.applySettings(configuration.getProperties());
		serviceRegistry = serviceRegistryBuilder.build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}
	
	public HibernateUtil() {
		
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}
	
	//per ottenere direttamente la session all'interno della classe DAO
	public static Session getSession(){
		return sessionFactory.openSession();
	}
	
	public static void closeSession(Session session){
		session.close();
	}

}
