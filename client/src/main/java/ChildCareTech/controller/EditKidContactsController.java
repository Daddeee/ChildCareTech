package ChildCareTech.controller;

import ChildCareTech.Client;
import ChildCareTech.common.DTO.*;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.services.AccessorWindowService;
import ChildCareTech.services.ObservableDTOs.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EditKidContactsController implements AccessorWindowController{
    @FXML
    private TableView<ObservablePersonInterface> contactsTable;
    @FXML
    private TableView<ObservablePersonInterface> personsTable;
    @FXML
    private TableColumn<ObservablePersonInterface, String> firstNameColumnC;
    @FXML
    private TableColumn<ObservablePersonInterface, String> lastNameColumnC;
    @FXML
    private TableColumn<ObservablePersonInterface, String> FCColumnC;
    @FXML
    private TableColumn<ObservablePersonInterface, String> firstNameColumnP;
    @FXML
    private TableColumn<ObservablePersonInterface, String> lastNameColumnP;
    @FXML
    private TableColumn<ObservablePersonInterface, String> FCColumnP;
    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button saveButton;

    private ObservableList<ObservablePersonInterface> contacts = FXCollections.observableArrayList();
    private ObservableList<ObservablePersonInterface> persons = FXCollections.observableArrayList();
    private AccessorWindowService accessorWindowService;
    private KidDTO kidDTO = null;


    public void initialize() {
        initTable();
        initMenu();
    }

    @FXML
    public void addButtonAction(ActionEvent event) {
        ObservablePersonInterface selected = personsTable.getSelectionModel().getSelectedItem();
        if(selected == null) return;
        contacts.add(selected);
        persons.remove(selected);
    }
    @FXML
    public void removeButtonAction(ActionEvent event) {
        ObservablePersonInterface selected = contactsTable.getSelectionModel().getSelectedItem();
        if(selected == null) return;
        contacts.remove(selected);
        persons.add(selected);
    }
    @FXML
    public void saveButtonAction(ActionEvent event) {
        Set<AdultDTO> adults = new HashSet<>();
        for(ObservablePersonInterface observablePersonInterface : contacts) {
            AdultDTO adultDTO = (AdultDTO) observablePersonInterface.getDTO();
            //adultDTO.getContacts().add(kidDTO);  ??????
            adults.add(adultDTO);
        }
        try {
            KidDTO newKid = new KidDTO(kidDTO.getId(), kidDTO.getPerson(), kidDTO.getFirstTutor(), kidDTO.getSecondTutor(), kidDTO.getPediatrist(), adults);
            Client.getSessionService().getSession().updateKid(newKid);
            accessorWindowService.close();
            accessorWindowService.refreshTable();
        } catch (RemoteException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        } catch (UpdateFailedException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void setAccessorWindowService(AccessorWindowService accessorWindowService) {
        this.accessorWindowService = accessorWindowService;
    }
    private void initMenu() {
        removeButton.setDisable(true);
        addButton.setDisable(true);

        contactsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                removeButton.setDisable(false);
                addButton.setDisable(true);
            }
            else {
                removeButton.setDisable(true);
                addButton.setDisable(true);
            }
        });
        personsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                removeButton.setDisable(true);
                addButton.setDisable(false);
            }
            else {
                removeButton.setDisable(true);
                addButton.setDisable(true);
            }
        });
    }
    private void initTable() {
        List<AdultDTO> adults = new ArrayList<>();

        firstNameColumnC.setCellValueFactory(new PropertyValueFactory<ObservablePersonInterface, String>("firstName"));
        lastNameColumnC.setCellValueFactory(new PropertyValueFactory<ObservablePersonInterface, String>("lastName"));
        FCColumnC.setCellValueFactory(new PropertyValueFactory<ObservablePersonInterface, String>("fiscalCode"));
        firstNameColumnP.setCellValueFactory(new PropertyValueFactory<ObservablePersonInterface, String>("firstName"));
        lastNameColumnP.setCellValueFactory(new PropertyValueFactory<ObservablePersonInterface, String>("lastName"));
        FCColumnP.setCellValueFactory(new PropertyValueFactory<ObservablePersonInterface, String>("fiscalCode"));

        contactsTable.setItems(contacts);
        personsTable.setItems(persons);

        try {
            adults = Client.getSessionService().getSession().getAllAdults();
        } catch(RemoteException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        persons.clear();
        contacts.clear();
        for(AdultDTO adultDTO : adults) {
            persons.add(new ObservableAdult(adultDTO));
        }

    }
    public void initContacts(KidDTO kidDTO) {
        this.kidDTO = kidDTO;
        Set<AdultDTO> contacts = kidDTO.getContacts();
        contacts.clear();
        for(AdultDTO adultDTO : contacts) {
            this.contacts.add(new ObservableAdult(adultDTO));
            this.persons.remove(new ObservableAdult(adultDTO));
        }
    }
}
