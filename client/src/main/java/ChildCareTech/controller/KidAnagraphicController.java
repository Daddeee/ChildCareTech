package ChildCareTech.controller;

import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.services.AccessorSceneManager;
import ChildCareTech.services.MainSceneManager;
import ChildCareTech.services.ObservableDTOs.ObservableKid;
import ChildCareTech.services.SessionService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.rmi.RemoteException;
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


    private ObservableList<ObservableKid> items = FXCollections.observableArrayList();


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
        refreshTable();
        personTable.setItems(items);
    }

    @FXML
    public void addButtonAction(ActionEvent event) {
        try {
            AccessorSceneManager.loadAddKid();
        } catch (IOException ex) {
            System.err.println("Can't load addKid window");
            ex.printStackTrace();
        }
        try {
            List<KidDTO> list = SessionService.getSession().getAllKids();
        } catch(RemoteException ex) {
            System.err.println("remote error");
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

    private void refreshTable() {
        List<KidDTO> kidDTOList = new ArrayList<>();
        try {
            kidDTOList = SessionService.getSession().getAllKids();
        } catch(RemoteException e){
            e.printStackTrace();
        }

        for(KidDTO kid : kidDTOList) {
            items.add(new ObservableKid(kid));
        }
    }
}
