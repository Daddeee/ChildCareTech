package ChildCareTech.common;

import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.TripDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface UserSession extends Remote {
    void logout() throws RemoteException;

    List<TripDTO> getAllTrips() throws RemoteException;

    void saveKid(KidDTO kid) throws RemoteException;
    List<KidDTO> getAllKids() throws RemoteException;
}
