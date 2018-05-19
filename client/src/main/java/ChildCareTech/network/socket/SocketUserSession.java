package ChildCareTech.network.socket;

import ChildCareTech.common.DTO.*;
import ChildCareTech.common.UserSession;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.common.exceptions.CheckpointFailedException;
import ChildCareTech.common.exceptions.UpdateFailedException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class SocketUserSession implements UserSession {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public SocketUserSession(Socket socket, ObjectOutputStream out, ObjectInputStream in) {
        this.socket = socket;
        this.in = in;
        this.out = out;
    }

    @Override
    public void saveKid(KidDTO kid) throws RemoteException, AddFailedException {

    }

    @Override
    public void saveAdult(AdultDTO adult) throws RemoteException, AddFailedException {

    }

    @Override
    public void saveSupplier(SupplierDTO supplier) throws RemoteException, AddFailedException {

    }

    @Override
    public void savePediatrist(PediatristDTO pediatrist) throws RemoteException, AddFailedException {

    }

    @Override
    public void saveStaff(StaffDTO staff) throws RemoteException, AddFailedException {

    }

    @Override
    public void removeKid(KidDTO kidDTO) throws RemoteException {

    }

    @Override
    public void removeAdult(AdultDTO adult) throws RemoteException {

    }

    @Override
    public void removePediatrist(PediatristDTO pediatristDTO) throws RemoteException {

    }

    @Override
    public void removeStaff(StaffDTO staffDTO) throws RemoteException {

    }

    @Override
    public void removeSupplier(SupplierDTO supplierDTO) throws RemoteException {

    }

    @Override
    public void saveTrip(TripDTO trip) throws RemoteException, AddFailedException {

    }

    @Override
    public void updateTrip(TripDTO newTripDTO) throws RemoteException, UpdateFailedException {

    }

    @Override
    public TripDTO getTrip(int id) throws RemoteException, NoSuchElementException {
        return null;
    }

    @Override
    public void updateKid(KidDTO newKidDTO) throws RemoteException, UpdateFailedException {

    }

    @Override
    public void updateAdult(AdultDTO adult) throws RemoteException, UpdateFailedException {

    }

    @Override
    public void updatePediatrist(PediatristDTO pediatristDTO) throws RemoteException, UpdateFailedException {

    }

    @Override
    public void updateStaffMember(StaffDTO staffDTO) throws RemoteException, UpdateFailedException {

    }

    @Override
    public void updateSupplier(SupplierDTO supplierDTO) throws RemoteException, UpdateFailedException {

    }

    @Override
    public void removeTrip(TripDTO trip) throws RemoteException {

    }

    @Override
    public void removeRoute(RouteDTO route) throws RemoteException {

    }

    @Override
    public void updateRouteEvent(RouteDTO routeDTO) throws RemoteException, UpdateFailedException {

    }

    @Override
    public List<KidDTO> getAllKids() throws RemoteException {
        return null;
    }

    @Override
    public Collection<KidDTO> getAvailableKids(TripDTO tripDTO) throws RemoteException {
        return null;
    }

    @Override
    public List<AdultDTO> getAllAdults() throws RemoteException {
        return null;
    }

    @Override
    public List<AdultDTO> getAllAdultsEx() throws RemoteException {
        return null;
    }

    @Override
    public List<PediatristDTO> getAllPediatrists() throws RemoteException {
        return null;
    }

    @Override
    public List<StaffDTO> getAllStaff() throws RemoteException {
        return null;
    }

    @Override
    public List<SupplierDTO> getAllSuppliers() throws RemoteException {
        return null;
    }

    @Override
    public List<TripDTO> getAllTrips() throws RemoteException {
        return null;
    }

    @Override
    public void saveTripPartecipation(TripPartecipationDTO tripPartecipationDTO) throws RemoteException, AddFailedException {

    }

    @Override
    public void removeTripPartecipation(TripPartecipationDTO tripPartecipationDTO) throws RemoteException {

    }

    @Override
    public void saveTripBusRelation(TripDTO tripDTO, BusDTO busDTO) throws RemoteException, AddFailedException {

    }

    @Override
    public void removeTripBusRelation(TripDTO tripDTO, BusDTO busDTO) throws RemoteException {

    }

    @Override
    public void saveBus(BusDTO bus) throws RemoteException, AddFailedException {

    }

    @Override
    public void removeBus(BusDTO bus) throws RemoteException {

    }

    @Override
    public void updateBus(BusDTO newBus) throws RemoteException, UpdateFailedException {

    }

    @Override
    public List<BusDTO> getAllBuses() throws RemoteException {
        return null;
    }

    @Override
    public Collection<BusDTO> getAvailableBuses(TripDTO tripDTO) throws RemoteException {
        return null;
    }

    @Override
    public void saveFood(FoodDTO foodDTO) throws RemoteException, AddFailedException {

    }

    @Override
    public void updateFood(FoodDTO newFood) throws RemoteException, UpdateFailedException {

    }

    @Override
    public void removeFood(FoodDTO foodDTO) throws RemoteException {

    }

    @Override
    public List<FoodDTO> getAllFoods() throws RemoteException {
        return null;
    }

    @Override
    public Collection<FoodDTO> getAvailableFoods(PersonDTO personDTO) throws RemoteException {
        return null;
    }

    @Override
    public void addAllergy(PersonDTO personDTO, FoodDTO foodDTO) throws RemoteException, AddFailedException {

    }

    @Override
    public void removeAllergy(PersonDTO personDTO, FoodDTO foodDTO) throws RemoteException {

    }

    @Override
    public PersonDTO getPerson(String fiscalCode) throws RemoteException {
        return null;
    }

    @Override
    public boolean isFirstEverStartup() throws RemoteException {
        return false;
    }

    @Override
    public void setFirstEverStartup(boolean value) throws RemoteException {

    }

    @Override
    public void generateDays(DayGenerationSettingsDTO settings) throws RemoteException {

    }

    @Override
    public void triggerDailyScheduling() throws RemoteException {

    }

    @Override
    public void saveCanteen(CanteenDTO canteenDTO, List<LocalTime> entryTimeList, List<LocalTime> exitTimeList) throws RemoteException, AddFailedException {

    }

    @Override
    public List<String> getAllCanteenNames() throws RemoteException {
        return null;
    }

    @Override
    public List<CanteenDTO> getAllCanteenes() throws RemoteException {
        return null;
    }

    @Override
    public CanteenDTO getCanteenByName(String name) throws NoSuchElementException, RemoteException {
        return null;
    }

    @Override
    public void removeCanteen(CanteenDTO canteenDTO) throws RemoteException {

    }

    @Override
    public List<DishDTO> getAllDishes() throws RemoteException {
        return null;
    }

    @Override
    public void createDish(DishDTO dishDTO) throws RemoteException {

    }

    @Override
    public void updateDish(DishDTO dishDTO) throws RemoteException {

    }

    @Override
    public void deleteDish(DishDTO dishDTO) throws RemoteException {

    }

    @Override
    public void createMenu(MealDTO mealDTO) throws RemoteException {

    }

    @Override
    public void updateMenu(MealDTO mealDTO) throws RemoteException {

    }

    @Override
    public void removeDishFromMenu(MenuDTO menuDTO, DishDTO dishDTO) throws RemoteException {

    }

    @Override
    public void addDishToMenu(MenuDTO menuDTO, DishDTO dishDTO) throws RemoteException {

    }

    @Override
    public WorkDayDTO getCurrentWorkDay() throws RemoteException {
        return null;
    }

    @Override
    public WorkDayDTO getWorkDay(LocalDate date) throws RemoteException {
        return null;
    }

    @Override
    public LocalDate getMinSavedDate() throws RemoteException {
        return null;
    }

    @Override
    public LocalDate getMaxSavedDate() throws RemoteException {
        return null;
    }

    @Override
    public Set<CheckpointDTO> getEventCheckpoints(EventDTO eventDTO) throws RemoteException {
        return null;
    }

    @Override
    public void saveCheckpoint(String fiscalCode, EventDTO event, LocalTime time) throws CheckpointFailedException, RemoteException {

    }

    @Override
    public void logout() throws RemoteException {

    }
}
