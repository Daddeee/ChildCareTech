package ChildCareTech.controller;

import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.EventStatus;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.services.AccessorSceneManager;
import ChildCareTech.services.MainSceneManager;
import ChildCareTech.services.SessionService;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;

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


    private ObservableList<TripDTO> items = FXCollections.observableArrayList();


    public TripsController() {}

    @FXML
    public void initialize() {
        tripsTable.setItems(items);
        refreshTable();

        tripsTable.setRowFactory(tripDTOTableView -> {
            final TableRow<TripDTO> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();

            final MenuItem showTrip = new MenuItem("Dettagli");
            showTrip.setOnAction(event ->  {
                    contextMenu.hide();
                    try {
                        AccessorSceneManager.loadShowTrip(row.getItem());
                    } catch (IOException ex) {
                        System.err.println("Can't load showTrip window");
                        ex.printStackTrace();
                    }
            });

            final MenuItem deleteTrip = new MenuItem("Elimina");
            deleteTrip.setOnAction(event -> {
                contextMenu.hide();
                try {
                    SessionService.getSession().removeTrip(row.getItem());
                } catch (RemoteException ex) {
                    System.err.println("error remote");
                    ex.printStackTrace();
                }
                refreshTable();
            });

            final MenuItem updateTrip = new MenuItem("Modifica");
            updateTrip.setOnAction(event -> {
                contextMenu.hide();
                try {
                    AccessorSceneManager.loadUpdateTrip(row.getItem());
                } catch (IOException ex) {
                    System.err.println("Can't load updateTrip window");
                    ex.printStackTrace();
                }
                refreshTable();
            });

            final MenuItem manageTripPartecipations = new MenuItem("Iscrizioni");
            manageTripPartecipations.setOnAction(event -> {
                contextMenu.hide();
                try {
                    AccessorSceneManager.loadTripPartecipationManagement(row.getItem());
                } catch (IOException ex) {
                    System.err.println("Can't load trip partecipation window");
                    ex.printStackTrace();
                }
                refreshTable();
            });

            final MenuItem manageTripRoutes = new MenuItem("Gestione tragitto");
            manageTripRoutes.setOnAction(event -> {
                contextMenu.hide();
            });

            contextMenu.getItems().add(showTrip);
            contextMenu.getItems().add(deleteTrip);
            contextMenu.getItems().add(updateTrip);
            contextMenu.getItems().add(manageTripPartecipations);
            contextMenu.getItems().add(manageTripRoutes);


            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                            .then((ContextMenu) null)
                            .otherwise(contextMenu)
            );

            return row;
        });
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

    private void refreshTable(){
        List<TripDTO> tripsDTOList = new ArrayList<>();

        try {
            tripsDTOList = SessionService.getSession().getAllTrips();
        } catch(RemoteException e){
            e.printStackTrace();
        }

        items.clear();
        items.addAll(tripsDTOList);
    }
}
