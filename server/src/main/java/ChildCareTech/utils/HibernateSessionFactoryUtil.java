package ChildCareTech.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Singleton class that encapsulate the creation and configuration of Hibernate SessionFactory.
 */
public class HibernateSessionFactoryUtil {
    private static SessionFactory ourInstance = null;

    /**
     * Create and configure the Hibernate SessionFactory.
     */
    public static void startHibernate() {
        ourInstance = new Configuration().configure().buildSessionFactory();
    }

    /**
     * @return the unique SessionFactory instance.
     */
    public static SessionFactory getInstance() {
        if (ourInstance == null) startHibernate();
        return ourInstance;
    }

    /**
     * Close the Hibernate SessionFactory if present.
     */
    public static void close() {
        if(ourInstance != null)
            ourInstance.close();
    }

    private HibernateSessionFactoryUtil() {}
}
