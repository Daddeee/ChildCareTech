package ChildCareTech.services;

import ChildCareTech.common.UserSessionFactory;
import ChildCareTech.common.exceptions.RegistrationFailedException;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RegistrationService {
    private static UserSessionFactory sessionFactory = null;
    private static String registrationErrorMessage = null;

    public static boolean registerAttempt(String userName, String password) {
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

    public static String getRegistrationErrorMessage() {
        return registrationErrorMessage;
    }
}
