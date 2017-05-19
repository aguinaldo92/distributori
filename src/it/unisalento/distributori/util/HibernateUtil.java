package it.unisalento.distributori.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	private static Logger logger;

	/*
	 * per inizializzare le variabili statiche si utilizza il blocco static che viene
	 * eseguito prima del costruttore. In questa maniera siamo sicuri che sessionFactory
	 * anzichè essere ricreato a ogni connessione al DB , sarà unico in tutta l'app
	 */

	static {
		logger = LogManager.getLogger(HibernateUtil.class.getName());
		Configuration configuration = new Configuration();
		configuration.configure();
		StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
		serviceRegistryBuilder.applySettings(configuration.getProperties());
		serviceRegistry = serviceRegistryBuilder.build();
		try{
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable e){
			logger.error("il DB non è raggiungibile, non è possibile generare la sessionFactory", e);
			throw new ExceptionInInitializerError(e);
		}
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
	public static Session getSession() {
		try {
			return sessionFactory.openSession();
		}catch (Exception e){
			logger.error("Impossibile connettersi al DB",e);
			throw e;
		}
	}

	public static void closeSession(Session session){
		try{
			session.close();
		} catch (NullPointerException ne){
			logger.error("Impossibile chiudere la sessione con il database, sessione nulla",ne);
			throw ne;
		}
	}

}
