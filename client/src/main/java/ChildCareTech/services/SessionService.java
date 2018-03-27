package ChildCareTech.services;


import ChildCareTech.common.UserSession;
import ChildCareTech.common.UserSessionFactory;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class SessionService {

    private static UserSession session = null;

    private SessionService() { }

    public static void loginAttempt(UserSessionFactory sessionFactory, String userName, String password) {
        try {
            sessionFactory = (UserSessionFactory) Naming.lookup("rmi://localhost:1099/session_factory");

            session = sessionFactory.login(userName, password);
        } catch (MalformedURLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (RemoteException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (NotBoundException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static UserSession getSession() {
        return session;
    }

    public static boolean isNull(){
        return session == null;
    }
}
