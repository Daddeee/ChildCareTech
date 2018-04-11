package ChildCareTech.common;

import ChildCareTech.common.DTO.*;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.model.entities.Bus;

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

    void updateTrip(TripDTO newTripDTO) throws RemoteException, UpdateFailedException;

    void removeTrip(TripDTO trip) throws RemoteException;
    void removeRoute(RouteDTO route) throws RemoteException;

    List<KidDTO> getAllKids() throws RemoteException;
    List<AdultDTO> getAllAdults() throws RemoteException;
    List<TripDTO> getAllTrips() throws RemoteException;

    void saveBus(BusDTO bus) throws RemoteException, AddFailedException;
    void removeBus(BusDTO bus) throws RemoteException;
    void updateBus(BusDTO oldBus, BusDTO newBus) throws RemoteException, UpdateFailedException;
    List<BusDTO> getAllBuses() throws RemoteException;

    void logout() throws RemoteException;
}
