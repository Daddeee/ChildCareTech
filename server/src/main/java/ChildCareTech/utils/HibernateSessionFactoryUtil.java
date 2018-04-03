package ChildCareTech.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory ourInstance = null;

    public static void startHibernate() {
        ourInstance = new Configuration().configure().buildSessionFactory();
    }

    public static SessionFactory getInstance() {
        if (ourInstance == null) startHibernate();
        return ourInstance;
    }

    private HibernateSessionFactoryUtil() {
    }
}
