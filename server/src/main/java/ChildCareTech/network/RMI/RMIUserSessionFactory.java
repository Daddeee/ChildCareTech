package ChildCareTech.network.RMI;

import ChildCareTech.common.UserSessionFactory;
import ChildCareTech.common.exceptions.LoginFailedException;
import ChildCareTech.common.exceptions.RegistrationFailedException;
import ChildCareTech.controller.UserController;
import ChildCareTech.model.entities.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIUserSessionFactory extends UnicastRemoteObject implements UserSessionFactory {
    private static RMIUserSessionFactory sessionFactory = null;

    public static RMIUserSessionFactory getSessionFactory() throws RemoteException {
        if (sessionFactory == null) sessionFactory = new RMIUserSessionFactory();
        return sessionFactory;
    }

    private RMIUserSessionFactory() throws RemoteException {
    }

    @Override
    public RMIUserSessionFacade login(String userName, String password) throws LoginFailedException, RemoteException {
        User u = UserController.getUser(userName, password);
        RMIUserSessionFacade session = new RMIUserSessionFacade(u);
        UserController.storeSession(u, session);
        return session;
    }

    @Override
    public boolean register(String userName, String password) throws RegistrationFailedException {
        return UserController.registerUser(userName, password);
    }
}
