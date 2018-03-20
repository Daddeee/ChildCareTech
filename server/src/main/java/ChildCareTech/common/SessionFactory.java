package ChildCareTech.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SessionFactory extends Remote {
    Session login(String userName, String password) throws RemoteException;
}
