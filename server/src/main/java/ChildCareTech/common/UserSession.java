package ChildCareTech.common;

import ChildCareTech.common.DTO.*;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.common.exceptions.CheckpointFailedException;
import ChildCareTech.common.exceptions.UpdateFailedException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public interface UserSession extends Remote {

    void saveKid(KidDTO kid) throws RemoteException, AddFailedException;
    void saveAdult(AdultDTO adult) throws  RemoteException, AddFailedException;
    void saveSupplier(SupplierDTO supplier) throws RemoteException, AddFailedException;
    void savePediatrist(PediatristDTO pediatrist) throws RemoteException, AddFailedException;
    void saveStaff(StaffDTO supplier) throws  RemoteException, AddFailedException;
    void removeAdult(AdultDTO adult) throws RemoteException;
    void removePediatrist(PediatristDTO pediatristDTO) throws RemoteException;
    void removeStaffMember(StaffDTO staffDTO) throws RemoteException;
    void removeSupplier(SupplierDTO supplierDTO) throws RemoteException;
    void saveTrip(TripDTO trip) throws RemoteException, AddFailedException;
    void updateTrip(TripDTO newTripDTO) throws RemoteException, UpdateFailedException;
    TripDTO getTrip(int id) throws RemoteException, NoSuchElementException;
    void removeTrip(TripDTO trip) throws RemoteException;

    void removeRoute(RouteDTO route) throws RemoteException;
    List<KidDTO> getAllKids() throws RemoteException;
    Collection<KidDTO> getAvailableKids(TripDTO tripDTO) throws RemoteException;

    List<AdultDTO> getAllAdults() throws RemoteException;
    List<AdultDTO> getAllAdultsEx() throws RemoteException;
    List<PediatristDTO> getAllPediatrists() throws RemoteException;
    List<StaffDTO> getAllStaffMembers() throws RemoteException;
    List<SupplierDTO> getAllSuppliers() throws RemoteException;
    List<TripDTO> getAllTrips() throws RemoteException;

    void saveTripPartecipation(TripPartecipationDTO tripPartecipationDTO) throws RemoteException, AddFailedException;
    void removeTripPartecipation(TripPartecipationDTO tripPartecipationDTO) throws RemoteException;
    void saveTripBusRelation(TripDTO tripDTO, BusDTO busDTO) throws RemoteException, AddFailedException;

    void saveBus(BusDTO bus) throws RemoteException, AddFailedException;
    void removeBus(BusDTO bus) throws RemoteException;
    void updateBus(BusDTO newBus) throws RemoteException, UpdateFailedException;
    List<BusDTO> getAllBuses() throws RemoteException;
    Collection<BusDTO> getAvailableBuses(TripDTO tripDTO) throws RemoteException;

    boolean isFirstEverStartup() throws RemoteException;
    void setFirstEverStartup(boolean value) throws RemoteException;

    void generateDays(DayGenerationSettingsDTO settings) throws RemoteException;
    WorkDayDTO getCurrentWorkDay() throws RemoteException;
    WorkDayDTO getWorkDay(LocalDate date) throws RemoteException;
    LocalDate getMinSavedDate() throws RemoteException;
    LocalDate getMaxSavedDate() throws RemoteException;

    Set<CheckpointDTO> getEventCheckpoints(EventDTO eventDTO) throws RemoteException;
    void saveCheckpoint(String fiscalCode, EventDTO event, LocalTime time) throws CheckpointFailedException, RemoteException;

    void logout() throws RemoteException;
}
