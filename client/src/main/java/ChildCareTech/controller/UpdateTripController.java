package ChildCareTech.controller;

import ChildCareTech.Client;
import ChildCareTech.common.DTO.RouteDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.EventStatus;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.services.*;
import ChildCareTech.utils.RestrictedDatePicker;
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
import java.util.List;
import java.util.Set;

public class UpdateTripController implements AccessorWindowController{
    @FXML
    private TextField metaField;
    @FXML
    private TextField noteField;
    @FXML
    private RestrictedDatePicker depDateField;
    @FXML
    private RestrictedDatePicker arrDateField;
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
    private AccessorWindowService accessorWindowService;

    @FXML
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
            addRoute(new TempRouteData(r.getId(), r.getRouteNumber(), r.getDepartureLocation(), r.getArrivalLocation(), r.getStatus(), r.getDepartureEvent(), r.getArrivalEvent()));

    }

    @FXML
    public void addRouteButtonAction(ActionEvent e) throws IOException{
        addRoute(new TempRouteData(0,routeCounter + 1, departureLocationField.getText(), arrivalLocationField.getText(), EventStatus.WAIT, null, null));
    }

    @FXML
    public void cancelButtonAction(ActionEvent e){
        accessorWindowService.close();
    }

    @FXML
    public void updateButtonAction(ActionEvent e){
        int id = oldTrip.getId();
        String meta = metaField.getText();
        String note = noteField.getText();
        LocalDate depDate = depDateField.getValue();
        LocalDate arrDate = arrDateField.getValue();

        TripDTO tripDTO = new TripDTO(id, meta, note, depDate, arrDate, oldTrip.getStatus(),null, null, null);

        Set<RouteDTO> routeDTOSet = new HashSet<>();
        for(TempRouteData r : routes)
            routeDTOSet.add(r.getRouteDTO(tripDTO));
        tripDTO.setRoutes(routeDTOSet);

        try {
            Client.getSessionService().getSession().updateTrip(tripDTO);
            accessorWindowService.close();
        } catch (RemoteException ex) {
            System.err.println("error remote");
            ex.printStackTrace();
        } catch (UpdateFailedException ex) {
            alertLabel.setText("Aggiornamento non riuscito: " + ex.getMessage());
            return;
        }

        refreshTripList();
    }

    private void addRoute(TempRouteData added){
        routeCounter++;
        routes.add(added);
        routesTable.getItems().add(added);
    }

    private void removeRoute(TempRouteData removed){
        try {
            Client.getSessionService().getSession().removeRoute(removed.getRouteDTO(oldTrip));
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

    private void refreshTripList() {
        List<NewTripListController> list = ContainedWindowService.getTripsListList();
        for(NewTripListController ntlc : list ) {
            ntlc.refreshTable();
        }
    }
    public void setAccessorWindowService(AccessorWindowService accessorWindowService) {
        this.accessorWindowService = accessorWindowService;
    }
}
