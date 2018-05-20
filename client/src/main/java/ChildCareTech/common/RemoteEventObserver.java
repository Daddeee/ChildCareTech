package ChildCareTech.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteEventObserver extends Remote {
    void update(RemoteUpdatable updatable) throws RemoteException;
    void unexport() throws RemoteException;
}

