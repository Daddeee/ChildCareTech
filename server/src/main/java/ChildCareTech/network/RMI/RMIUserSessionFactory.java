package ChildCareTech.network.RMI;

import ChildCareTech.common.UserSessionFactory;
import ChildCareTech.common.exceptions.LoginFailedException;
import ChildCareTech.common.exceptions.RegistrationFailedException;
import ChildCareTech.controller.UserController;
import ChildCareTech.model.entities.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * This class is a factory of {@link RMIUserSessionFacade} instances. Exposed as a singleton,
 * it provides the creation/storage logic for RMI sessions and the possibility to register users.
 */
public class RMIUserSessionFactory extends UnicastRemoteObject implements UserSessionFactory {
    private static RMIUserSessionFactory sessionFactory = null;

    /**
     * @return the RMIUserSessionFactory instance.
     * @throws RemoteException
     */
    public static RMIUserSessionFactory getSessionFactory() throws RemoteException {
        if (sessionFactory == null) sessionFactory = new RMIUserSessionFactory();
        return sessionFactory;
    }

    private RMIUserSessionFactory() throws RemoteException {}

    /**
     * Attempt login for user with provided credentials: if those are correct, the method returns
     * an instance of {@link RMIUserSessionFacade}, representing the active session for the logged user.
     *
     * @param userName the provided username
     * @param password the provided password
     * @return an instance of {@link RMIUserSessionFacade}
     * @throws LoginFailedException if credentials are invalid.
     * @throws RemoteException
     */
    @Override
    public RMIUserSessionFacade login(String userName, String password) throws LoginFailedException, RemoteException {
        User u = UserController.getUser(userName, password);
        RMIUserSessionFacade session = new RMIUserSessionFacade(u);
        UserController.storeSession(u, session);
        return session;
    }

    /**
     * See {@link UserController#registerUser(String, String)}
     *
     * @param userName
     * @param password
     * @return
     * @throws RegistrationFailedException
     */
    @Override
    public boolean register(String userName, String password) throws RegistrationFailedException {
        return UserController.registerUser(userName, password);
    }
}
