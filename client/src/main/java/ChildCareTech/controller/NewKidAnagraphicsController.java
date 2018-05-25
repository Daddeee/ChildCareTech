package ChildCareTech.controller;

import ChildCareTech.Client;
import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.services.AccessorWindowService;
import ChildCareTech.services.ObservableDTOs.ObservableKid;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NewKidAnagraphicsController implements TableWindowControllerInterface {
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;
    @FXML
    private Button detailsButton;
    @FXML
    private Button contactsButton;
    @FXML
    private Button allergiesButton;
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

    private AccessorWindowService accessorWindowService;
    private ObservableList<ObservableKid> items = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        accessorWindowService = new AccessorWindowService(this);
        initTable();
        initMenu();
    }

    @FXML
    public void addButtonAction(ActionEvent event) {
        try {
            accessorWindowService.loadAddKidWindow();
        } catch (IOException ex) {
            System.err.println("Can't load addKid window");
            ex.printStackTrace();
        }
    }
    @FXML
    public void editButtonAction(ActionEvent event) {
        try {
            accessorWindowService.loadEditKidWindow(personTable.getSelectionModel().getSelectedItem());
        } catch (IOException ex) {
            System.err.println("can't load editKid window");
            ex.printStackTrace();
        }
        refreshTable();
    }
    @FXML
    public void contactsButtonAction(ActionEvent event) {
        ObservableKid observableKid = personTable.getSelectionModel().getSelectedItem();
        if(observableKid == null)
            return;
        try {
            accessorWindowService.loadEditKidContactsWindow(observableKid);
        } catch(IOException ex) {
            System.err.println("can't load edit kid contacts window");
            ex.printStackTrace();
        }
    }
    @FXML
    public void allergiesButtonAction(ActionEvent event) {
        try {
            accessorWindowService.loadEditKidAllergiesWindow(personTable.getSelectionModel().getSelectedItem());
        } catch (IOException ex) {
            System.err.println("error loading edit kid allergies window");
            ex.printStackTrace();
        }
    }
    @FXML
    public void deleteButtonAction(ActionEvent event) {
        try {
            Client.getSessionService().getSession().removeKid((personTable.getSelectionModel().getSelectedItem()).getDTO());
        } catch (RemoteException ex) {
            System.err.println("error remote");
            ex.printStackTrace();
        }
        refreshTable();
    }
    @FXML
    public void detailsButtonAction(ActionEvent event) {
        try {
            accessorWindowService.loadShowKidWindow(personTable.getSelectionModel().getSelectedItem());
        } catch (IOException ex) {
            System.err.println("Can't load show window");
            ex.printStackTrace();
        }
    }

    public void refreshTable() {
        List<KidDTO> kidDTOList = new ArrayList<>();
        try {
            kidDTOList = Client.getSessionService().getSession().getAllKids();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        items.clear();
        for (KidDTO kid : kidDTOList) {
            items.add(new ObservableKid(kid));
        }
    }

    private void initTable() {
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

    private void initMenu() {
        editButton.setDisable(true);
        detailsButton.setDisable(true);
        deleteButton.setDisable(true);
        contactsButton.setDisable(true);
        allergiesButton.setDisable(true);

        personTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                editButton.setDisable(false);
                detailsButton.setDisable(false);
                deleteButton.setDisable(false);
                contactsButton.setDisable(false);
                allergiesButton.setDisable(false);
            }
            else {
                editButton.setDisable(true);
                detailsButton.setDisable(true);
                deleteButton.setDisable(true);
                contactsButton.setDisable(true);
                allergiesButton.setDisable(true);
            }
        });
    }
    public void notifyUpdate() {
    }
}
