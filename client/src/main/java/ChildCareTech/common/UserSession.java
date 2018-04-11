package ChildCareTech.common;

import ChildCareTech.common.DTO.*;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.common.exceptions.UpdateFailedException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface UserSession extends Remote {

    void saveKid(KidDTO kid) throws RemoteException;
    void saveAdult(AdultDTO adult) throws  RemoteException, AddFailedException;
    void saveSupplier(SupplierDTO supplier) throws RemoteException, AddFailedException;
    void savePediatrist(PediatristDTO pediatrist) throws RemoteException, AddFailedException;
    void saveStaff(StaffDTO supplier) throws  RemoteException, AddFailedException;
    void saveTrip(TripDTO trip) throws RemoteException, AddFailedException;

    void updateTrip(TripDTO oldTripDTO, TripDTO newTripDTO) throws RemoteException, UpdateFailedException;

    void removeTrip(TripDTO trip) throws RemoteException;

    List<KidDTO> getAllKids() throws RemoteException;
    List<AdultDTO> getAllAdults() throws RemoteException;
    List<TripDTO> getAllTrips() throws RemoteException;

    void logout() throws RemoteException;
}


