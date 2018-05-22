package ChildCareTech.services;

import ChildCareTech.controller.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContainedWindowService {

    private AnchorPane anchorPane;
    private FXMLLoader kidAnagraphicsLoader;
    private FXMLLoader adultAnagraphicsLoader;
    private FXMLLoader tripListLoader;
    private FXMLLoader canteenManagerLoader;
    private FXMLLoader workDayManagerLoader;

    private static List<TableWindowControllerInterface> actives = new ArrayList<>();

    private Node kidAnagraphicsNode;
    private Node adultAnagraphicsNode;
    private Node tripListNode;
    private Node canteenManagerNode;
    private Node workDayManagerNode;

    private TableWindowControllerInterface active;

    public ContainedWindowService(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
        loadFXMLLoaders();
        loadNodes();
    }

    public void loadKidAnagraphics() {
        if(active != null)
            actives.remove(active);
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(kidAnagraphicsNode);
        anchorChild(anchorPane, kidAnagraphicsNode);
        active = ((TableWindowControllerInterface)kidAnagraphicsLoader.getController());
        active.refreshTable();
        actives.add(active);
    }
    public void loadAdultAnagraphics() {
        if(active != null)
            actives.remove(active);
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(adultAnagraphicsNode);
        anchorChild(anchorPane, adultAnagraphicsNode);
        active = ((TableWindowControllerInterface)adultAnagraphicsLoader.getController());
        active.refreshTable();
        actives.add(active);
    }
    public void loadTripList() {
        if(active != null)
            actives.remove(active);
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(tripListNode);
        anchorChild(anchorPane, tripListNode);
        active = ((TableWindowControllerInterface)tripListLoader.getController());
        active.refreshTable();
        actives.add(active);
    }
    public void loadCanteenManager() {
        if(active != null)
            actives.remove(active);
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(canteenManagerNode);
        anchorChild(anchorPane, canteenManagerNode);
        active = ((TableWindowControllerInterface)canteenManagerLoader.getController());
        active.refreshTable();
        actives.add(active);
    }
    public void loadWorkDayManager() {
        if(active != null)
            actives.remove(active);
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(workDayManagerNode);
        anchorChild(anchorPane, workDayManagerNode);
        active = ((TableWindowControllerInterface)workDayManagerLoader.getController());
        active.refreshTable();
        actives.add(active);
    }

    public void anchorChild(AnchorPane anchorPane, Node node) {
        anchorPane.setBottomAnchor(node, 0.0);
        anchorPane.setLeftAnchor(node, 0.0);
        anchorPane.setTopAnchor(node, 0.0);
        anchorPane.setRightAnchor(node, 0.0);
    }

    public NewKidAnagraphicsController getKidAnagraphicsController() {
        return kidAnagraphicsLoader.getController();
    }
    public NewAdultAnagraphicsController getAdultAnagraphicsController() { return adultAnagraphicsLoader.getController(); }
    public NewTripListController getTripListController() { return tripListLoader.getController(); }
    public NewCanteenManagerController getCanteenManagerController() { return canteenManagerLoader.getController(); }
    public NewWorkDayController getWorkDayController() { return workDayManagerLoader.getController(); }

    private void loadFXMLLoaders() {
        this.kidAnagraphicsLoader = new FXMLLoader(ContainedWindowService.class.getResource(ResourcesPaths.getKidAnagraphicsFXMLPath()));
        this.adultAnagraphicsLoader = new FXMLLoader(ContainedWindowService.class.getResource(ResourcesPaths.getAdultAnagraphicsFXMLPath()));
        this.tripListLoader = new FXMLLoader(ContainedWindowService.class.getResource(ResourcesPaths.getTripListFXMLPath()));
        this.canteenManagerLoader = new FXMLLoader(ContainedWindowService.class.getResource(ResourcesPaths.getCanteenManagerFXMLPath()));
        this.workDayManagerLoader = new FXMLLoader(ContainedWindowService.class.getResource(ResourcesPaths.getWorkDayManagerFXMLPath()));
    }
    private void loadNodes() {
        try {
            kidAnagraphicsNode = kidAnagraphicsLoader.load();
            adultAnagraphicsNode = adultAnagraphicsLoader.load();
            tripListNode = tripListLoader.load();
            canteenManagerNode = canteenManagerLoader.load();
            workDayManagerNode = workDayManagerLoader.load();

            ActiveControllersList.addKidAnagraphicsController(kidAnagraphicsLoader.getController());
            ActiveControllersList.addAdultAnagraphicsController(adultAnagraphicsLoader.getController());
            ActiveControllersList.addTripListController(tripListLoader.getController());
            ActiveControllersList.addCanteenManagerController(canteenManagerLoader.getController());
            ActiveControllersList.addWorkDayManagerController(workDayManagerLoader.getController());

        } catch(IOException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void clearInstance() {

        ActiveControllersList.removeKidAnagraphicsController(this.kidAnagraphicsLoader.getController());
        ActiveControllersList.removeAdultAnagraphicsController(this.adultAnagraphicsLoader.getController());
        ActiveControllersList.removeTripListController(this.tripListLoader.getController());
        ActiveControllersList.removeCanteenManagerController(this.canteenManagerLoader.getController());
        ActiveControllersList.removeWorkDayManagerController(this.workDayManagerLoader.getController());

        if(active != null)
            actives.remove(active);
    }

    public static void refreshAllTables() {
        List<TableWindowControllerInterface> tables = new ArrayList<>();
        tables.addAll(ActiveControllersList.getKidAnagraphicControllersList());
        tables.addAll(ActiveControllersList.getAdultAnagraphicControllersList());
        tables.addAll(ActiveControllersList.getTripsListControllersList());
        tables.addAll(ActiveControllersList.getCanteenManagerControllersList());
        tables.addAll(ActiveControllersList.getWorkDayManagerControllersList());
        refreshTables(tables);

    }
    private static void refreshTables(List<TableWindowControllerInterface> tables) {
        tables.removeAll(actives);  //chiedo all'utente se vuole aggiornare
        for(TableWindowControllerInterface tableWindowControllerInterface : tables) {
            tableWindowControllerInterface.refreshTable();
        }
        for(TableWindowControllerInterface tableWindowControllerInterface : actives) {
            tableWindowControllerInterface.notifyUpdate();
        }
    }
    public static void refreshKidTables() {
        List<TableWindowControllerInterface> tables = new ArrayList<>();
        tables.addAll(ActiveControllersList.getKidAnagraphicControllersList());
        refreshTables(tables);
    }
    public static void refreshAdultTables() {
        List<TableWindowControllerInterface> tables = new ArrayList<>();
        tables.addAll(ActiveControllersList.getAdultAnagraphicControllersList());
        refreshTables(tables);
    }
    public static void refreshTripsTables() {
        List<TableWindowControllerInterface> tables = new ArrayList<>();
        tables.addAll(ActiveControllersList.getTripsListControllersList());
        refreshTables(tables);
    }
    public static void refreshCanteenTables() {
        List<TableWindowControllerInterface> tables = new ArrayList<>();
        tables.addAll(ActiveControllersList.getCanteenManagerControllersList());
        refreshTables(tables);
    }
    public static void refreshWorkDayTables() {
        List<TableWindowControllerInterface> tables = new ArrayList<>();
        tables.addAll(ActiveControllersList.getWorkDayManagerControllersList());
        refreshTables(tables);
    }

}
