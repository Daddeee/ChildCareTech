package ChildCareTech.network.socket;

import ChildCareTech.common.RemoteEventObserver;
import ChildCareTech.common.RemoteUpdatable;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.HashMap;

/**
 * Server side of a socket remote event observer.
 */
public class SocketRemoteEventObserver implements RemoteEventObserver {
    /**
     * This map holds all the socket remote event observers currently active.
     */
    public static HashMap<String, SocketRemoteEventObserver> socketRemoteEventObserversMap = new HashMap<>();

    private Socket socket;
    private ObjectOutputStream out;

    /**
     * Connects to the client side of the remote event observer on the provided port.
     * <p>
     * TODO: In real usages, both port and ip address must be provided to create a connection.
     * @param socketPort the provided port.
     */
    public SocketRemoteEventObserver(int socketPort){
        try{
            this.socket = new Socket("localhost", socketPort);
            this.out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Sends an update request to the client side.
     *
     * @param updatable specify which part must be updated.
     * @throws RemoteException
     */
    @Override
    public void update(RemoteUpdatable updatable) throws RemoteException {
        try {
            out.writeObject(updatable);
        } catch (IOException e){
            e.printStackTrace();
            throw new RemoteException(e.getMessage());
        }
    }

    /**
     * Shut down this observer.
     * @throws RemoteException
     */
    @Override
    public void unexport() throws RemoteException {
        try {
            this.out.close();
            this.socket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public int getPort() {
        return socket.getPort();
    }
}
