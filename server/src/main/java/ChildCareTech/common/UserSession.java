package ChildCareTech.common;

import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.exceptions.AddFailedException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface UserSession extends Remote {
    void saveKid(KidDTO kid) throws RemoteException;
    void saveTrip(TripDTO trip) throws RemoteException, AddFailedException;
    List<TripDTO> getAllTrips() throws RemoteException;
    void logout() throws RemoteException;
    List<KidDTO> getAllKids() throws RemoteException;
}
