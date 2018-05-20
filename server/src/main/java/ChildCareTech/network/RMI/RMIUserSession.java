package ChildCareTech.network.RMI;

import ChildCareTech.common.DTO.*;
import ChildCareTech.common.RemoteEventObserver;
import ChildCareTech.common.UserSession;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.common.exceptions.CheckpointFailedException;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.controller.*;
import ChildCareTech.model.entities.*;
import ChildCareTech.utils.*;
import ChildCareTech.utils.DTO.DTOFactory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class RMIUserSession extends UnicastRemoteObject implements UserSession {
    private User user;
    private MenuController menuController;
    private DishController dishController;
    private CanteenController canteenController;
    private PersonController personController;
    private FoodController foodController;
    private BusController busController;
    private WorkDayController workDayController;
    private KidController kidController;
    private AdultController adultController;
    private PediatristController pediatristController;
    private StaffController staffController;
    private SupplierController supplierController;
    private TripController tripController;
    private RouteController routeController;
    private CheckpointController checkpointController;
    private TripPartecipationController tripPartecipationController;
    private WorkDayGenerationController workDayGenerationController;

    public RMIUserSession(User user) throws RemoteException {
        this.user = user;
        this.menuController = new MenuController();
        this.dishController = new DishController();
        this.canteenController = new CanteenController();
        this.personController = new PersonController();
        this.foodController = new FoodController();
        this.busController = new BusController();
        this.workDayController = new WorkDayController();
        this.kidController = new KidController();
        this.adultController = new AdultController();
        this.pediatristController = new PediatristController();
        this.staffController = new StaffController();
        this.supplierController = new SupplierController();
        this.tripController = new TripController();
        this.routeController = new RouteController();
        this.checkpointController = new CheckpointController();
        this.tripPartecipationController = new TripPartecipationController();
        this.workDayGenerationController = new WorkDayGenerationController();
       
    }

    @Override
    public List<CanteenDTO> getAllCanteenes() throws RemoteException {
        return canteenController.doGetAllCanteenes();
    }

    @Override
    public void updateAdult(AdultDTO adult) throws RemoteException, UpdateFailedException {
        adultController.doUpdateAdult(adult);
    }

    @Override
    public void updatePediatrist(PediatristDTO pediatristDTO) throws RemoteException, UpdateFailedException {
        pediatristController.doUpdatePediatrist(pediatristDTO);
    }

    @Override
    public void updateStaffMember(StaffDTO staffDTO) throws RemoteException, UpdateFailedException {
        staffController.doUpdateStaffMember(staffDTO);
    }

    @Override
    public void updateSupplier(SupplierDTO supplierDTO) throws RemoteException, UpdateFailedException {
        supplierController.doUpdateSupplier(supplierDTO);
    }

    @Override
    public void addDishToMenu(MenuDTO menuDTO, DishDTO dishDTO) {
        menuController.doAddDishToMenu(menuDTO, dishDTO);
    }

    @Override
    public void removeDishFromMenu(MenuDTO menuDTO, DishDTO dishDTO) {
        menuController.doRemoveDishFromMenu(menuDTO, dishDTO);
    }

    @Override
    public void updateMenu(MealDTO mealDTO) {
        menuController.doUpdateMenu(mealDTO);
    }

    @Override
    public void createMenu(MealDTO mealDTO) {
        menuController.doCreateMenu(mealDTO);
    }

    @Override
    public void deleteDish(DishDTO dishDTO) {
        dishController.doDeleteDish(dishDTO);
    }

    @Override
    public void updateDish(DishDTO dishDTO) {
        dishController.doUpdateDish(dishDTO);
    }

    @Override
    public void createDish(DishDTO dishDTO) {
        dishController.doCreateDish(dishDTO);
    }

    @Override
    public List<DishDTO> getAllDishes() {
        return dishController.doGetAllDishes();
    }

    @Override
    public CanteenDTO getCanteenByName(String name) throws NoSuchElementException {
        return canteenController.doGetCanteenByName(name);
    }

    @Override
    public List<String> getAllCanteenNames() {
        return canteenController.doGetAllCanteenNames();
    }

    @Override
    public void removeCanteen(CanteenDTO canteenDTO) {
        canteenController.doRemoveCanteen(canteenDTO);
    }

    @Override
    public void saveCanteen(CanteenDTO canteenDTO, List<LocalTime> entryTimeList, List<LocalTime> exitTimeList) throws AddFailedException{
        canteenController.doSaveCanteen(canteenDTO, entryTimeList, exitTimeList);
    }

    @Override
    public void removeAllergy(PersonDTO personDTO, FoodDTO foodDTO) {
        personController.doRemoveAllergy(personDTO, foodDTO);
    }

    @Override
    public void addAllergy(PersonDTO personDTO, FoodDTO foodDTO) throws AddFailedException{
        personController.doAddAllergy(personDTO, foodDTO);
    }

    @Override
    public PersonDTO getPerson(String fiscalCode) {
        return personController.doGetPerson(fiscalCode);
    }

    @Override
    public Collection<FoodDTO> getAvailableFoods(PersonDTO personDTO) {
        return foodController.doGetAvailableFoods(personDTO);
    }

    public void updateFood(FoodDTO newFoodDTO) throws UpdateFailedException {
        foodController.doUpdateFood(newFoodDTO);
    }

    public void removeFood(FoodDTO foodDTO) throws RemoteException {
        foodController.doRemoveFood(foodDTO);
    }

    @Override
    public void saveFood(FoodDTO foodDTO) throws AddFailedException {
        foodController.doSaveFood(foodDTO);
    }

    @Override
    public List<FoodDTO> getAllFoods() {
        return foodController.doGetAllFoods();
    }

    @Override
    public void removeTripPartecipation(TripPartecipationDTO tripPartecipationDTO) {
        tripPartecipationController.doRemoveTripPartecipation(tripPartecipationDTO);
    }

    @Override
    public void saveTripPartecipation(TripPartecipationDTO tripPartecipationDTO) throws AddFailedException {
        tripPartecipationController.doSaveTripPartecipation(tripPartecipationDTO);
    }

    @Override
    public Set<CheckpointDTO> getEventCheckpoints(EventDTO eventDTO) {
        return checkpointController.doGetEventCheckpoints(eventDTO);
    }

    @Override
    public void saveCheckpoint(String fiscalCode, EventDTO eventDTO, LocalTime time) throws CheckpointFailedException {
        checkpointController.doSaveCheckpoint(fiscalCode, eventDTO, time);
    }

    @Override
    public LocalDate getMinSavedDate() {
        return workDayController.doGetMinSavedDate();
    }

    @Override
    public LocalDate getMaxSavedDate() {
        return workDayController.doGetMaxSavedDate();
    }

    @Override
    public WorkDayDTO getWorkDay(LocalDate date) {
        return workDayController.doGetWorkDay(date);
    }

    @Override
    public void triggerDailyScheduling() throws RemoteException {
        workDayController.doTriggerDailyScheduling();
    }

    @Override
    public WorkDayDTO getCurrentWorkDay() {
        return workDayController.doGetCurrentWorkDay();
    }

    @Override
    public void generateDays(DayGenerationSettingsDTO settings) {
        workDayGenerationController.doGenerateDays(settings);
    }

    @Override
    public void updateRouteEvent(RouteDTO routeDTO) throws RemoteException, UpdateFailedException{
        routeController.doUpdateRouteEvent(routeDTO);
    }

    @Override
    public void removeRoute(RouteDTO routeDTO) throws RemoteException{
        routeController.doRemoveRoute(routeDTO);
    }

    @Override
    public List<AdultDTO> getAllAdultsEx() throws RemoteException {
        return adultController.doGetAllAdultsEx();
    }

    @Override
    public void removeTripBusRelation(TripDTO tripDTO, BusDTO busDTO) {
        tripController.doRemoveTripBusRelation(tripDTO, busDTO);
    }

    @Override
    public void saveTripBusRelation(TripDTO tripDTO, BusDTO busDTO) throws AddFailedException {
        tripController.doSaveTripBusRelation(tripDTO, busDTO);
    }

    @Override
    public TripDTO getTrip(int id) throws NoSuchElementException {
        return tripController.doGetTrip(id);
    }

    @Override
    public void removeTrip(TripDTO tripDTO) {
        tripController.doRemoveTrip(tripDTO);
    }

    @Override
    public void saveTrip(TripDTO tripDTO) throws AddFailedException{
        tripController.doSaveTrip(tripDTO);
    }

    @Override
    public void updateTrip(TripDTO newTripDTO) throws UpdateFailedException{
        tripController.doUpdateTrip(newTripDTO);
    }

    @Override
    public List<TripDTO> getAllTrips() {
        return tripController.doGetAllTrips();
    }

    @Override
    public void updateKid(KidDTO newKidDTO) throws RemoteException, UpdateFailedException {
        kidController.doUpdateKid(newKidDTO);
    }

    @Override
    public void removeKid(KidDTO kidDTO) throws RemoteException {
        kidController.doRemoveKid(kidDTO);
    }

    @Override
    public void saveKid(KidDTO kidDTO) throws AddFailedException {
        kidController.doSaveKid(kidDTO);
    }

    @Override
    public Collection<KidDTO> getAvailableKids(TripDTO tripDTO) {
        return kidController.doGetAvailableKids(tripDTO);
    }

    @Override
    public List<KidDTO> getAllKids() throws RemoteException {
        return kidController.doGetAllKids();
    }

    @Override
    public void saveAdult(AdultDTO adultDTO) throws AddFailedException {
        adultController.doSaveAdult(adultDTO);
    }

    @Override
    public List<AdultDTO> getAllAdults() throws RemoteException {
        return adultController.doGetAllAdults();
    }

    @Override
    public void removeAdult(AdultDTO adultDTO) throws RemoteException {
        adultController.doRemoveAdult(adultDTO);
    }

    @Override
    public void savePediatrist(PediatristDTO pediatristDTO) throws AddFailedException {
        pediatristController.doSavePediatrist(pediatristDTO);
    }

    @Override
    public List<PediatristDTO> getAllPediatrists() throws RemoteException {
        return pediatristController.doGetAllPediatrists();
    }

    @Override
    public void removePediatrist(PediatristDTO pediatristDTO) throws RemoteException {
        pediatristController.doRemovePediatrist(pediatristDTO);
    }

    @Override
    public void removeStaff(StaffDTO staffDTO) throws RemoteException {
        staffController.doRemoveStaff(staffDTO);
    }

    @Override
    public List<StaffDTO> getAllStaff() throws RemoteException {
        return staffController.doGetAllStaff();
    }

    @Override
    public void saveStaff(StaffDTO staffDTO) throws AddFailedException {
        staffController.doSaveStaff(staffDTO);
    }

    @Override
    public void saveSupplier(SupplierDTO supplierDTO) throws AddFailedException {
        supplierController.doSaveSupplier(supplierDTO);
    }

    @Override
    public List<SupplierDTO> getAllSuppliers() throws RemoteException {
        return supplierController.doGetAllSuppliers();
    }

    @Override
    public void removeSupplier(SupplierDTO supplierDTO) throws RemoteException {
        supplierController.doRemoveSupplier(supplierDTO);
    }

    @Override
    public void saveBus(BusDTO busDTO) throws RemoteException, AddFailedException{
        busController.doSaveBus(busDTO);
    }

    @Override
    public void removeBus(BusDTO busDTO) throws RemoteException{
        busController.doRemoveBus(busDTO);
    }

    @Override
    public void updateBus(BusDTO newBusDTO) throws RemoteException, UpdateFailedException{
        busController.doUpdateBus(newBusDTO);
    }

    @Override
    public List<BusDTO> getAllBuses() throws RemoteException{
        return busController.doGetAllBuses();
    }

    @Override
    public Collection<BusDTO> getAvailableBuses(TripDTO tripDTO) {
        return busController.doGetAvailableBuses(tripDTO);
    }

    @Override
    public boolean isFirstEverStartup() {
        return workDayGenerationController.doIsFirstEverStartup();
    }

    @Override
    public void setFirstEverStartup(boolean value) {
        workDayGenerationController.doSetFirstEverStartup(value);
    }

    @Override
    public void addRemoteEventObserver(RemoteEventObserver observer) {
        RemoteEventObservable.getInstance().addObserver(observer);
    }

    @Override
    public void removeRemoteEventObserver(RemoteEventObserver observer) {
        RemoteEventObservable.getInstance().removeObserver(observer);
    }

    @Override
    public void logout() throws RemoteException {
        UserController.removeSession(user);
        UnicastRemoteObject.unexportObject(this, true);
    }
}
