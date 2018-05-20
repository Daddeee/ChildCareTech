package ChildCareTech.network.socket;

import ChildCareTech.common.RemoteEventObserver;
import ChildCareTech.common.RemoteUpdatable;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.HashMap;

public class SocketRemoteEventObserver implements RemoteEventObserver {
    public static HashMap<String, SocketRemoteEventObserver> socketRemoteEventObserversMap = new HashMap<>();

    private int socketPort;
    private Socket socket;
    private ObjectOutputStream out;

    public SocketRemoteEventObserver(int socketPort){
        this.socketPort = socketPort;
        try{
            this.socket = new Socket("localhost", socketPort);
            this.out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(RemoteUpdatable updatable) throws RemoteException {
        try {
            out.writeObject(updatable);
        } catch (IOException e){
            e.printStackTrace();
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public void unexport() throws RemoteException {
        try {
            this.out.close();
            this.socket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
