package ChildCareTech.network.RMI;

import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.RemoteEventObserver;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RMIRemoteEventObserver extends UnicastRemoteObject implements RemoteEventObserver, Serializable {
    public RMIRemoteEventObserver() throws RemoteException {}

    @Override
    public void update(List<EventDTO> eventDTOs) {
        for(EventDTO e : eventDTOs)
            System.out.println(e.getName());
    }
}
