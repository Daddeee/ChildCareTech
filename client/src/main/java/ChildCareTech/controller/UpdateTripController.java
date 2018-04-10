package ChildCareTech.controller;

import ChildCareTech.common.DTO.RouteDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.services.AccessorStageService;
import ChildCareTech.services.MainSceneManager;
import ChildCareTech.services.SessionService;
import ChildCareTech.utils.TempRouteData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

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
    private int maxRouteNumber = 1;

    private void initialize(){
        routesTable.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode() == KeyCode.CANCEL) {
                    TempRouteData removed = routesTable.getSelectionModel().getSelectedItem();
                    routes.remove(removed);

                    maxRouteNumber--;
                    for(int i = 0; i < maxRouteNumber; i++)
                        routes.get(i).routeNumber = i + 1;

                    routesTable.getItems().clear();
                    routesTable.getItems().addAll(routes);
                }
            }
        });
    }

    public void initData(TripDTO tripDTO){
        oldTrip = tripDTO;

        metaField.setText(tripDTO.getMeta());
        noteField.setText(tripDTO.getNote());
        depDateField.setValue(tripDTO.getDepDate());
        arrDateField.setValue(tripDTO.getArrDate());

        for(RouteDTO r : tripDTO.getRoutes())
            addRoute(r.getRouteNumber(), r.getDepartureLocation(), r.getArrivalLocation());

    }

    @FXML
    public void addRouteButtonAction(ActionEvent e) throws IOException {
        addRoute(maxRouteNumber, departureLocationField.getText(), arrivalLocationField.getText());
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
        String meta = metaField.getText();
        String note = noteField.getText();
        LocalDate depDate = depDateField.getValue();
        LocalDate arrDate = arrDateField.getValue();

        TripDTO tripDTO = new TripDTO(meta, note, depDate, arrDate, new HashSet<>(), null);

        for(TempRouteData r : routes)
            tripDTO.getRoutes().add(r.getRouteDTO(tripDTO));

        try {
            SessionService.getSession().updateTrip(oldTrip, tripDTO);
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

    private void addRoute(int routeNumber, String departureLocation, String arrivalLocation){
        TempRouteData r = new TempRouteData(
                routeNumber,
                departureLocation,
                arrivalLocation
        );
        maxRouteNumber++;
        routes.add(r);
        routesTable.getItems().add(r);
    }
}
