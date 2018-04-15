package ChildCareTech.services;


import ChildCareTech.common.RemoteEventObserver;
import ChildCareTech.common.UserSession;
import ChildCareTech.common.UserSessionFactory;
import ChildCareTech.common.exceptions.LoginFailedException;
import ChildCareTech.network.RMI.RMIRemoteEventObserver;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class SessionService {
    private static UserSessionFactory sessionFactory = null;
    private static UserSession session = null;
    private static String loginErrorMessage = null;
    private static RemoteEventObserver observer = null;

    private SessionService() {
    }

    public static void loginAttempt(String userName, String password) {
        try {
            initSessionFactory();
            session = sessionFactory.login(userName, password);
        } catch (LoginFailedException | RemoteException | NotBoundException | MalformedURLException e) {
            loginErrorMessage = e.getMessage();
            e.printStackTrace();
        }
    }

    public static void registerRemoteEventObserver(){

        try {
            initSessionFactory();
            observer = new RMIRemoteEventObserver();
            sessionFactory.addRemoteEventObserver(observer);
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            loginErrorMessage = e.getMessage();
            e.printStackTrace();
        }
    }

    private static void initSessionFactory() throws NotBoundException, MalformedURLException, RemoteException {
        if(sessionFactory == null)
            sessionFactory = (UserSessionFactory) Naming.lookup("rmi://localhost:1099/session_factory");
    }

    public static void logoutAttempt() {
        try {
            if (session != null) session.logout();
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        session = null;
        observer = null;
    }

    public static UserSession getSession() {
        return session;
    }

    public static RemoteEventObserver getObserver() {
        return observer;
    }

    public static boolean isNull() {
        return session == null;
    }

    public static String getLoginErrorMessage() {
        return loginErrorMessage;
    }
}
