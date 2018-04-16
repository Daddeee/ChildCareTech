package ChildCareTech.common;

import ChildCareTech.common.DTO.EventDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RemoteEventObserver extends Remote {
    void update(List<EventDTO> eventDTO) throws RemoteException;
}
