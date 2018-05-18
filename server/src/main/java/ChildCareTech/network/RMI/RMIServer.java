package ChildCareTech.network.RMI;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    private int rmiPort;

    public RMIServer(int rmiPort) {
        this.rmiPort = rmiPort;
    }

    public void start() {
        try {
            Registry registry = LocateRegistry.createRegistry(rmiPort);
            registry.bind("session_factory", RMIUserSessionFactory.getSessionFactory());
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            return;
        }
        System.out.println("[RMI] Authentication service running on port " + rmiPort);
    }
}
