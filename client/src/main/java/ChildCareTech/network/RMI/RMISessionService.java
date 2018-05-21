package ChildCareTech.network.RMI;


import ChildCareTech.common.RemoteEventObserver;
import ChildCareTech.common.UserSession;
import ChildCareTech.common.UserSessionFactory;
import ChildCareTech.common.exceptions.LoginFailedException;
import ChildCareTech.network.SessionService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RMISessionService implements SessionService {
    private UserSessionFactory sessionFactory = null;
    private UserSession session = null;
    private String loginErrorMessage = null;
    private RemoteEventObserver observer = null;

    public RMISessionService() {
    }

    @Override
    public void loginAttempt(String userName, String password) {
        try {
            initSessionFactory();
            session = sessionFactory.login(userName, password);

            observer = new RMIRemoteEventObserver();
            session.addRemoteEventObserver(observer);
        } catch (LoginFailedException | RemoteException | NotBoundException | MalformedURLException e) {
            loginErrorMessage = e.getMessage();
            e.printStackTrace();
        }
    }

    @Override
    public void logoutAttempt() {
        try {
            if (session != null) {
                session.removeRemoteEventObserver(observer);
                session.logout();
            }
            if (observer != null) observer.unexport();
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        session = null;
        observer = null;
    }

    private void initSessionFactory() throws NotBoundException, MalformedURLException, RemoteException {
        if(sessionFactory == null)
            sessionFactory = (UserSessionFactory) Naming.lookup("rmi://localhost:1099/session_factory");
    }

    @Override
    public UserSession getSession() {
        return session;
    }

    @Override
    public RemoteEventObserver getObserver() {
        return observer;
    }

    @Override
    public boolean isNull() {
        return session == null;
    }

    @Override
    public String getLoginErrorMessage() {
        return loginErrorMessage;
    }
}
