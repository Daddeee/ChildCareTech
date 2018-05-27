package ChildCareTech.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This interfaces defines the method an observer must implement to be notified on refresh events.
 */
public interface RemoteEventObserver extends Remote {
    /**
     * Update different client part based on the provided param.
     *
     * @param updatable specify which part of the client must be updated.
     * @throws RemoteException
     */
    void update(RemoteUpdatable updatable) throws RemoteException;

    /**
     * Close and remove the observer.
     *
     * @throws RemoteException
     */
    void unexport() throws RemoteException;
}
