package ChildCareTech.services;


import ChildCareTech.common.UserSession;
import ChildCareTech.common.UserSessionFactory;
import ChildCareTech.common.exceptions.LoginFailedException;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class SessionService {
    private static UserSessionFactory sessionFactory = null;
    private static UserSession session = null;
    private static String loginErrorMessage = null;

    private SessionService() {
    }

    public static void loginAttempt(String userName, String password) {
        try {
            sessionFactory = (UserSessionFactory) Naming.lookup("rmi://localhost:1099/session_factory");

            session = sessionFactory.login(userName, password);
        } catch (LoginFailedException | RemoteException | NotBoundException | MalformedURLException e) {
            loginErrorMessage = e.getMessage();
            e.printStackTrace();
        }
    }

    public static void logoutAttempt() {
        try {
            if (session != null) session.logout();
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        session = null;
        //Logout callback???? Da mettere qua
    }

    public static UserSession getSession() {
        return session;
    }

    public static boolean isNull() {
        return session == null;
    }

    public static String getLoginErrorMessage() {
        return loginErrorMessage;
    }
}
