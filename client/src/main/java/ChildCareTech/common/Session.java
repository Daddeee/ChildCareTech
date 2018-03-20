package ChildCareTech.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Session extends Remote{
    void greetWorld() throws RemoteException;
    void logout() throws RemoteException;
}
