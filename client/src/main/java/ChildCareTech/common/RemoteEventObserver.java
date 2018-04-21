package ChildCareTech.common;

import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.DTO.WorkDayDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RemoteEventObserver extends Remote {
    void update(WorkDayDTO workDayDTO, List<TripDTO> tripDTOS) throws RemoteException;
    void unexport() throws RemoteException;
}
