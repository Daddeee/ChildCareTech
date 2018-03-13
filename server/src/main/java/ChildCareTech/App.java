package ChildCareTech;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.Properties;

public class App
{
    private static SessionFactory sessionFactory;
    private static Properties properties;

    public static void main(String[] args)
    {
        String confPath = "/";
        try {
            //properties = Config.getProperties(confPath);
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }


    }

}
