package ChildCareTech.network.RMI;

import ChildCareTech.common.UserSession;
import ChildCareTech.common.UserSessionFactory;
import ChildCareTech.common.exceptions.LoginFailedException;
import ChildCareTech.common.exceptions.RegistrationFailedException;
import ChildCareTech.controller.SessionController;
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
    public RMIUserSession login(String userName, String password) throws LoginFailedException, RemoteException{
        User u = SessionController.getUser(userName, password);
        RMIUserSession session = new RMIUserSession(u);
        SessionController.storeSession(session, userName);
        return session;
    }

    @Override
    public boolean register(String userName, String password) throws RegistrationFailedException, RemoteException {
        return SessionController.registerUser(userName, password);
    }
}
