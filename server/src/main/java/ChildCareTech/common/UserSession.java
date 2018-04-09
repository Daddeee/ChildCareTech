package ChildCareTech.common;

import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.exceptions.AddFailedException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface UserSession extends Remote {
    List<KidDTO> getAllKids() throws RemoteException;

    void saveKid(KidDTO kid) throws RemoteException;
    void saveTrip(TripDTO trip) throws RemoteException, AddFailedException;
    void removeTrip(TripDTO trip) throws RemoteException;

    List<TripDTO> getAllTrips() throws RemoteException;

    void logout() throws RemoteException;
}
