package ChildCareTech.common;

import ChildCareTech.common.DTO.EventDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Observer;

public interface RemoteEventObserver extends Remote {
    void update(EventDTO eventDTO) throws RemoteException;
}
