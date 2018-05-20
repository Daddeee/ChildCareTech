package ChildCareTech.network.socket;

import ChildCareTech.common.RemoteEventObserver;
import ChildCareTech.common.RemoteUpdatable;
import ChildCareTech.services.RemoteUpdateService;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;

public class SocketRemoteEventObserver implements Runnable, RemoteEventObserver {
    private int socketPort;
    private ServerSocket serverSocket;
    private RemoteUpdateService remoteUpdateService;

    public SocketRemoteEventObserver(int socketPort) {
        this.socketPort = socketPort;
        this.remoteUpdateService = new RemoteUpdateService();

        boolean availablePort = false;
        while(!availablePort) {
            try {
                this.serverSocket = new ServerSocket(this.socketPort);
                availablePort = true;
            } catch (IOException e) { this.socketPort++; }
        }
    }

    public int getSocketPort() {
        return socketPort;
    }

    public void start() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            Socket socket = serverSocket.accept();
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            while (true) {
                RemoteUpdatable remoteUpdate = (RemoteUpdatable) in.readObject();
                update(remoteUpdate);
            }
        } catch (EOFException e) { //Socket observer unexported, do nothing.
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void update(RemoteUpdatable updatable) throws RemoteException {
        remoteUpdateService.handle(updatable);
    }

    @Override
    public void unexport(){
        try {
            serverSocket.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public int getPort() {
        return socketPort;
    }
}
