package ChildCareTech.controller;

import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.services.AccessorSceneManager;
import ChildCareTech.services.MainSceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
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
    public void initialize() {
        tripsTable.setItems(items);
        populateTable();
    }

    @FXML
    public void addButtonAction(ActionEvent event) {

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
        items.add(new TripDTO("meta1", "note1", LocalDate.now(), LocalDate.now(), null, null));
        items.add(new TripDTO("meta2", "note2", LocalDate.now(), LocalDate.now(), null, null));
    }
}
