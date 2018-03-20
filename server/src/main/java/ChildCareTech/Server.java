package ChildCareTech;

import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTech.utils.Settings;
import org.hibernate.SessionFactory;
public class Server
{
    private static SessionFactory sessionFactory;

    public static void main(String[] args)
    {
        int rmiPort = Integer.parseInt(Settings.getProperty("port.rmi"));
        int socketPort = Integer.parseInt(Settings.getProperty("port.socket"));

        HibernateSessionFactoryUtil.startHibernate();
    }

}
