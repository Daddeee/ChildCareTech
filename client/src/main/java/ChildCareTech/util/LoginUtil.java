package ChildCareTech.util;

import ChildCareTech.common.Session;
import ChildCareTech.common.SessionFactory;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class LoginUtil {

    private static Session session = null;

    private LoginUtil() {
    }

    public static Session loginAttempt(SessionFactory sessionFactory, String userName, String password) {
        try {
            sessionFactory = (SessionFactory) Naming.lookup("rmi://localhost:1099/session_factory");

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

        return session;
    }
}
