package ChildCareTech.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserSession extends Remote{
    void greetWorld() throws RemoteException;
    void logout() throws RemoteException;
}
