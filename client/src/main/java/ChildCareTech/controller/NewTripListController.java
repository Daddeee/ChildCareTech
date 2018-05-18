package ChildCareTech.controller;

import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.EventStatus;
import ChildCareTech.services.AccessorWindowService;
import ChildCareTech.services.SessionService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class NewTripListController implements TableWindowControllerInterface {
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button detailsButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button busButton;
    @FXML
    private Button subscriptionsButton;
    @FXML
    private Button journeyManagerButton;

    @FXML
    private TableView<TripDTO> tripsTable;
    private ObservableList<TripDTO> items = FXCollections.observableArrayList();
    private AccessorWindowService accessorWindowService;

    @FXML
    public void initialize() {
        accessorWindowService = new AccessorWindowService(this);
        tripsTable.setItems(items);
        refreshTable();
        initMenu();
    }

    @FXML
    public void busButtonAction(ActionEvent event) {
        try {
            accessorWindowService.loadBusListWindow();
        } catch (IOException ex) {
            System.err.println("Can't load bus window");
            ex.printStackTrace();
        }
    }

    @FXML
    public void addButtonAction(ActionEvent event) {
        try {
            accessorWindowService.loadAddTripWindow();
        } catch (IOException ex) {
            System.err.println("Can't load addTrip window");
            ex.printStackTrace();
        }
    }

    @FXML
    public void detailsButtonAction(ActionEvent event) {
        try {
            accessorWindowService.loadShowTripWindow(tripsTable.getSelectionModel().getSelectedItem());
        } catch (IOException ex) {
            System.err.println("Can't load showTrip window");
            ex.printStackTrace();
        }
    }

    @FXML
    public void editButtonAction(ActionEvent event) {
        if(!tripsTable.getSelectionModel().getSelectedItem().getStatus().equals(EventStatus.WAIT)){
            //stampa errore: impossibile modificare una gita avviata/terminata
            return;
        }

        try {
            accessorWindowService.loadUpdateTripWindow(tripsTable.getSelectionModel().getSelectedItem());
        } catch (IOException ex) {
            System.err.println("Can't load updateTrip window");
            ex.printStackTrace();
        }
    }

    @FXML
    public void deleteButtonAction(ActionEvent event) {
        try {
            SessionService.getSession().removeTrip(tripsTable.getSelectionModel().getSelectedItem());
        } catch (RemoteException ex) {
            System.err.println("error remote");
            ex.printStackTrace();
        }
        refreshTable();
    }

    @FXML
    public void subscriptionsButtonAction(ActionEvent event) {
        if(!tripsTable.getSelectionModel().getSelectedItem().getStatus().equals(EventStatus.WAIT)){
            //stampa errore: impossibile gestire le iscrizioni di una gita già avviata/terminata
            return;
        }

        try {
            accessorWindowService.loadTripPartecipationWindow(tripsTable.getSelectionModel().getSelectedItem());
        } catch (IOException ex) {
            System.err.println("Can't load trip partecipation window");
            ex.printStackTrace();
        }
    }

    @FXML
    public void journeyManagerButtonAction(ActionEvent event) {
        if(!tripsTable.getSelectionModel().getSelectedItem().getStatus().equals(EventStatus.OPEN)){
            //stampa errore: impossibile gestire il tragitto di una gita non avviata
            return;
        }

        try {
            accessorWindowService.loadTripRoutesWindow(tripsTable.getSelectionModel().getSelectedItem());
        } catch (IOException ex) {
            System.err.println("Can't load trip partecipation window");
            ex.printStackTrace();
        }
    }

    public void refreshTable(){
        List<TripDTO> tripsDTOList = new ArrayList<>();

        try {
            tripsDTOList = SessionService.getSession().getAllTrips();
        } catch(RemoteException e){
            e.printStackTrace();
        }

        items.clear();
        items.addAll(tripsDTOList);
    }

    private void initMenu() {

        editButton.setDisable(true);
        detailsButton.setDisable(true);
        deleteButton.setDisable(true);
        subscriptionsButton.setDisable(true);
        journeyManagerButton.setDisable(true);

        tripsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                editButton.setDisable(false);
                detailsButton.setDisable(false);
                deleteButton.setDisable(false);
                subscriptionsButton.setDisable(false);
                journeyManagerButton.setDisable(false);
            }
            else {
                editButton.setDisable(true);
                detailsButton.setDisable(true);
                deleteButton.setDisable(true);
                subscriptionsButton.setDisable(true);
                journeyManagerButton.setDisable(true);
            }
        });
    }
}
