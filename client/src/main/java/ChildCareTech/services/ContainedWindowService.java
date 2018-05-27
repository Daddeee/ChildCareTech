package ChildCareTech.services;

import ChildCareTech.controller.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides a window management service for the panes that will be loaded in other container windows.
 */

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

    /**
     * This constructor sets the pane passed as parameter as the container and call methods for loading.
     * @param anchorPane the pane that will be used as container.
     */
    public ContainedWindowService(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
        loadFXMLLoaders();
        loadNodes();
    }

    /**
     * Loads the kids anagraphic node into the container node and sets its controller as the active window.
     * Refreshes the kids anagraphic table.
     */
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
    /**
     * Loads the adults anagraphic node into the container node and sets its controller as the active window.
     * Refreshes the adults anagraphic table.
     */
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
    /**
     * Loads the trips list node into the container node and sets its controller as the active window.
     * Refreshes the trips list table.
     */
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
    /**
     * Loads the canteen manager node into the container node and sets its controller as the active window.
     * Refreshes the canteen manager table.
     */
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
    /**
     * Loads the workday manager node into the container node and sets its controller as the active window.
     * Refreshes the workday manager table.
     */
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
    private void anchorChild(AnchorPane anchorPane, Node node) {
        anchorPane.setBottomAnchor(node, 0.0);
        anchorPane.setLeftAnchor(node, 0.0);
        anchorPane.setTopAnchor(node, 0.0);
        anchorPane.setRightAnchor(node, 0.0);
    }

    /**
     * @return the controller for the kids anagraphic window
     */
    public NewKidAnagraphicsController getKidAnagraphicsController() {
        return kidAnagraphicsLoader.getController();
    }
    /**
     * @return the controller for the adults anagraphic window
     */
    public NewAdultAnagraphicsController getAdultAnagraphicsController() { return adultAnagraphicsLoader.getController(); }
    /**
     * @return the controller for the trips list window
     */
    public NewTripListController getTripListController() { return tripListLoader.getController(); }
    /**
     * @return the controller for the canteen manager window
     */
    public NewCanteenManagerController getCanteenManagerController() { return canteenManagerLoader.getController(); }
    /**
     * @return the controller for the workday manager window
     */
    public NewWorkDayController getWorkDayController() { return workDayManagerLoader.getController(); }

    /**
     * Creates the loaders from relative fxml's and css' file paths for:
     * <p>
     *     kid's anagraphics
     *     adult's anagraphics
     *     trips list
     *     canteens manager
     *     workday manager
     * </p>
     *
     * Should be called once from the constructor.
     */
    private void loadFXMLLoaders() {
        this.kidAnagraphicsLoader = new FXMLLoader(ContainedWindowService.class.getResource(ResourcesPaths.getKidAnagraphicsFXMLPath()));
        this.adultAnagraphicsLoader = new FXMLLoader(ContainedWindowService.class.getResource(ResourcesPaths.getAdultAnagraphicsFXMLPath()));
        this.tripListLoader = new FXMLLoader(ContainedWindowService.class.getResource(ResourcesPaths.getTripListFXMLPath()));
        this.canteenManagerLoader = new FXMLLoader(ContainedWindowService.class.getResource(ResourcesPaths.getCanteenManagerFXMLPath()));
        this.workDayManagerLoader = new FXMLLoader(ContainedWindowService.class.getResource(ResourcesPaths.getWorkDayManagerFXMLPath()));
    }

    /**
     * Stores the result of the loaders' load into relative Node variables.
     * Should be called once from the constructor.
     */
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

    /**
     * Removes the controllers' instances from controller lists used for remote refresh purposes.
     * Should be called before deleting the instance of ContainedWindowService.
     */
    public void clearInstance() {

        ActiveControllersList.removeKidAnagraphicsController(this.kidAnagraphicsLoader.getController());
        ActiveControllersList.removeAdultAnagraphicsController(this.adultAnagraphicsLoader.getController());
        ActiveControllersList.removeTripListController(this.tripListLoader.getController());
        ActiveControllersList.removeCanteenManagerController(this.canteenManagerLoader.getController());
        ActiveControllersList.removeWorkDayManagerController(this.workDayManagerLoader.getController());

        if(active != null)
            actives.remove(active);
    }

    /**
     * Calls the refreshTable method for all controllers saved.
     */
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

    /**
     * Calls the refreshTable method for all kids anagraphics windows except for the ones active in the foreground.
     */
    public static void refreshKidTables() {
        List<TableWindowControllerInterface> tables = new ArrayList<>();
        tables.addAll(ActiveControllersList.getKidAnagraphicControllersList());
        refreshTables(tables);
    }
    /**
     * Calls the refreshTable method for all adults anagraphics windows except for the ones active in the foreground.
     */
    public static void refreshAdultTables() {
        List<TableWindowControllerInterface> tables = new ArrayList<>();
        tables.addAll(ActiveControllersList.getAdultAnagraphicControllersList());
        refreshTables(tables);
    }
    /**
     * Calls the refreshTable method for all trips lists windows except for the ones active in the foreground.
     */
    public static void refreshTripsTables() {
        List<TableWindowControllerInterface> tables = new ArrayList<>();
        tables.addAll(ActiveControllersList.getTripsListControllersList());
        refreshTables(tables);
    }
    /**
     * Calls the refreshTable method for all canteen managers windows except for the ones active in the foreground.
     */
    public static void refreshCanteenTables() {
        List<TableWindowControllerInterface> tables = new ArrayList<>();
        tables.addAll(ActiveControllersList.getCanteenManagerControllersList());
        refreshTables(tables);
    }
    /**
     * Calls the refreshTable method for all workday managers windows except for the ones active in the foreground.
     */
    public static void refreshWorkDayTables() {
        List<TableWindowControllerInterface> tables = new ArrayList<>();
        tables.addAll(ActiveControllersList.getWorkDayManagerControllersList());
        refreshTables(tables);
    }

}
