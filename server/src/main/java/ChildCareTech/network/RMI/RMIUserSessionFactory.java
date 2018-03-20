package ChildCareTech.network.RMI;

import ChildCareTech.common.UserSession;
import ChildCareTech.common.UserSessionFactory;
import ChildCareTech.controller.LoginController;
import ChildCareTech.model.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIUserSessionFactory extends UnicastRemoteObject implements UserSessionFactory {
    private static RMIUserSessionFactory sessionFactory = null;

    public static RMIUserSessionFactory getSessionFactory() throws RemoteException {
        if(sessionFactory==null) sessionFactory = new RMIUserSessionFactory();
        return sessionFactory;
    }

    private RMIUserSessionFactory() throws RemoteException {}

    @Override
    public UserSession login(String userName, String password) throws RemoteException{
        User u = LoginController.login(userName, password);

        if(u != null) return new RMISession(u);
        return null;
    }
}
