package ChildCareTech.network.RMI;

import ChildCareTech.common.Session;
import ChildCareTech.common.SessionFactory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMISessionFactory extends UnicastRemoteObject implements SessionFactory {
    private static RMISessionFactory sessionFactory = null;

    public static RMISessionFactory getSessionFactory() throws RemoteException {
        if(sessionFactory==null) sessionFactory = new RMISessionFactory();
        return sessionFactory;
    }

    private RMISessionFactory() throws RemoteException {}

    @Override
    public Session login(String userName, String password) throws RemoteException{
        if(userName.equals("admin") && password.equals("admin")) return new RMISession();
        return null;
    }
}
