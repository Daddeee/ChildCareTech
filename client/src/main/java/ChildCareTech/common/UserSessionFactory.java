package ChildCareTech.common;

import ChildCareTech.common.exceptions.LoginFailedException;
import ChildCareTech.common.exceptions.RegistrationFailedException;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserSessionFactory extends Remote {
    UserSession login(String userName, String password) throws LoginFailedException, RemoteException;

    boolean register(String userName, String password) throws RegistrationFailedException, RemoteException;
}
