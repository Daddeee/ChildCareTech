package ChildCareTech.network.RMI;

import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.RemoteEventObserver;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIRemoteEventObserver extends UnicastRemoteObject implements RemoteEventObserver, Serializable {
    public RMIRemoteEventObserver() throws RemoteException {}

    @Override
    public void update(EventDTO eventDTO) {
        System.out.println(eventDTO.getName());
    }
}
