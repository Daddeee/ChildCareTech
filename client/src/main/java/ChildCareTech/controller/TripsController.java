package ChildCareTech.controller;

import ChildCareTech.common.DTO.TripDTO;
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
        refreshTable();
        tripsTable.setItems(items);

        tripsTable.setRowFactory(tripDTOTableView -> {
            final TableRow<TripDTO> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();

            final MenuItem showTrip = new MenuItem("Dettagli");
            showTrip.setOnAction(event ->  {
                    contextMenu.hide();
                    try {
                        AccessorSceneManager.loadShowTrip(row.getItem());
                    } catch (IOException ex) {
                        System.err.println("Can't load addTrip window");
                        ex.printStackTrace();
                    }
            });
            contextMenu.getItems().add(showTrip);

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
