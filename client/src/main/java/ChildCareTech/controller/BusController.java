package ChildCareTech.controller;

import ChildCareTech.common.DTO.BusDTO;
import ChildCareTech.services.AccessorSceneManager;
import ChildCareTech.services.MainSceneManager;
import ChildCareTech.services.SessionService;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class BusController {
    @FXML
    private TableView<BusDTO> busesTable;

    private ObservableList<BusDTO> items = FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        refreshTable();

        busesTable.setRowFactory(tripDTOTableView -> {
            final TableRow<BusDTO> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();

            final MenuItem showBus = new MenuItem("Dettagli");
            showBus.setOnAction(event ->  {
                /*contextMenu.hide();
                try {
                    AccessorSceneManager.loadShowBus(row.getItem());
                } catch (IOException ex) {
                    System.err.println("Can't load showBus window");
                    ex.printStackTrace();
                }*/
                System.out.println("dettagli");
            });
            contextMenu.getItems().add(showBus);

            final MenuItem deleteBus = new MenuItem("Elimina");
            deleteBus.setOnAction(event -> {
                /*contextMenu.hide();
                try {
                    SessionService.getSession().removeTrip(row.getItem());
                } catch (RemoteException ex) {
                    System.err.println("error remote");
                    ex.printStackTrace();
                }
                refreshTable();*/
                System.out.println("elimina");
            });
            contextMenu.getItems().add(deleteBus);

            final MenuItem updateBus = new MenuItem("Modifica");
            updateBus.setOnAction(event -> {
                /*contextMenu.hide();
                try {
                    AccessorSceneManager.loadUpdateTrip(row.getItem());
                } catch (IOException ex) {
                    System.err.println("Can't load updateBus window");
                    ex.printStackTrace();
                }
                refreshTable();*/
                System.out.println("modifica");
            });
            contextMenu.getItems().add(updateBus);

            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                            .then((ContextMenu) null)
                            .otherwise(contextMenu)
            );

            return row;
        });
    }

    @FXML
    protected void addButtonAction(ActionEvent e){

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
        List<BusDTO> busesDTOList = new ArrayList<>();

        try {
            busesDTOList = SessionService.getSession().getAllBuses();
        } catch(RemoteException e){
            e.printStackTrace();
        }

        items.clear();
        items.addAll(busesDTOList);
        busesTable.setItems(items);
    }
}
