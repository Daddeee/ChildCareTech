package ChildCareTech.network.RMI;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * The RMI Server, encapsulates all logic correlated directly to RMI.
 */
public class RMIServer {
    private int rmiPort;

    /**
     * @param rmiPort the port on which to run the RMI server.
     */
    public RMIServer(int rmiPort) {
        this.rmiPort = rmiPort;
    }

    /**
     * Create the RMI registry on the given {@link #rmiPort port} and then binds on it an instance of {@link RMIUserSessionFactory}.
     * RMI automatically starts a thread to handle remote invocations.
     */
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
