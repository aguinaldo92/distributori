/**
 * 
 */
package it.unisalento.distributori.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * @author aguinaldo
 *
 */
public class HibernateServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		SessionFactory sf = (SessionFactory) sce.getServletContext().getAttribute("SessionFactory");
		sf.close();
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try{
			System.out.println("Inizializzaione!!!!!");
			//URL url = HibernateServletContextListener.class.getResource("/hibernate.cfg.xml");
			Configuration config = new Configuration();
			config.configure(); // url
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
			SessionFactory sf = config.buildSessionFactory(serviceRegistry);
			sce.getServletContext().setAttribute("SessionFactory", sf);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Non è possibile collegarsi al database");
			System.exit(1);
		}

	}
}
