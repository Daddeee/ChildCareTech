package ChildCareTech.common;

import ChildCareTech.common.DTO.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * The type of a socket request.
 * One can assume that for each method in the {@link UserSessionFacade} interface there is a corresponding entry in this enum.
 * The vice versa is true except for the {@link #LOGIN} and {@link #REGISTER} entries that correspond to methods in the {@link UserSessionFactory} interface.
 */
public enum SocketRequestType {
    /**
     * {@link UserSessionFactory#login(String, String)}
     */
    LOGIN,

    /**
     * {@link UserSessionFacade#logout()}
     */
    LOGOUT,

    /**
     * {@link UserSessionFactory#register(String, String)}
     */
    REGISTER,
    /**
     * {@link UserSessionFacade#updateStaffMember(StaffDTO)}
     */
    UPDATE_STAFF_MEMBER,
    /**
     * {@link UserSessionFacade#saveAdult(AdultDTO)}
     */
    SAVE_ADULT,
    /**
     * {@link UserSessionFacade#saveSupplier(SupplierDTO)}
     */
    SAVE_SUPPLIER,
    /**
     * {@link UserSessionFacade#saveKid(KidDTO)}
     */
    SAVE_KID,
    /**
     * {@link UserSessionFacade#savePediatrist(PediatristDTO)}
     */
    SAVE_PEDIATRIST,
    /**
     * {@link UserSessionFacade#saveStaff(StaffDTO)}
     */
    SAVE_STAFF,
    /**
     * {@link UserSessionFacade#removeKid(KidDTO)}
     */
    REMOVE_KID,
    /**
     * {@link UserSessionFacade#removeAdult(AdultDTO)}
     */
    REMOVE_ADULT,
    /**
     * {@link UserSessionFacade#removePediatrist(PediatristDTO)}
     */
    REMOVE_PEDIATRIST,
    /**
     * {@link UserSessionFacade#removeStaff(StaffDTO)}
     */
    REMOVE_STAFF,
    /**
     * {@link UserSessionFacade#removeSupplier(SupplierDTO)}
     */
    REMOVE_SUPPLIER,
    /**
     * {@link UserSessionFacade#addContactToKid(KidDTO, AdultDTO)}
     */
    ADD_CONTACT_TO_KID,
    /**
     * {@link UserSessionFacade#removeContactFromKid(KidDTO, AdultDTO)}
     */
    REMOVE_CONTACT_FROM_KID,
    /**
     * {@link UserSessionFacade#saveTrip(TripDTO)}
     */
    SAVE_TRIP,
    /**
     * {@link UserSessionFacade#updateTrip(TripDTO)}
     */
    UPDATE_TRIP,
    /**
     * {@link UserSessionFacade#getTrip(int)}
     */
    GET_TRIP,
    /**
     * {@link UserSessionFacade#updateKid(KidDTO)}
     */
    UPDATE_KID,
    /**
     * {@link UserSessionFacade#updateAdult(AdultDTO)}
     */
    UPDATE_ADULT,
    /**
     * {@link UserSessionFacade#updatePediatrist(PediatristDTO)}
     */
    UPDATE_PEDIATRIST,
    /**
     * {@link UserSessionFacade#updateSupplier(SupplierDTO)}
     */
    UPDATE_SUPPLIER,
    /**
     * {@link UserSessionFacade#removeTrip(TripDTO)}
     */
    REMOVE_TRIP,
    /**
     * {@link UserSessionFacade#removeRoute(RouteDTO)}
     */
    REMOVE_ROUTE,
    /**
     * {@link UserSessionFacade#updateRouteEvent(RouteDTO)}
     */
    UPDATE_ROUTE_EVENT,
    /**
     * {@link UserSessionFacade#getAllKids()}
     */
    GET_ALL_KIDS,
    /**
     * {@link UserSessionFacade#getAvailableKids(TripDTO)}
     */
    GET_AVAILABLE_KIDS,
    /**
     * {@link UserSessionFacade#getAllAdults()}
     */
    GET_ALL_ADULTS,
    /**
     * {@link UserSessionFacade#getAllAdultsExclusive()}
     */
    GET_ALL_ADULTS_EX,
    /**
     * {@link UserSessionFacade#getAllPediatrists()}
     */
    GET_ALL_PEDIATRISTS,
    /**
     * {@link UserSessionFacade#getAllStaff()}
     */
    GET_ALL_STAFF,
    /**
     * {@link UserSessionFacade#getAllSuppliers()}
     */
    GET_ALL_SUPPLIERS,
    /**
     * {@link UserSessionFacade#getAllTrips()}
     */
    GET_ALL_TRIPS,
    /**
     * {@link UserSessionFacade#saveTripPartecipation(TripPartecipationDTO)}
     */
    SAVE_TRIP_PARTECIPATION,
    /**
     * {@link UserSessionFacade#removeTripPartecipation(TripPartecipationDTO)}
     */
    REMOVE_TRIP_PARTECIPATION,
    /**
     * {@link UserSessionFacade#saveTripBusRelation(TripDTO, BusDTO)}
     */
    SAVE_TRIP_BUS_RELATION,
    /**
     * {@link UserSessionFacade#removeTripBusRelation(TripDTO, BusDTO)}
     */
    REMOVE_TRIP_BUS_RELATION,
    /**
     * {@link UserSessionFacade#saveBus(BusDTO)}
     */
    SAVE_BUS,
    /**
     * {@link UserSessionFacade#removeBus(BusDTO)}
     */
    REMOVE_BUS,
    /**
     * {@link UserSessionFacade#updateBus(BusDTO)}
     */
    UPDATE_BUS,
    /**
     * {@link UserSessionFacade#getAllBuses()}
     */
    GET_ALL_BUSES,
    /**
     * {@link UserSessionFacade#getAvailableBuses(TripDTO)}
     */
    GET_AVAILABLE_BUSES,
    /**
     * {@link UserSessionFacade#saveFood(FoodDTO)}
     */
    SAVE_FOOD,
    /**
     * {@link UserSessionFacade#updateFood(FoodDTO)}
     */
    UPDATE_FOOD,
    /**
     * {@link UserSessionFacade#removeFood(FoodDTO)}
     */
    REMOVE_FOOD,
    /**
     * {@link UserSessionFacade#getAllFoods()}
     */
    GET_ALL_FOODS,
    /**
     * {@link UserSessionFacade#getAvailableFoods(PersonDTO)}
     */
    GET_AVAILABLE_FOODS,
    /**
     * {@link UserSessionFacade#addAllergy(PersonDTO, FoodDTO)}
     */
    ADD_ALLERGY,
    /**
     * {@link UserSessionFacade#removeAllergy(PersonDTO, FoodDTO)}
     */
    REMOVE_ALLERGY,
    /**
     * {@link UserSessionFacade#getPerson(String)}
     */
    GET_PERSON,
    /**
     * {@link UserSessionFacade#isFirstEverStartup()}
     */
    IS_FIRST_EVER_STARTUP,
    /**
     * {@link UserSessionFacade#setFirstEverStartup(boolean)}
     */
    SET_FIRST_EVER_STARTUP,
    /**
     * {@link UserSessionFacade#generateDays(DayGenerationSettingsDTO)}
     */
    GENERATE_DAYS,
    /**
     * {@link UserSessionFacade#triggerDailyScheduling()}
     */
    TRIGGER_DAILY_SCHEDULING,
    /**
     * {@link UserSessionFacade#saveCanteen(CanteenDTO, List, List)}
     */
    SAVE_CANTEEN,
    /**
     * {@link UserSessionFacade#getAllCanteenNames()}
     */
    GET_ALL_CANTEEN_NAMES,
    /**
     * {@link UserSessionFacade#getAllCanteenes()}
     */
    GET_ALL_CANTEENES,
    /**
     * {@link UserSessionFacade#getCanteenByName(String)}
     */
    GET_CANTEEN_BY_NAME,
    /**
     * {@link UserSessionFacade#removeCanteen(CanteenDTO)}
     */
    REMOVE_CANTEEN,
    /**
     * {@link UserSessionFacade#getAllDishes()}
     */
    GET_ALL_DISHES,
    /**
     * {@link UserSessionFacade#createDish(DishDTO)}
     */
    CREATE_DISH,
    /**
     * {@link UserSessionFacade#updateDish(DishDTO)}
     */
    UPDATE_DISH,
    /**
     * {@link UserSessionFacade#deleteDish(DishDTO)}
     */
    DELETE_DISH,
    /**
     * {@link UserSessionFacade#createMenu(MealDTO)}
     */
    CREATE_MENU,
    /**
     * {@link UserSessionFacade#updateMenu(MealDTO)}
     */
    UPDATE_MENU,
    /**
     * {@link UserSessionFacade#validateMenu(MenuDTO)}
     */
    VALIDATE_MENU,
    /**
     * {@link UserSessionFacade#removeDishFromMenu(MenuDTO, DishDTO)}
     */
    REMOVE_DISH_FROM_MENU,
    /**
     * {@link UserSessionFacade#addDishToMenu(MenuDTO, DishDTO)}
     */
    ADD_DISH_TO_MENU,
    /**
     * {@link UserSessionFacade#getCurrentWorkDay()}
     */
    GET_CURRENT_WORK_DAY,
    /**
     * {@link UserSessionFacade#getWorkDay(LocalDate)}
     */
    GET_WORK_DAY,
    /**
     * {@link UserSessionFacade#getMinSavedDate()}
     */
    GET_MIN_SAVED_DATE,
    /**
     * {@link UserSessionFacade#getMaxSavedDate()}
     */
    GET_MAX_SAVED_DATE,
    /**
     * {@link UserSessionFacade#getEventCheckpoints(EventDTO)}
     */
    GET_EVENT_CHECKPOINTS,
    /**
     * {@link UserSessionFacade#saveCheckpoint(String, EventDTO, LocalTime)}
     */
    SAVE_CHECKPOINT,
    /**
     * {@link UserSessionFacade#saveTripCheckpoint(String, EventDTO, LocalTime, String, TripDTO)}
     */
    SAVE_TRIP_CHECKPOINT,
    /**
     * {@link UserSessionFacade#addRemoteEventObserver(RemoteEventObserver)}
     */
    ADD_OBSERVER,
    /**
     * {@link UserSessionFacade#removeRemoteEventObserver(RemoteEventObserver)}
     */
    REMOVE_OBSERVER
}
