package ChildCareTech;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App
{
    private static SessionFactory sessionFactory;
    public static void main(String[] args)
    {

        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }

    }

}
