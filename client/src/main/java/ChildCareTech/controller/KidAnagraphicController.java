package ChildCareTech.controller;

import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.Sex;
import ChildCareTech.services.AccessorSceneManager;
import ChildCareTech.services.MainSceneManager;
import ChildCareTech.services.ObservableDTOs.ObservableKid;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class KidAnagraphicController {

    @FXML
    private Button addButton;
    @FXML
    private Button backButton;
    @FXML
    private Button logOutButton;
    @FXML
    private TableView<ObservableKid> personTable;
    @FXML
    private TableColumn<ObservableKid, String> firstNameColumn;
    @FXML
    private TableColumn<ObservableKid, String> lastNameColumn;
    @FXML
    private TableColumn<ObservableKid, String> fiscalCodeColumn;
    @FXML
    private TableColumn<ObservableKid, LocalDate> bDateColumn;
    @FXML
    private TableColumn<ObservableKid, String> addressColumn;
    @FXML
    private TableColumn<ObservableKid, String> firstTutorFCColumn;
    @FXML
    private TableColumn<ObservableKid, String> secondTutorFCColumn;
    @FXML
    private TableColumn<ObservableKid, String> pediatristFCColumn;


    private ObservableList<ObservableKid> list = FXCollections.observableArrayList();


    public KidAnagraphicController() { }

    @FXML
    public void initialize() {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<ObservableKid, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<ObservableKid, String>("lastName"));
        fiscalCodeColumn.setCellValueFactory(new PropertyValueFactory<ObservableKid, String>("fiscalCode"));
        bDateColumn.setCellValueFactory(new PropertyValueFactory<ObservableKid, LocalDate>("birthDate"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<ObservableKid, String>("address"));
        firstTutorFCColumn.setCellValueFactory(new PropertyValueFactory<ObservableKid, String>("firstTutorFC"));
        secondTutorFCColumn.setCellValueFactory(new PropertyValueFactory<ObservableKid, String>("secondTutorFC"));
        pediatristFCColumn.setCellValueFactory(new PropertyValueFactory<ObservableKid, String>("pediatristFC"));
        personTable.setItems(list);
        populateTable();
    }

    @FXML
    public void addButtonAction(ActionEvent event) {
        try {
            AccessorSceneManager.loadAddPerson();
        } catch (IOException ex) {
            System.err.println("Can't load addKid window");
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

    private void populateTable() {

    }
}
