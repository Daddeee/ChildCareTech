package ChildCareTech.controller;

import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.services.AccessorSceneManager;
import ChildCareTech.services.MainSceneManager;
import ChildCareTech.services.SessionService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TripsController {
    @FXML
    private Button addButton;
    @FXML
    private Button backButton;

    @FXML
    private TableView<TripDTO> tripsTable;
    @FXML
    private TableColumn<TripDTO, String> metaColumn;
    @FXML
    private TableColumn<TripDTO, String> noteColumn;
    @FXML
    private TableColumn<TripDTO, LocalDate> depDateColumn;
    @FXML
    private TableColumn<TripDTO, LocalDate> arrDateColumn;


    private ObservableList<TripDTO> items = FXCollections.observableArrayList();


    public TripsController() {}

    @FXML
    public void initialize(){
        populateTable();
    }

    @FXML
    public void addButtonAction(ActionEvent event) {
        try {
            AccessorSceneManager.loadAddTrip();
        } catch (IOException ex) {
            System.err.println("Can't load addTrip window");
            ex.printStackTrace();
        }
    }

    @FXML
    public void backButtonAction(ActionEvent event) {
        try {
            MainSceneManager.loadHome();
        } catch (IOException ex) {
            System.err.println("Can't load addKid window");
            ex.printStackTrace();
        }
    }

    private void populateTable(){
        List<TripDTO> tripsDTOList = new ArrayList<>();

        try {
            tripsDTOList = SessionService.getSession().getAllTrips();
        } catch(RemoteException e){
            e.printStackTrace();
        }

        tripsTable.getItems().addAll(tripsDTOList);
    }
}
