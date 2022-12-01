package com.nagarro.utilities;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	//XML based configuration
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    private static SessionFactory buildSessionFactory() {
        try {
            // used to turn off the logging details of hibernate on console
            Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        	serviceRegistry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml")
                    .build();

            sessionFactory = new MetadataSources( serviceRegistry )
                        .buildMetadata()
                        .buildSessionFactory();        	

            return sessionFactory;
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) sessionFactory = buildSessionFactory();
        return sessionFactory;
    }
}
