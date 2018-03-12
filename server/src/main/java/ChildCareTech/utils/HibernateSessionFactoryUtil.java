package ChildCareTech.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory ourInstance;

    static{
        ourInstance = new Configuration().configure().buildSessionFactory();
    }

    public static SessionFactory getInstance() {
        return ourInstance;
    }

    private HibernateSessionFactoryUtil() {}
}
