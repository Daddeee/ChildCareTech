package ChildCareTech.network.socket;

import ChildCareTech.common.DTO.*;
import ChildCareTech.common.SocketRequest;
import ChildCareTech.common.SocketRequestType;
import ChildCareTech.common.SocketResponse;
import ChildCareTech.common.SocketResponseType;
import ChildCareTech.controller.*;
import ChildCareTech.utils.RemoteEventObservable;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

public class SocketProtocol {
    private HashMap<SocketRequestType, SocketRequestHandler> methodMap;
    private SocketUserSessionFacade socketUserSessionFacade;

    private AdultController adultController;
    private BusController busController;
    private CanteenController canteenController;
    private CheckpointController checkpointController;
    private DishController dishController;
    private FoodController foodController;
    private KidController kidController;
    private MenuController menuController;
    private PediatristController pediatristController;
    private PersonController personController;
    private RouteController routeController;
    private StaffController staffController;
    private TripController tripController;
    private TripPartecipationController tripPartecipationController;
    private SupplierController supplierController;
    private WorkDayController workDayController;
    private WorkDayGenerationController workDayGenerationController;

    public SocketProtocol(SocketUserSessionFacade socketUserSessionFacade) {
        this.methodMap = new HashMap<>();
        this.socketUserSessionFacade = socketUserSessionFacade;

        this.adultController = new AdultController();
        this.busController = new BusController();
        this.canteenController = new CanteenController();
        this.checkpointController = new CheckpointController();
        this.dishController = new DishController();
        this.foodController = new FoodController();
        this.kidController = new KidController();
        this.menuController = new MenuController();
        this.pediatristController = new PediatristController();
        this.personController = new PersonController();
        this.routeController = new RouteController();
        this.staffController = new StaffController();
        this.supplierController = new SupplierController();
        this.tripController = new TripController();
        this.tripPartecipationController = new TripPartecipationController();
        this.workDayController = new WorkDayController();
        this.workDayGenerationController = new WorkDayGenerationController();

        loadProtocol();
    }

    public SocketResponse handleRequest(SocketRequest socketRequest){
        return methodMap.get(socketRequest.requestType).handle(socketRequest);
    }

    private SocketResponse handleLogin(SocketRequest request){
        SocketResponse response;

        try{
            String userName = (String) request.params[0];
            String password = (String) request.params[1];

            socketUserSessionFacade.setUser(UserController.getUser(userName, password));
            UserController.storeSession(socketUserSessionFacade.getUser(), new Object());

            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch (Exception e){
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleLogout(SocketRequest request){
        SocketResponse response;

        try {
            UserController.removeSession(socketUserSessionFacade.getUser());
            socketUserSessionFacade.close();
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch (Exception e){
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleRegister(SocketRequest request){
        SocketResponse response;
        boolean returnValue;

        try {
            String userName = (String) request.params[0];
            String password = (String) request.params[1];

            returnValue = UserController.registerUser(userName, password);

            response = new SocketResponse(SocketResponseType.SUCCESS, returnValue);
        } catch (Exception e){
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleSaveStaff(SocketRequest request) {
        SocketResponse response;


        try{
            StaffDTO arg0 = (StaffDTO) request.params[0];

            staffController.doSaveStaff(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleSaveAdult(SocketRequest request) {
        SocketResponse response;


        try{
            AdultDTO arg0 = (AdultDTO) request.params[0];

            adultController.doSaveAdult(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleSaveTrip(SocketRequest request) {
        SocketResponse response;


        try{
            TripDTO arg0 = (TripDTO) request.params[0];

            tripController.doSaveTrip(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleUpdateTrip(SocketRequest request) {
        SocketResponse response;


        try{
            TripDTO arg0 = (TripDTO) request.params[0];

            tripController.doUpdateTrip(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleGetTrip(SocketRequest request) {
        SocketResponse response;
        TripDTO returnValue;

        try{
            int arg0 = (int) request.params[0];

            returnValue = tripController.doGetTrip(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, returnValue);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleUpdateKid(SocketRequest request) {
        SocketResponse response;


        try{
            KidDTO arg0 = (KidDTO) request.params[0];

            kidController.doUpdateKid(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleUpdateAdult(SocketRequest request) {
        SocketResponse response;


        try{
            AdultDTO arg0 = (AdultDTO) request.params[0];

            adultController.doUpdateAdult(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleUpdatePediatrist(SocketRequest request) {
        SocketResponse response;


        try{
            PediatristDTO arg0 = (PediatristDTO) request.params[0];

            pediatristController.doUpdatePediatrist(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleUpdateStaffMember(SocketRequest request) {
        SocketResponse response;


        try{
            StaffDTO arg0 = (StaffDTO) request.params[0];

            staffController.doUpdateStaffMember(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleUpdateSupplier(SocketRequest request) {
        SocketResponse response;


        try{
            SupplierDTO arg0 = (SupplierDTO) request.params[0];

            supplierController.doUpdateSupplier(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleRemoveTrip(SocketRequest request) {
        SocketResponse response;


        try{
            TripDTO arg0 = (TripDTO) request.params[0];

            tripController.doRemoveTrip(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleRemoveRoute(SocketRequest request) {
        SocketResponse response;


        try{
            RouteDTO arg0 = (RouteDTO) request.params[0];

            routeController.doRemoveRoute(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleUpdateRouteEvent(SocketRequest request) {
        SocketResponse response;


        try{
            RouteDTO arg0 = (RouteDTO) request.params[0];

            routeController.doUpdateRouteEvent(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleGetAllKids(SocketRequest request) {
        SocketResponse response;
        List<KidDTO> returnValue;

        try{

            returnValue = kidController.doGetAllKids();
            response = new SocketResponse(SocketResponseType.SUCCESS, (Serializable) returnValue);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleGetAvailableKids(SocketRequest request) {
        SocketResponse response;
        java.util.Collection<KidDTO> returnValue;

        try{
            TripDTO arg0 = (TripDTO) request.params[0];

            returnValue = kidController.doGetAvailableKids(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, (Serializable) returnValue);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleGetAllAdults(SocketRequest request) {
        SocketResponse response;
        List<AdultDTO> returnValue;

        try{

            returnValue = adultController.doGetAllAdults();
            response = new SocketResponse(SocketResponseType.SUCCESS, (Serializable) returnValue);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleGetAllAdultsEx(SocketRequest request) {
        SocketResponse response;
        List<AdultDTO> returnValue;

        try{

            returnValue = adultController.doGetAllAdultsExclusive();
            response = new SocketResponse(SocketResponseType.SUCCESS, (Serializable) returnValue);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleGetAllPediatrists(SocketRequest request) {
        SocketResponse response;
        List<PediatristDTO> returnValue;

        try{

            returnValue = pediatristController.doGetAllPediatrists();
            response = new SocketResponse(SocketResponseType.SUCCESS, (Serializable) returnValue);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleGetAllStaff(SocketRequest request) {
        SocketResponse response;
        List<StaffDTO> returnValue;

        try{

            returnValue = staffController.doGetAllStaff();
            response = new SocketResponse(SocketResponseType.SUCCESS, (Serializable) returnValue);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleGetAllSuppliers(SocketRequest request) {
        SocketResponse response;
        List<SupplierDTO> returnValue;

        try{

            returnValue = supplierController.doGetAllSuppliers();
            response = new SocketResponse(SocketResponseType.SUCCESS, (Serializable) returnValue);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleGetAllTrips(SocketRequest request) {
        SocketResponse response;
        List<TripDTO> returnValue;

        try{

            returnValue = tripController.doGetAllTrips();
            response = new SocketResponse(SocketResponseType.SUCCESS, (Serializable) returnValue);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleSaveTripPartecipation(SocketRequest request) {
        SocketResponse response;


        try{
            TripPartecipationDTO arg0 = (TripPartecipationDTO) request.params[0];

            tripPartecipationController.doSaveTripPartecipation(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleRemoveTripPartecipation(SocketRequest request) {
        SocketResponse response;


        try{
            TripPartecipationDTO arg0 = (TripPartecipationDTO) request.params[0];

            tripPartecipationController.doRemoveTripPartecipation(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleSaveTripBusRelation(SocketRequest request) {
        SocketResponse response;


        try{
            TripDTO arg0 = (TripDTO) request.params[0];
            BusDTO arg1 = (BusDTO) request.params[1];

            tripController.doSaveTripBusRelation(arg0, arg1);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleRemoveTripBusRelation(SocketRequest request) {
        SocketResponse response;


        try{
            TripDTO arg0 = (TripDTO) request.params[0];
            BusDTO arg1 = (BusDTO) request.params[1];

            tripController.doRemoveTripBusRelation(arg0, arg1);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleSaveBus(SocketRequest request) {
        SocketResponse response;


        try{
            BusDTO arg0 = (BusDTO) request.params[0];

            busController.doSaveBus(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleRemoveBus(SocketRequest request) {
        SocketResponse response;


        try{
            BusDTO arg0 = (BusDTO) request.params[0];

            busController.doRemoveBus(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleUpdateBus(SocketRequest request) {
        SocketResponse response;


        try{
            BusDTO arg0 = (BusDTO) request.params[0];

            busController.doUpdateBus(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleSaveKid(SocketRequest request) {
        SocketResponse response;


        try{
            KidDTO arg0 = (KidDTO) request.params[0];

            kidController.doSaveKid(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleRemoveKid(SocketRequest request) {
        SocketResponse response;


        try{
            KidDTO arg0 = (KidDTO) request.params[0];

            kidController.doRemoveKid(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleRemoveAdult(SocketRequest request) {
        SocketResponse response;


        try{
            AdultDTO arg0 = (AdultDTO) request.params[0];

            adultController.doRemoveAdult(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleRemovePediatrist(SocketRequest request) {
        SocketResponse response;


        try{
            PediatristDTO arg0 = (PediatristDTO) request.params[0];

            pediatristController.doRemovePediatrist(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleRemoveStaff(SocketRequest request) {
        SocketResponse response;


        try{
            StaffDTO arg0 = (StaffDTO) request.params[0];

            staffController.doRemoveStaff(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleRemoveSupplier(SocketRequest request) {
        SocketResponse response;


        try{
            SupplierDTO arg0 = (SupplierDTO) request.params[0];

            supplierController.doRemoveSupplier(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleSaveSupplier(SocketRequest request) {
        SocketResponse response;


        try{
            SupplierDTO arg0 = (SupplierDTO) request.params[0];

            supplierController.doSaveSupplier(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleSavePediatrist(SocketRequest request) {
        SocketResponse response;


        try{
            PediatristDTO arg0 = (PediatristDTO) request.params[0];

            pediatristController.doSavePediatrist(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleGetAllBuses(SocketRequest request) {
        SocketResponse response;
        List<BusDTO> returnValue;

        try{

            returnValue = busController.doGetAllBuses();
            response = new SocketResponse(SocketResponseType.SUCCESS, (Serializable) returnValue);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleGetAvailableBuses(SocketRequest request) {
        SocketResponse response;
        java.util.Collection<BusDTO> returnValue;

        try{
            TripDTO arg0 = (TripDTO) request.params[0];

            returnValue = busController.doGetAvailableBuses(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, (Serializable) returnValue);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleSaveFood(SocketRequest request) {
        SocketResponse response;


        try{
            FoodDTO arg0 = (FoodDTO) request.params[0];

            foodController.doSaveFood(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleUpdateFood(SocketRequest request) {
        SocketResponse response;


        try{
            FoodDTO arg0 = (FoodDTO) request.params[0];

            foodController.doUpdateFood(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleRemoveFood(SocketRequest request) {
        SocketResponse response;


        try{
            FoodDTO arg0 = (FoodDTO) request.params[0];

            foodController.doRemoveFood(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleGetAllFoods(SocketRequest request) {
        SocketResponse response;
        List<FoodDTO> returnValue;

        try{

            returnValue = foodController.doGetAllFoods();
            response = new SocketResponse(SocketResponseType.SUCCESS, (Serializable) returnValue);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleGetAvailableFoods(SocketRequest request) {
        SocketResponse response;
        java.util.Collection<FoodDTO> returnValue;

        try{
            PersonDTO arg0 = (PersonDTO) request.params[0];

            returnValue = foodController.doGetAvailableFoods(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, (Serializable) returnValue);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleAddAllergy(SocketRequest request) {
        SocketResponse response;


        try{
            PersonDTO arg0 = (PersonDTO) request.params[0];
            FoodDTO arg1 = (FoodDTO) request.params[1];

            personController.doAddAllergy(arg0, arg1);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleRemoveAllergy(SocketRequest request) {
        SocketResponse response;


        try{
            PersonDTO arg0 = (PersonDTO) request.params[0];
            FoodDTO arg1 = (FoodDTO) request.params[1];

            personController.doRemoveAllergy(arg0, arg1);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleGetPerson(SocketRequest request) {
        SocketResponse response;
        PersonDTO returnValue;

        try{
            String arg0 = (String) request.params[0];

            returnValue = personController.doGetPerson(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, returnValue);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleIsFirstEverStartup(SocketRequest request) {
        SocketResponse response;
        boolean returnValue;

        try{

            returnValue = workDayGenerationController.doIsFirstEverStartup();
            response = new SocketResponse(SocketResponseType.SUCCESS, returnValue);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleSetFirstEverStartup(SocketRequest request) {
        SocketResponse response;


        try{
            boolean arg0 = (boolean) request.params[0];

            workDayGenerationController.doSetFirstEverStartup(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleGenerateDays(SocketRequest request) {
        SocketResponse response;


        try{
            DayGenerationSettingsDTO arg0 = (DayGenerationSettingsDTO) request.params[0];

            workDayGenerationController.doGenerateDays(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleTriggerDailyScheduling(SocketRequest request) {
        SocketResponse response;


        try{
            
            workDayController.doTriggerDailyScheduling();
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleSaveCanteen(SocketRequest request) {
        SocketResponse response;


        try{
            CanteenDTO arg0 = (CanteenDTO) request.params[0];
            List<LocalTime> arg1 = (List<LocalTime>) request.params[1];
            List<LocalTime> arg2 = (List<LocalTime>) request.params[2];

            canteenController.doSaveCanteen(arg0, arg1, arg2);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleGetAllCanteenNames(SocketRequest request) {
        SocketResponse response;
        List<java.lang.String> returnValue;

        try{

            returnValue = canteenController.doGetAllCanteenNames();
            response = new SocketResponse(SocketResponseType.SUCCESS, (Serializable) returnValue);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleGetAllCanteenes(SocketRequest request) {
        SocketResponse response;
        List<CanteenDTO> returnValue;

        try{

            returnValue = canteenController.doGetAllCanteenes();
            response = new SocketResponse(SocketResponseType.SUCCESS, (Serializable) returnValue);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleGetCanteenByName(SocketRequest request) {
        SocketResponse response;
        CanteenDTO returnValue;

        try{
            String arg0 = (String) request.params[0];

            returnValue = canteenController.doGetCanteenByName(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, returnValue);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleRemoveCanteen(SocketRequest request) {
        SocketResponse response;


        try{
            CanteenDTO arg0 = (CanteenDTO) request.params[0];

            canteenController.doRemoveCanteen(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleGetAllDishes(SocketRequest request) {
        SocketResponse response;
        List<DishDTO> returnValue;

        try{

            returnValue = dishController.doGetAllDishes();
            response = new SocketResponse(SocketResponseType.SUCCESS, (Serializable) returnValue);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleCreateDish(SocketRequest request) {
        SocketResponse response;


        try{
            DishDTO arg0 = (DishDTO) request.params[0];

            dishController.doCreateDish(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleUpdateDish(SocketRequest request) {
        SocketResponse response;


        try{
            DishDTO arg0 = (DishDTO) request.params[0];

            dishController.doUpdateDish(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleDeleteDish(SocketRequest request) {
        SocketResponse response;


        try{
            DishDTO arg0 = (DishDTO) request.params[0];

            dishController.doDeleteDish(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleCreateMenu(SocketRequest request) {
        SocketResponse response;


        try{
            MealDTO arg0 = (MealDTO) request.params[0];

            menuController.doCreateMenu(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleUpdateMenu(SocketRequest request) {
        SocketResponse response;


        try{
            MealDTO arg0 = (MealDTO) request.params[0];

            menuController.doUpdateMenu(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleRemoveDishFromMenu(SocketRequest request) {
        SocketResponse response;


        try{
            MenuDTO arg0 = (MenuDTO) request.params[0];
            DishDTO arg1 = (DishDTO) request.params[1];

            menuController.doRemoveDishFromMenu(arg0, arg1);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleAddDishToMenu(SocketRequest request) {
        SocketResponse response;


        try{
            MenuDTO arg0 = (MenuDTO) request.params[0];
            DishDTO arg1 = (DishDTO) request.params[1];

            menuController.doAddDishToMenu(arg0, arg1);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleGetCurrentWorkDay(SocketRequest request) {
        SocketResponse response;
        WorkDayDTO returnValue;

        try{

            returnValue = workDayController.doGetCurrentWorkDay();
            response = new SocketResponse(SocketResponseType.SUCCESS, returnValue);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleGetWorkDay(SocketRequest request) {
        SocketResponse response;
        WorkDayDTO returnValue;

        try{
            LocalDate arg0 = (LocalDate) request.params[0];

            returnValue = workDayController.doGetWorkDay(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, returnValue);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleGetMinSavedDate(SocketRequest request) {
        SocketResponse response;
        LocalDate returnValue;

        try{

            returnValue = workDayController.doGetMinSavedDate();
            response = new SocketResponse(SocketResponseType.SUCCESS, returnValue);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleGetMaxSavedDate(SocketRequest request) {
        SocketResponse response;
        LocalDate returnValue;

        try{

            returnValue = workDayController.doGetMaxSavedDate();
            response = new SocketResponse(SocketResponseType.SUCCESS, returnValue);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleGetEventCheckpoints(SocketRequest request) {
        SocketResponse response;
        java.util.Set<CheckpointDTO> returnValue;

        try{
            EventDTO arg0 = (EventDTO) request.params[0];

            returnValue = checkpointController.doGetEventCheckpoints(arg0);
            response = new SocketResponse(SocketResponseType.SUCCESS, (Serializable) returnValue);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleSaveCheckpoint(SocketRequest request) {
        SocketResponse response;


        try{
            String arg0 = (String) request.params[0];
            EventDTO arg1 = (EventDTO) request.params[1];
            LocalTime arg2 = (LocalTime) request.params[2];

            checkpointController.doSaveCheckpoint(arg0, arg1, arg2);
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleAddRemoteEventObserver(SocketRequest request) {
        SocketResponse response;

        try{
            int port = (Integer) request.params[0];
            String userName = (String) request.params[1];

            SocketRemoteEventObserver observer = new SocketRemoteEventObserver(port);
            RemoteEventObservable.getInstance().addObserver(observer);
            SocketRemoteEventObserver.socketRemoteEventObserversMap.put(userName, observer);

            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleRemoveRemoteEventObserver(SocketRequest request) {
        SocketResponse response;

        try{
            String userName = (String) request.params[0];

            SocketRemoteEventObserver observer = SocketRemoteEventObserver.socketRemoteEventObserversMap.get(userName);
            RemoteEventObservable.getInstance().removeObserver(observer);
            observer.unexport();

            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch(Exception e) {
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleAddContactToKid(SocketRequest request) {
        SocketResponse response;

        try{
            KidDTO arg0 = (KidDTO) request.params[0];
            AdultDTO arg1 = (AdultDTO) request.params[1];

            kidController.doAddContactToKid(arg0, arg1);

            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch (Exception e){
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleRemoveContactFromKid(SocketRequest request) {
        SocketResponse response;

        try{
            KidDTO arg0 = (KidDTO) request.params[0];
            AdultDTO arg1 = (AdultDTO) request.params[1];

            kidController.doRemoveContactFromKid(arg0, arg1);

            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch (Exception e){
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleValidateMenu(SocketRequest request) {
        SocketResponse response;

        try{
            MenuDTO arg0 = (MenuDTO) request.params[0];

            menuController.doValidateMenu(arg0);

            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch (Exception e){
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private void loadProtocol(){
        this.methodMap.put(SocketRequestType.LOGIN, this::handleLogin);
        this.methodMap.put(SocketRequestType.LOGOUT, this::handleLogout);
        this.methodMap.put(SocketRequestType.REGISTER, this::handleRegister);
        this.methodMap.put(SocketRequestType.SAVE_PEDIATRIST, this:: handleSavePediatrist);
        this.methodMap.put(SocketRequestType.GET_WORK_DAY, this:: handleGetWorkDay);
        this.methodMap.put(SocketRequestType.SAVE_TRIP_BUS_RELATION, this:: handleSaveTripBusRelation);
        this.methodMap.put(SocketRequestType.GET_ALL_PEDIATRISTS, this:: handleGetAllPediatrists);
        this.methodMap.put(SocketRequestType.REMOVE_STAFF, this:: handleRemoveStaff);
        this.methodMap.put(SocketRequestType.GENERATE_DAYS, this:: handleGenerateDays);
        this.methodMap.put(SocketRequestType.REMOVE_KID, this:: handleRemoveKid);
        this.methodMap.put(SocketRequestType.REMOVE_ROUTE, this:: handleRemoveRoute);
        this.methodMap.put(SocketRequestType.REMOVE_TRIP, this:: handleRemoveTrip);
        this.methodMap.put(SocketRequestType.REMOVE_PEDIATRIST, this:: handleRemovePediatrist);
        this.methodMap.put(SocketRequestType.SET_FIRST_EVER_STARTUP, this:: handleSetFirstEverStartup);
        this.methodMap.put(SocketRequestType.GET_ALL_SUPPLIERS, this:: handleGetAllSuppliers);
        this.methodMap.put(SocketRequestType.GET_ALL_FOODS, this:: handleGetAllFoods);
        this.methodMap.put(SocketRequestType.GET_ALL_DISHES, this:: handleGetAllDishes);
        this.methodMap.put(SocketRequestType.DELETE_DISH, this:: handleDeleteDish);
        this.methodMap.put(SocketRequestType.UPDATE_STAFF_MEMBER, this:: handleUpdateStaffMember);
        this.methodMap.put(SocketRequestType.SAVE_CHECKPOINT, this:: handleSaveCheckpoint);
        this.methodMap.put(SocketRequestType.UPDATE_PEDIATRIST, this:: handleUpdatePediatrist);
        this.methodMap.put(SocketRequestType.REMOVE_TRIP_PARTECIPATION, this:: handleRemoveTripPartecipation);
        this.methodMap.put(SocketRequestType.REMOVE_FOOD, this:: handleRemoveFood);
        this.methodMap.put(SocketRequestType.GET_ALL_ADULTS, this:: handleGetAllAdults);
        this.methodMap.put(SocketRequestType.SAVE_FOOD, this:: handleSaveFood);
        this.methodMap.put(SocketRequestType.GET_ALL_KIDS, this:: handleGetAllKids);
        this.methodMap.put(SocketRequestType.SAVE_BUS, this:: handleSaveBus);
        this.methodMap.put(SocketRequestType.UPDATE_DISH, this:: handleUpdateDish);
        this.methodMap.put(SocketRequestType.GET_MAX_SAVED_DATE, this:: handleGetMaxSavedDate);
        this.methodMap.put(SocketRequestType.UPDATE_TRIP, this:: handleUpdateTrip);
        this.methodMap.put(SocketRequestType.UPDATE_ADULT, this:: handleUpdateAdult);
        this.methodMap.put(SocketRequestType.GET_AVAILABLE_KIDS, this:: handleGetAvailableKids);
        this.methodMap.put(SocketRequestType.UPDATE_BUS, this:: handleUpdateBus);
        this.methodMap.put(SocketRequestType.GET_TRIP, this:: handleGetTrip);
        this.methodMap.put(SocketRequestType.SAVE_TRIP_PARTECIPATION, this:: handleSaveTripPartecipation);
        this.methodMap.put(SocketRequestType.GET_MIN_SAVED_DATE, this:: handleGetMinSavedDate);
        this.methodMap.put(SocketRequestType.GET_ALL_TRIPS, this:: handleGetAllTrips);
        this.methodMap.put(SocketRequestType.SAVE_CANTEEN, this:: handleSaveCanteen);
        this.methodMap.put(SocketRequestType.REMOVE_DISH_FROM_MENU, this:: handleRemoveDishFromMenu);
        this.methodMap.put(SocketRequestType.SAVE_STAFF, this:: handleSaveStaff);
        this.methodMap.put(SocketRequestType.GET_CURRENT_WORK_DAY, this:: handleGetCurrentWorkDay);
        this.methodMap.put(SocketRequestType.GET_ALL_ADULTS_EX, this:: handleGetAllAdultsEx);
        this.methodMap.put(SocketRequestType.UPDATE_SUPPLIER, this:: handleUpdateSupplier);
        this.methodMap.put(SocketRequestType.UPDATE_ROUTE_EVENT, this:: handleUpdateRouteEvent);
        this.methodMap.put(SocketRequestType.GET_ALL_CANTEENES, this:: handleGetAllCanteenes);
        this.methodMap.put(SocketRequestType.SAVE_SUPPLIER, this:: handleSaveSupplier);
        this.methodMap.put(SocketRequestType.SAVE_KID, this:: handleSaveKid);
        this.methodMap.put(SocketRequestType.GET_ALL_STAFF, this:: handleGetAllStaff);
        this.methodMap.put(SocketRequestType.TRIGGER_DAILY_SCHEDULING, this:: handleTriggerDailyScheduling);
        this.methodMap.put(SocketRequestType.REMOVE_ALLERGY, this:: handleRemoveAllergy);
        this.methodMap.put(SocketRequestType.IS_FIRST_EVER_STARTUP, this:: handleIsFirstEverStartup);
        this.methodMap.put(SocketRequestType.REMOVE_ADULT, this:: handleRemoveAdult);
        this.methodMap.put(SocketRequestType.REMOVE_BUS, this:: handleRemoveBus);
        this.methodMap.put(SocketRequestType.GET_AVAILABLE_FOODS, this:: handleGetAvailableFoods);
        this.methodMap.put(SocketRequestType.CREATE_DISH, this:: handleCreateDish);
        this.methodMap.put(SocketRequestType.GET_ALL_CANTEEN_NAMES, this:: handleGetAllCanteenNames);
        this.methodMap.put(SocketRequestType.REMOVE_TRIP_BUS_RELATION, this:: handleRemoveTripBusRelation);
        this.methodMap.put(SocketRequestType.GET_CANTEEN_BY_NAME, this:: handleGetCanteenByName);
        this.methodMap.put(SocketRequestType.CREATE_MENU, this:: handleCreateMenu);
        this.methodMap.put(SocketRequestType.ADD_DISH_TO_MENU, this:: handleAddDishToMenu);
        this.methodMap.put(SocketRequestType.REMOVE_CANTEEN, this:: handleRemoveCanteen);
        this.methodMap.put(SocketRequestType.ADD_ALLERGY, this:: handleAddAllergy);
        this.methodMap.put(SocketRequestType.GET_PERSON, this:: handleGetPerson);
        this.methodMap.put(SocketRequestType.UPDATE_KID, this:: handleUpdateKid);
        this.methodMap.put(SocketRequestType.SAVE_ADULT, this:: handleSaveAdult);
        this.methodMap.put(SocketRequestType.GET_EVENT_CHECKPOINTS, this:: handleGetEventCheckpoints);
        this.methodMap.put(SocketRequestType.UPDATE_MENU, this:: handleUpdateMenu);
        this.methodMap.put(SocketRequestType.SAVE_TRIP, this:: handleSaveTrip);
        this.methodMap.put(SocketRequestType.REMOVE_SUPPLIER, this:: handleRemoveSupplier);
        this.methodMap.put(SocketRequestType.UPDATE_FOOD, this:: handleUpdateFood);
        this.methodMap.put(SocketRequestType.GET_ALL_BUSES, this:: handleGetAllBuses);
        this.methodMap.put(SocketRequestType.GET_AVAILABLE_BUSES, this:: handleGetAvailableBuses);
        this.methodMap.put(SocketRequestType.ADD_OBSERVER, this::handleAddRemoteEventObserver);
        this.methodMap.put(SocketRequestType.REMOVE_OBSERVER, this::handleRemoveRemoteEventObserver);
        this.methodMap.put(SocketRequestType.ADD_CONTACT_TO_KID, this::handleAddContactToKid);
        this.methodMap.put(SocketRequestType.REMOVE_CONTACT_FROM_KID, this::handleRemoveContactFromKid);
        this.methodMap.put(SocketRequestType.VALIDATE_MENU, this::handleValidateMenu);
    }

    public interface SocketRequestHandler {
        SocketResponse handle(SocketRequest request);
    }
}
