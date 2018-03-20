package ChildCareTech.network.RMI;

import ChildCareTech.common.UserSession;
import ChildCareTech.model.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMISession extends UnicastRemoteObject implements UserSession {
    private User user;

    public RMISession(User user) throws RemoteException {
        this.user = user;
    }

    @Override
    public void greetWorld() throws RemoteException{
        System.out.println("Hello, world!");
    }

    @Override
    public void logout() throws RemoteException{
        UnicastRemoteObject.unexportObject(this, true);
    }
}
