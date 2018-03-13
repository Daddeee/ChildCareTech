package ChildCareTech;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.Properties;
import ChildCareTech.utils.Config;

public class App
{
    private static SessionFactory sessionFactory;
    private static Properties properties;

    public static void main(String[] args)
    {
        final String confPath = "./src/resources/config.xml";
        try {
            properties = Config.getProperties(confPath);
        } catch (Exception ex) {
            System.err.println("Failed to load configuration file" + ex);
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

}
