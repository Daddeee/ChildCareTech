package ChildCareTech.network.RMI;

import ChildCareTech.common.UserSessionFactory;
import ChildCareTech.common.exceptions.RegistrationFailedException;
import ChildCareTech.network.RegistrationService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RMIRegistrationService implements RegistrationService {
    private UserSessionFactory sessionFactory = null;
    private String registrationErrorMessage = null;

    public boolean registerAttempt(String userName, String password) {
        boolean status = false;
        try {
            sessionFactory = (UserSessionFactory) Naming.lookup("rmi://localhost:1099/session_factory");

            status = sessionFactory.register(userName, password);
        } catch (RegistrationFailedException | RemoteException | NotBoundException | MalformedURLException e) {
            registrationErrorMessage = e.getMessage();
            e.printStackTrace();
        }

        return status;
    }

    public String getRegistrationErrorMessage() {
        return registrationErrorMessage;
    }
}
