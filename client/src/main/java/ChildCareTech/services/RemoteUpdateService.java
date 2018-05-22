package ChildCareTech.services;

import ChildCareTech.common.RemoteUpdatable;
import ChildCareTech.controller.*;
import javafx.application.Platform;

import java.util.HashMap;

public class RemoteUpdateService {
    private HashMap<RemoteUpdatable, Runnable> updateProcedureMap;

    public RemoteUpdateService() {
        updateProcedureMap = new HashMap<>();
        updateProcedureMap.put(RemoteUpdatable.KID, this::updateKid);
        updateProcedureMap.put(RemoteUpdatable.ADULT, this::updateAdult);
        updateProcedureMap.put(RemoteUpdatable.CANTEEN, this::updateCanteen);
        updateProcedureMap.put(RemoteUpdatable.TRIP, this::updateTrip);
        updateProcedureMap.put(RemoteUpdatable.EVENT, this::updateEvent);
        updateProcedureMap.put(RemoteUpdatable.TODAY, this::updateToday);
        updateProcedureMap.put(RemoteUpdatable.DISH, this::updateDish);
        updateProcedureMap.put(RemoteUpdatable.FOOD, this::updateFood);
        updateProcedureMap.put(RemoteUpdatable.BUS, this::updateBus);
        updateProcedureMap.put(RemoteUpdatable.CHECKPOINT, this::updateCheckpoint);
        updateProcedureMap.put(RemoteUpdatable.MENU, this::updateMenu);
        updateProcedureMap.put(RemoteUpdatable.ALLERGY, this::updateAllergy);
        updateProcedureMap.put(RemoteUpdatable.TRIPPARTECIPATION, this::updateTripPartecipation);
    }

    public void handle(RemoteUpdatable updatable){
        updateProcedureMap.get(updatable).run();
    }

    private void updateKid(){
        Platform.runLater(() -> {
            for(NewKidAnagraphicsController controller : ActiveControllersList.getKidAnagraphicControllersList())
                controller.refreshTable();
        });
    }

    private void updateAdult(){
        Platform.runLater(() -> {
            for(NewAdultAnagraphicsController controller : ActiveControllersList.getAdultAnagraphicControllersList())
                controller.refreshTable();
        });
    }

    private void updateCanteen(){
        Platform.runLater(() -> {
            for(NewCanteenManagerController controller : ActiveControllersList.getCanteenManagerControllersList())
                controller.refreshTable();
            for(NewCanteenesListController controller : ActiveControllersList.getCanteenControllersList())
                controller.refreshTable();
        });
    }

    private void updateTrip(){
        Platform.runLater(() -> {
            for(NewTripListController controller : ActiveControllersList.getTripsListControllersList())
                controller.refreshTable();
        });
    }

    private void updateEvent(){
        updateToday();
    }

    private void updateToday(){
        Platform.runLater(() -> {
            for(NewWorkDayController controller : ActiveControllersList.getWorkDayManagerControllersList())
                controller.remoteUpdate();
        });
    }

    private void updateDish(){
        Platform.runLater(() -> {
            for(KitchenController controller : ActiveControllersList.getDishControllersList())
                controller.refreshTable();
        });
    }

    private void updateFood(){
        Platform.runLater(() -> {
            for(FoodsListController controller : ActiveControllersList.getFoodControllersList())
                controller.refreshTable();
        });
    }

    private void updateBus(){
        Platform.runLater(() -> {
            for(NewBusController controller : ActiveControllersList.getBusControllersList())
                controller.refreshTable();
        });
    }

    private void updateCheckpoint(){
        Platform.runLater(() -> {
            for(NewWorkDayController controller : ActiveControllersList.getWorkDayManagerControllersList())
                controller.refreshTable();
        });
    }

    private void updateMenu(){
        System.out.println("Menu update");
    }

    private void updateAllergy(){
        System.out.println("Allergy update");
    }

    private void updateTripPartecipation(){
        System.out.println("TripPartecipation update");
    }
}
