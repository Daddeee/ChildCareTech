package ChildCareTech.network.RMI;

import ChildCareTech.common.Session;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMISession extends UnicastRemoteObject implements Session {
    public RMISession() throws RemoteException {}

    @Override
    public void greetWorld() throws RemoteException{
        System.out.println("Hello, world!");
    }

    @Override
    public void logout() throws RemoteException{
        UnicastRemoteObject.unexportObject(this, true);
    }
}
