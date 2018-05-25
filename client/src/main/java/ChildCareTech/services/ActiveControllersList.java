package ChildCareTech.services;

import ChildCareTech.controller.*;

import java.util.ArrayList;
import java.util.List;

public class ActiveControllersList {

    private static List<NewKidAnagraphicsController> kidAnagraphicControllersList = new ArrayList<>();
    private static List<NewAdultAnagraphicsController> adultAnagraphicControllersList = new ArrayList<>();
    private static List<NewTripListController> tripsListControllersList = new ArrayList<>();
    private static List<NewCanteenManagerController> canteenManagerControllersList = new ArrayList<>();
    private static List<NewWorkDayController> workDayManagerControllersList = new ArrayList<>();
    private static List<NewBusController> busControllersList = new ArrayList<>();
    private static List<FoodsListController> foodControllersList = new ArrayList<>();
    private static List<NewCanteenesListController> canteenControllersList = new ArrayList<>();
    private static List<KitchenController> dishControllersList = new ArrayList<>();
    private static List<EventReportController> eventReportControllersList = new ArrayList<>();
    private static List<TripPresenceRegistrationController> presenceRegistrationControllersList = new ArrayList<>();
    private static List<CodeInputWindowController> codeInputWindowControllerList = new ArrayList<>();

    private ActiveControllersList() { }

    public static void addKidAnagraphicsController(NewKidAnagraphicsController newKidAnagraphicsController) {
        kidAnagraphicControllersList.add(newKidAnagraphicsController);
    }
    public static void removeKidAnagraphicsController(NewKidAnagraphicsController newKidAnagraphicsController) {
        kidAnagraphicControllersList.remove(newKidAnagraphicsController);
    }
    public static List<NewKidAnagraphicsController> getKidAnagraphicControllersList() { return new ArrayList<>(kidAnagraphicControllersList); }

    public static void addAdultAnagraphicsController(NewAdultAnagraphicsController newAdultAnagraphicsController) {
        adultAnagraphicControllersList.add(newAdultAnagraphicsController);
    }
    public static void removeAdultAnagraphicsController(NewAdultAnagraphicsController newAdultAnagraphicsController) {
        adultAnagraphicControllersList.remove(newAdultAnagraphicsController);
    }
    public static List<NewAdultAnagraphicsController> getAdultAnagraphicControllersList() { return new ArrayList<>(adultAnagraphicControllersList); }

    public static void addTripListController(NewTripListController newTripListController) {
        tripsListControllersList.add(newTripListController);
    }
    public static void removeTripListController(NewTripListController newTripListController) {
        tripsListControllersList.remove(newTripListController);
    }
    public static List<NewTripListController> getTripsListControllersList() { return new ArrayList<>(tripsListControllersList); }

    public static void addCanteenManagerController(NewCanteenManagerController newCanteenManagerController) {
        canteenManagerControllersList.add(newCanteenManagerController);
    }
    public static void removeCanteenManagerController(NewCanteenManagerController newCanteenManagerController) {
        canteenManagerControllersList.remove(newCanteenManagerController);
    }
    public static List<NewCanteenManagerController> getCanteenManagerControllersList() { return new ArrayList<>(canteenManagerControllersList); }

    public static void addWorkDayManagerController(NewWorkDayController newWorkDayController) {
        workDayManagerControllersList.add(newWorkDayController);
    }
    public static void removeWorkDayManagerController(NewWorkDayController newWorkDayController) {
        workDayManagerControllersList.remove(newWorkDayController);
    }
    public static List<NewWorkDayController> getWorkDayManagerControllersList() { return new ArrayList<>(workDayManagerControllersList); }

    public static void addBusController(NewBusController newBusController) {
        busControllersList.add(newBusController);
    }
    public static void removeBusController(NewBusController newBusController) {
        busControllersList.remove(newBusController);
    }
    public static List<NewBusController> getBusControllersList() { return new ArrayList<>(busControllersList); }

    public static void addFoodController(FoodsListController foodsListController) {
        foodControllersList.add(foodsListController);
    }
    public static void removeFoodController(FoodsListController foodsListController) {
        foodControllersList.remove(foodsListController);
    }
    public static List<FoodsListController> getFoodControllersList() { return new ArrayList<>(foodControllersList); }

    public static void addCanteenControllerList(NewCanteenesListController newCanteenesListController) {
        canteenControllersList.add(newCanteenesListController);
    }
    public static void removeCanteenControllerList(NewCanteenesListController newCanteenesListController) {
        canteenControllersList.remove(newCanteenesListController);
    }
    public static List<NewCanteenesListController> getCanteenControllersList() { return new ArrayList<>(canteenControllersList); }

    public static void addDishController(KitchenController kitchenController) {
        dishControllersList.add(kitchenController);
    }
    public static void removeDishController(KitchenController kitchenController) {
        dishControllersList.remove(kitchenController);
    }
    public static List<KitchenController> getDishControllersList() { return new ArrayList<>(dishControllersList); }

    public static void addEventReportController(EventReportController eventReportController) {
        eventReportControllersList.add(eventReportController);
    }
    public static void removeEventReportController(EventReportController eventReportController) {
        eventReportControllersList.remove(eventReportController);
    }
    public static List<EventReportController> getEventReportControllersList() { return new ArrayList<>(eventReportControllersList); }

    public static void addPresenceRegistrationControllersList(TripPresenceRegistrationController presenceRegistrationController) {
        presenceRegistrationControllersList.add(presenceRegistrationController);
    }
    public  static void removePresenceRegistrationControllersList(TripPresenceRegistrationController presenceRegistrationController) {
        presenceRegistrationControllersList.remove(presenceRegistrationController);
    }
    public static List<TripPresenceRegistrationController> getPresenceRegistrationControllersList() {
        return presenceRegistrationControllersList;
    }
    public static void addCodeInputWindowController(CodeInputWindowController codeInputWindowController) {
        codeInputWindowControllerList.add(codeInputWindowController);
    }
    public static void removeCodeInputWindowController(CodeInputWindowController codeInputWindowController) {
        codeInputWindowControllerList.remove(codeInputWindowController);
    }
    public static List<CodeInputWindowController> getCodeInputWindowControllerList() {
        return new ArrayList<>(codeInputWindowControllerList);
    }
}
