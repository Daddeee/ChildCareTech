package ChildCareTech.controller;

import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.services.*;
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


public class AddTripController {
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

    private ObservableList<TempRouteData> routes = FXCollections.observableArrayList();
    private int maxRouteNumber = 1;

    private void initialize(){
        routesTable.setRowFactory(tempRouteDataTableView -> {
            final TableRow<TempRouteData> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();

            final MenuItem deleteRoute = new MenuItem("Elimina");
            deleteRoute.setOnAction(event -> {
                contextMenu.hide();
                removeRoute(row.getItem());
                refreshTable();
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

    @FXML
    public void addRouteButtonAction(ActionEvent e) throws IOException{
        TempRouteData r = new TempRouteData(maxRouteNumber, departureLocationField.getText(), arrivalLocationField.getText());
        addRoute(r);
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
    public void saveButtonAction(ActionEvent e){
        String meta = metaField.getText();
        String note = noteField.getText();
        LocalDate depDate = depDateField.getValue();
        LocalDate arrDate = arrDateField.getValue();

        TripDTO tripDTO = new TripDTO(meta, note, depDate, arrDate, new HashSet<>(), null);

        for(TempRouteData r : routes)
            tripDTO.getRoutes().add(r.getRouteDTO(tripDTO));

        try {
            SessionService.getSession().saveTrip(tripDTO);
        } catch (RemoteException ex) {
            System.err.println("error remote");
            ex.printStackTrace();
        } catch (AddFailedException ex) {
            alertLabel.setText("Salvataggio non riuscito: " + ex.getMessage());
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
        maxRouteNumber++;
        routes.add(added);
        routesTable.getItems().add(added);
    }

    private void removeRoute(TempRouteData removed){
        routes.remove(removed);

        maxRouteNumber--;
        for(int i = 0; i < maxRouteNumber; i++)
            routes.get(i).routeNumber = i + 1;

        refreshTable();
    }

    private void refreshTable() {
        routesTable.getItems().clear();
        routesTable.getItems().addAll(routes);
    }
}
