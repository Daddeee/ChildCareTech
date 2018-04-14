package ChildCareTech.controller;

import ChildCareTech.common.DTO.RouteDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.services.AccessorStageService;
import ChildCareTech.services.MainSceneManager;
import ChildCareTech.services.SessionService;
import ChildCareTech.utils.TempRouteData;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.HashSet;

public class UpdateTripController {
    @FXML
    private TextField metaField;
    @FXML
    private TextField noteField;
    @FXML
    private DatePicker depDateField;
    @FXML
    private DatePicker arrDateField;
    @FXML
    private Label alertLabel;
    @FXML
    private TextField departureLocationField;
    @FXML
    private TextField arrivalLocationField;

    @FXML
    private TableView<TempRouteData> routesTable;

    private TripDTO oldTrip;

    private ObservableList<TempRouteData> routes = FXCollections.observableArrayList();
    private int routeCounter = 0;

    public void initialize(){
        routesTable.setRowFactory(tempRouteDataTableView -> {
            final TableRow<TempRouteData> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();

            final MenuItem deleteRoute = new MenuItem("Elimina");
            deleteRoute.setOnAction(event -> {
                contextMenu.hide();
                removeRoute(row.getItem());
            });
            contextMenu.getItems().add(deleteRoute);

            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                            .then((ContextMenu) null)
                            .otherwise(contextMenu)
            );

            return row;
        });
    }

    public void initData(TripDTO tripDTO){
        oldTrip = tripDTO;

        metaField.setText(tripDTO.getMeta());
        noteField.setText(tripDTO.getNote());
        depDateField.setValue(tripDTO.getDepDate());
        arrDateField.setValue(tripDTO.getArrDate());

        for(RouteDTO r : tripDTO.getRoutes())
            addRoute(new TempRouteData(r.getId(), r.getRouteNumber(), r.getDepartureLocation(), r.getArrivalLocation()));

    }

    @FXML
    public void addRouteButtonAction(ActionEvent e) throws IOException{
        addRoute(new TempRouteData(0,routeCounter + 1, departureLocationField.getText(), arrivalLocationField.getText()));
    }

    @FXML
    public void cancelButtonAction(ActionEvent e){
        try {
            AccessorStageService.close();
        } catch (NoSuchFieldException ex) {
            System.err.println("Stage not initialized");
            ex.printStackTrace();
        }
    }

    @FXML
    public void updateButtonAction(ActionEvent e){
        int id = oldTrip.getId();
        String meta = metaField.getText();
        String note = noteField.getText();
        LocalDate depDate = depDateField.getValue();
        LocalDate arrDate = arrDateField.getValue();

        TripDTO tripDTO = new TripDTO(id, meta, note, depDate, arrDate, null, null, null);

        for(TempRouteData r : routes)
            tripDTO.getRoutes().add(r.getRouteDTO(tripDTO));

        try {
            SessionService.getSession().updateTrip(tripDTO);
        } catch (RemoteException ex) {
            System.err.println("error remote");
            ex.printStackTrace();
        } catch (UpdateFailedException ex) {
            alertLabel.setText("Aggiornamento non riuscito: " + ex.getMessage());
            return;
        }

        try {
            AccessorStageService.close();
            MainSceneManager.loadTrips();
        } catch (NoSuchFieldException ex) {
            System.err.println("Stage not initialized");
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void addRoute(TempRouteData added){
        routeCounter++;
        routes.add(added);
        routesTable.getItems().add(added);
    }

    private void removeRoute(TempRouteData removed){
        try {
            SessionService.getSession().removeRoute(removed.getRouteDTO(oldTrip));
        } catch (RemoteException ex) {
            System.err.println("error remote");
            ex.printStackTrace();
        }

        routes.remove(removed);

        routeCounter--;
        for(int i = 0; i < routeCounter; i++)
            routes.get(i).routeNumber = i + 1;

        refreshTable();
    }

    private void refreshTable() {
        routesTable.getItems().clear();
        routesTable.getItems().addAll(routes);
    }
}
