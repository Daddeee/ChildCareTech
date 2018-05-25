package ChildCareTech.network.socket;

import ChildCareTech.common.*;
import ChildCareTech.common.DTO.*;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.common.exceptions.CheckpointFailedException;
import ChildCareTech.common.exceptions.UpdateFailedException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class SocketUserSessionFacade implements UserSessionFacade {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String userName;

    public SocketUserSessionFacade(Socket socket, ObjectOutputStream out, ObjectInputStream in, String userName) {
        this.socket = socket;
        this.in = in;
        this.out = out;
        this.userName = userName;
    }

    @Override
    public void addContactToKid(KidDTO arg0, AdultDTO arg1) throws RemoteException{
        SocketRequest request = new SocketRequest(SocketRequestType.ADD_CONTACT_TO_KID, arg0, arg1);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
    }

    @Override
    public void removeContactFromKid(KidDTO arg0, AdultDTO arg1) throws RemoteException{
        SocketRequest request = new SocketRequest(SocketRequestType.REMOVE_CONTACT_FROM_KID, arg0, arg1);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
    }

    @Override
    public void updateStaffMember(StaffDTO arg0) throws RemoteException, UpdateFailedException {
        SocketRequest request = new SocketRequest(SocketRequestType.UPDATE_STAFF_MEMBER, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (UpdateFailedException) response.returnValue;
    }

    @Override
    public void removePediatrist(PediatristDTO arg0) throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.REMOVE_PEDIATRIST, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
    }

    @Override
    public void removeStaff(StaffDTO arg0) throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.REMOVE_STAFF, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
    }

    @Override
    public void removeSupplier(SupplierDTO arg0) throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.REMOVE_SUPPLIER, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
    }

    @Override
    public void saveTrip(TripDTO arg0) throws RemoteException, AddFailedException {
        SocketRequest request = new SocketRequest(SocketRequestType.SAVE_TRIP, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (AddFailedException) response.returnValue;
    }

    @Override
    public void updateTrip(TripDTO arg0) throws RemoteException, UpdateFailedException {
        SocketRequest request = new SocketRequest(SocketRequestType.UPDATE_TRIP, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (UpdateFailedException) response.returnValue;
    }

    @Override
    public TripDTO getTrip(int arg0) throws RemoteException, NoSuchElementException {
        SocketRequest request = new SocketRequest(SocketRequestType.GET_TRIP, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (NoSuchElementException) response.returnValue;
        return (TripDTO) response.returnValue;
    }

    @Override
    public void updateKid(KidDTO arg0) throws RemoteException, UpdateFailedException {
        SocketRequest request = new SocketRequest(SocketRequestType.UPDATE_KID, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (UpdateFailedException) response.returnValue;
    }

    @Override
    public void updateAdult(AdultDTO arg0) throws RemoteException, UpdateFailedException {
        SocketRequest request = new SocketRequest(SocketRequestType.UPDATE_ADULT, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (UpdateFailedException) response.returnValue;
    }

    @Override
    public void updatePediatrist(PediatristDTO arg0) throws RemoteException, UpdateFailedException {
        SocketRequest request = new SocketRequest(SocketRequestType.UPDATE_PEDIATRIST, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (UpdateFailedException) response.returnValue;
    }

    @Override
    public void updateSupplier(SupplierDTO arg0) throws RemoteException, UpdateFailedException {
        SocketRequest request = new SocketRequest(SocketRequestType.UPDATE_SUPPLIER, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (UpdateFailedException) response.returnValue;
    }

    @Override
    public void removeTrip(TripDTO arg0) throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.REMOVE_TRIP, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
    }

    @Override
    public void removeRoute(RouteDTO arg0) throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.REMOVE_ROUTE, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
    }

    @Override
    public void updateRouteEvent(RouteDTO arg0) throws RemoteException, UpdateFailedException {
        SocketRequest request = new SocketRequest(SocketRequestType.UPDATE_ROUTE_EVENT, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (UpdateFailedException) response.returnValue;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<KidDTO> getAllKids() throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.GET_ALL_KIDS);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
        return (List<KidDTO>) response.returnValue;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<KidDTO> getAvailableKids(TripDTO arg0) throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.GET_AVAILABLE_KIDS, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
        return (Collection<KidDTO>) response.returnValue;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<AdultDTO> getAllAdults() throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.GET_ALL_ADULTS);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
        return (List<AdultDTO>) response.returnValue;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<AdultDTO> getAllAdultsExclusive() throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.GET_ALL_ADULTS_EX);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
        return (List<AdultDTO>) response.returnValue;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PediatristDTO> getAllPediatrists() throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.GET_ALL_PEDIATRISTS);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
        return (List<PediatristDTO>) response.returnValue;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<StaffDTO> getAllStaff() throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.GET_ALL_STAFF);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
        return (List<StaffDTO>) response.returnValue;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<SupplierDTO> getAllSuppliers() throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.GET_ALL_SUPPLIERS);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
        return (List<SupplierDTO>) response.returnValue;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<TripDTO> getAllTrips() throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.GET_ALL_TRIPS);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
        return (List<TripDTO>) response.returnValue;
    }

    @Override
    public void saveTripPartecipation(TripPartecipationDTO arg0) throws RemoteException, AddFailedException {
        SocketRequest request = new SocketRequest(SocketRequestType.SAVE_TRIP_PARTECIPATION, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (AddFailedException) response.returnValue;
    }

    @Override
    public void removeTripPartecipation(TripPartecipationDTO arg0) throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.REMOVE_TRIP_PARTECIPATION, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
    }

    @Override
    public void saveTripBusRelation(TripDTO arg0, BusDTO arg1) throws RemoteException, AddFailedException {
        SocketRequest request = new SocketRequest(SocketRequestType.SAVE_TRIP_BUS_RELATION, arg0, arg1);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (AddFailedException) response.returnValue;
    }

    @Override
    public void removeTripBusRelation(TripDTO arg0, BusDTO arg1) throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.REMOVE_TRIP_BUS_RELATION, arg0, arg1);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
    }

    @Override
    public void saveBus(BusDTO arg0) throws RemoteException, AddFailedException {
        SocketRequest request = new SocketRequest(SocketRequestType.SAVE_BUS, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (AddFailedException) response.returnValue;
    }

    @Override
    public void removeBus(BusDTO arg0) throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.REMOVE_BUS, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
    }

    @Override
    public void updateBus(BusDTO arg0) throws RemoteException, UpdateFailedException {
        SocketRequest request = new SocketRequest(SocketRequestType.UPDATE_BUS, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (UpdateFailedException) response.returnValue;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<BusDTO> getAllBuses() throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.GET_ALL_BUSES);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
        return (List<BusDTO>) response.returnValue;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<BusDTO> getAvailableBuses(TripDTO arg0) throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.GET_AVAILABLE_BUSES, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
        return (Collection<BusDTO>) response.returnValue;
    }

    @Override
    public void saveFood(FoodDTO arg0) throws RemoteException, AddFailedException {
        SocketRequest request = new SocketRequest(SocketRequestType.SAVE_FOOD, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (AddFailedException) response.returnValue;
    }

    @Override
    public void updateFood(FoodDTO arg0) throws RemoteException, UpdateFailedException {
        SocketRequest request = new SocketRequest(SocketRequestType.UPDATE_FOOD, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (UpdateFailedException) response.returnValue;
    }

    @Override
    public void removeFood(FoodDTO arg0) throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.REMOVE_FOOD, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<FoodDTO> getAllFoods() throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.GET_ALL_FOODS);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
        return (List<FoodDTO>) response.returnValue;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<FoodDTO> getAvailableFoods(PersonDTO arg0) throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.GET_AVAILABLE_FOODS, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
        return (Collection<FoodDTO>) response.returnValue;
    }

    @Override
    public void addAllergy(PersonDTO arg0, FoodDTO arg1) throws RemoteException, AddFailedException {
        SocketRequest request = new SocketRequest(SocketRequestType.ADD_ALLERGY, arg0, arg1);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (AddFailedException) response.returnValue;
    }

    @Override
    public void removeAllergy(PersonDTO arg0, FoodDTO arg1) throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.REMOVE_ALLERGY, arg0, arg1);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
    }

    @Override
    public PersonDTO getPerson(String arg0) throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.GET_PERSON, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
        return (PersonDTO) response.returnValue;
    }

    @Override
    public void saveAdult(AdultDTO arg0) throws RemoteException, AddFailedException {
        SocketRequest request = new SocketRequest(SocketRequestType.SAVE_ADULT, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (AddFailedException) response.returnValue;
    }

    @Override
    public void saveSupplier(SupplierDTO arg0) throws RemoteException, AddFailedException {
        SocketRequest request = new SocketRequest(SocketRequestType.SAVE_SUPPLIER, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (AddFailedException) response.returnValue;
    }

    @Override
    public void removeAdult(AdultDTO arg0) throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.REMOVE_ADULT, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
    }

    @Override
    public void savePediatrist(PediatristDTO arg0) throws RemoteException, AddFailedException {
        SocketRequest request = new SocketRequest(SocketRequestType.SAVE_PEDIATRIST, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (AddFailedException) response.returnValue;
    }

    @Override
    public void saveStaff(StaffDTO arg0) throws RemoteException, AddFailedException {
        SocketRequest request = new SocketRequest(SocketRequestType.SAVE_STAFF, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (AddFailedException) response.returnValue;
    }

    @Override
    public void removeKid(KidDTO arg0) throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.REMOVE_KID, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
    }

    @Override
    public void saveKid(KidDTO arg0) throws RemoteException, AddFailedException {
        SocketRequest request = new SocketRequest(SocketRequestType.SAVE_KID, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (AddFailedException) response.returnValue;
    }

    @Override
    public boolean isFirstEverStartup() throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.IS_FIRST_EVER_STARTUP);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
        return (boolean) response.returnValue;
    }

    @Override
    public void setFirstEverStartup(boolean arg0) throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.SET_FIRST_EVER_STARTUP, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
    }

    @Override
    public void generateDays(DayGenerationSettingsDTO arg0) throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.GENERATE_DAYS, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
    }

    @Override
    public void triggerDailyScheduling() throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.TRIGGER_DAILY_SCHEDULING);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
    }

    @Override
    public void saveCanteen(CanteenDTO arg0, List<LocalTime> arg1, List<LocalTime> arg2) throws RemoteException, AddFailedException {
        SocketRequest request = new SocketRequest(SocketRequestType.SAVE_CANTEEN, arg0, (Serializable) arg1, (Serializable) arg2);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (AddFailedException) response.returnValue;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<java.lang.String> getAllCanteenNames() throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.GET_ALL_CANTEEN_NAMES);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
        return (List<java.lang.String>) response.returnValue;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<CanteenDTO> getAllCanteenes() throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.GET_ALL_CANTEENES);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
        return (List<CanteenDTO>) response.returnValue;
    }

    @Override
    public CanteenDTO getCanteenByName(String arg0) throws NoSuchElementException, RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.GET_CANTEEN_BY_NAME, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (NoSuchElementException) response.returnValue;
        return (CanteenDTO) response.returnValue;
    }

    @Override
    public void removeCanteen(CanteenDTO arg0) throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.REMOVE_CANTEEN, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<DishDTO> getAllDishes() throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.GET_ALL_DISHES);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
        return (List<DishDTO>) response.returnValue;
    }

    @Override
    public void createDish(DishDTO arg0) throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.CREATE_DISH, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
    }

    @Override
    public void updateDish(DishDTO arg0) throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.UPDATE_DISH, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
    }

    @Override
    public void deleteDish(DishDTO arg0) throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.DELETE_DISH, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
    }

    @Override
    public void createMenu(MealDTO arg0) throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.CREATE_MENU, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
    }

    @Override
    public void updateMenu(MealDTO arg0) throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.UPDATE_MENU, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
    }

    @Override
    public void validateMenu(MenuDTO arg0) throws RemoteException, UpdateFailedException {
        SocketRequest request = new SocketRequest(SocketRequestType.VALIDATE_MENU, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (UpdateFailedException) response.returnValue;
    }

    @Override
    public void removeDishFromMenu(MenuDTO arg0, DishDTO arg1) throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.REMOVE_DISH_FROM_MENU, arg0, arg1);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
    }

    @Override
    public void addDishToMenu(MenuDTO arg0, DishDTO arg1) throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.ADD_DISH_TO_MENU, arg0, arg1);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
    }

    @Override
    public WorkDayDTO getCurrentWorkDay() throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.GET_CURRENT_WORK_DAY);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
        return (WorkDayDTO) response.returnValue;
    }

    @Override
    public WorkDayDTO getWorkDay(LocalDate arg0) throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.GET_WORK_DAY, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
        return (WorkDayDTO) response.returnValue;
    }

    @Override
    public java.time.LocalDate getMinSavedDate() throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.GET_MIN_SAVED_DATE);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
        return (java.time.LocalDate) response.returnValue;
    }

    @Override
    public java.time.LocalDate getMaxSavedDate() throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.GET_MAX_SAVED_DATE);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
        return (java.time.LocalDate) response.returnValue;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<CheckpointDTO> getEventCheckpoints(EventDTO arg0) throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.GET_EVENT_CHECKPOINTS, arg0);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
        return (Set<CheckpointDTO>) response.returnValue;
    }

    @Override
    public void saveCheckpoint(String arg0, EventDTO arg1, LocalTime arg2) throws CheckpointFailedException, RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.SAVE_CHECKPOINT, arg0, arg1, arg2);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (CheckpointFailedException) response.returnValue;
    }

    @Override
    public void logout() throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.LOGOUT);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
    }

    @Override
    public void addRemoteEventObserver(RemoteEventObserver observer) throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.ADD_OBSERVER, observer.getPort(), this.userName);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
    }

    @Override
    public void removeRemoteEventObserver(RemoteEventObserver observer) throws RemoteException {
        SocketRequest request = new SocketRequest(SocketRequestType.REMOVE_OBSERVER, this.userName);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (RemoteException) response.returnValue;
    }
    @Override
    public void saveTripCheckpoint(String fiscalCode, EventDTO eventDTO, LocalTime time, String busPlate, TripDTO trip) throws RemoteException, CheckpointFailedException {
        SocketRequest request = new SocketRequest(SocketRequestType.SAVE_TRIP_CHECKPOINT, fiscalCode, eventDTO, time, busPlate, trip);
        SocketResponse response;

        response = getSocketResponse(request);

        if(response.responseType.equals(SocketResponseType.FAIL))
            throw (CheckpointFailedException) response.returnValue;
    }

    private SocketResponse getSocketResponse(SocketRequest request) throws RemoteException {
        SocketResponse response;
        try{
            out.writeObject(request);
            response = (SocketResponse)in.readObject();
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RemoteException(e.getMessage());
        }
        return response;
    }
}
