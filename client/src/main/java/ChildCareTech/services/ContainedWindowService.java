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
    private static List<NewKidAnagraphicsController> kidAnagraphicsList = new ArrayList<>();
    private static List<NewAdultAnagraphicsController> adultAnagraphicsList = new ArrayList<>();
    private static List<NewTripListController> tripsListList = new ArrayList<>();
    private static List<NewCanteenManagerController> canteenManagersList = new ArrayList<>();
    private static List<NewWorkDayController> workDayManagersList = new ArrayList<>();
    private Node kidAnagraphicsNode;
    private Node adultAnagraphicsNode;
    private Node tripListNode;
    private Node canteenManagerNode;
    private Node workDayManagerNode;

    public ContainedWindowService(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
        loadFXMLLoaders();
        loadNodes();
    }

    public void loadKidAnagraphics() {
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(kidAnagraphicsNode);
        anchorChild(anchorPane, kidAnagraphicsNode);
        ((TableWindowControllerInterface)kidAnagraphicsLoader.getController()).refreshTable();
    }
    public void loadAdultAnagraphics() {
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(adultAnagraphicsNode);
        anchorChild(anchorPane, adultAnagraphicsNode);
        ((TableWindowControllerInterface)adultAnagraphicsLoader.getController()).refreshTable();
    }
    public void loadTripList() {
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(tripListNode);
        anchorChild(anchorPane, tripListNode);
        ((TableWindowControllerInterface)tripListLoader.getController()).refreshTable();
    }
    public void loadCanteenManager() {
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(canteenManagerNode);
        anchorChild(anchorPane, canteenManagerNode);
        ((TableWindowControllerInterface)canteenManagerLoader.getController()).refreshTable();
    }
    public void loadWorkDayManager() {
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(workDayManagerNode);
        anchorChild(anchorPane, workDayManagerNode);
        ((TableWindowControllerInterface)workDayManagerLoader.getController()).refreshTable();
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
    public static List<NewKidAnagraphicsController> getKidAnagraphicsList() { return new ArrayList<>(kidAnagraphicsList); }
    public static List<NewAdultAnagraphicsController> getAdultAnagraphicsList() { return new ArrayList<>(adultAnagraphicsList); }
    public static List<NewTripListController> getTripsListList() { return new ArrayList<>(tripsListList); }
    public static List<NewCanteenManagerController> getCanteenManagersList() { return new ArrayList<>(canteenManagersList); }
    public static List<NewWorkDayController> getWorkDayManagersList() { return new ArrayList<>(workDayManagersList); }

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

            kidAnagraphicsList.add(kidAnagraphicsLoader.getController());
            adultAnagraphicsList.add(adultAnagraphicsLoader.getController());
            tripsListList.add(tripListLoader.getController());
            canteenManagersList.add(canteenManagerLoader.getController());
            workDayManagersList.add(workDayManagerLoader.getController());

        } catch(IOException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void clearInstance() {
        kidAnagraphicsList.remove(this.kidAnagraphicsLoader.getController());
        adultAnagraphicsList.remove(this.adultAnagraphicsLoader.getController());
        tripsListList.remove(this.tripListLoader.getController());
        canteenManagersList.remove(this.canteenManagerLoader.getController());
        workDayManagersList.remove(this.workDayManagerLoader.getController());
    }

    public static void refreshTables() {
        List<TableWindowControllerInterface> tables = new ArrayList<>();
        tables.addAll(kidAnagraphicsList);
        tables.addAll(adultAnagraphicsList);
        tables.addAll(tripsListList);
        tables.addAll(canteenManagersList);
        tables.addAll(workDayManagersList);
        for(TableWindowControllerInterface tableWindowControllerInterface : tables) {
            tableWindowControllerInterface.refreshTable();
        }
    }
}
