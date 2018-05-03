package application;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import resources.Admin;
import resources.Kitaplar;
import resources.Uyeler;

public class HibernateUtil {
	
	private static final SessionFactory sessionFactory = buildSessionFactory();
	 
    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure("resources\\hibernate.cfg.xml").addAnnotatedClass(Admin.class).addAnnotatedClass(Kitaplar.class).addAnnotatedClass(Uyeler.class).buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
 
    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }

}
