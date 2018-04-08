package ChildCareTech.controller;

import ChildCareTech.common.DTO.RouteDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.services.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private TextField routeNumberField;
    @FXML
    private TextField departureLocationField;
    @FXML
    private TextField arrivalLocationField;

    @FXML
    private TableView<TempRouteData> routesTable;

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

    @FXML
    public void addRouteButtonAction(ActionEvent e) throws IOException{
        TempRouteData r = new TempRouteData(maxRouteNumber, departureLocationField.getText(), arrivalLocationField.getText());
        maxRouteNumber++;
        routes.add(r);
        routesTable.getItems().add(r);
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

    public static class TempRouteData{
        public int routeNumber;
        public String departureLocation;
        public String arrivalLocation;

        public TempRouteData(int routeNumber, String departureLocation, String arrivalLocation){
            this.routeNumber = routeNumber;
            this.departureLocation = departureLocation;
            this.arrivalLocation = arrivalLocation;
        }

        public int getRouteNumber() { return routeNumber; }
        public String getDepartureLocation() { return departureLocation; }
        public String getArrivalLocation() { return arrivalLocation; }

        public RouteDTO getRouteDTO(TripDTO t) { return new RouteDTO(t, routeNumber, departureLocation, arrivalLocation); }
    }
}
