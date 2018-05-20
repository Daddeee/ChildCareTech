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
            for(NewKidAnagraphicsController controller : ContainedWindowService.getKidAnagraphicsList())
                controller.refreshTable();
        });
    }

    private void updateAdult(){
        Platform.runLater(() -> {
            for(NewAdultAnagraphicsController controller : ContainedWindowService.getAdultAnagraphicsList())
                controller.refreshTable();
        });
    }

    private void updateCanteen(){
        Platform.runLater(() -> {
            for(NewCanteenManagerController controller : ContainedWindowService.getCanteenManagersList())
                controller.refreshTable();
        });
    }

    private void updateTrip(){
        Platform.runLater(() -> {
            for(NewTripListController controller : ContainedWindowService.getTripsListList())
                controller.refreshTable();
        });
    }

    private void updateEvent(){
        updateToday();
    }

    private void updateToday(){
        Platform.runLater(() -> {
            for(NewWorkDayController controller : ContainedWindowService.getWorkDayManagersList())
                controller.remoteUpdate();
        });
    }

    private void updateDish(){
        System.out.println("Dish update");
    }

    private void updateFood(){
        System.out.println("Food update");
    }

    private void updateBus(){
        System.out.println("Bus update");
    }

    private void updateCheckpoint(){
        System.out.println("Checkpoint update");
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
