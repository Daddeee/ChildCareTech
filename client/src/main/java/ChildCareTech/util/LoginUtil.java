package ChildCareTech.util;


import ChildCareTech.common.UserSession;
import ChildCareTech.common.UserSessionFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class LoginUtil {

    private static UserSession session = null;

    private LoginUtil() { }

    public static UserSession loginAttempt(UserSessionFactory sessionFactory, String userName, String password) {
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

        return session;
    }
}
