package ChildCareTech.controller;

import ChildCareTech.common.DTO.FoodDTO;
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

public class FoodController {
    @FXML
    private TableView<FoodDTO> foodsTable;

    private ObservableList<FoodDTO> items = FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        refreshTable();

        foodsTable.setRowFactory(tripDTOTableView -> {
            final TableRow<FoodDTO> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();

            final MenuItem showFood = new MenuItem("Dettagli");
            showFood.setOnAction(event ->  {
                contextMenu.hide();
                /*try {
                    AccessorSceneManager.loadShowBus(row.getItem());
                } catch (IOException ex) {
                    System.err.println("Can't load showFood window");
                    ex.printStackTrace();
                }*/
            });
            contextMenu.getItems().add(showFood);

            final MenuItem deleteFood = new MenuItem("Elimina");
            deleteFood.setOnAction(event -> {
                contextMenu.hide();
                /*try {
                    SessionService.getSession().removeBus(row.getItem());
                } catch (RemoteException ex) {
                    System.err.println("error remote");
                    ex.printStackTrace();
                }*/
                refreshTable();
            });
            contextMenu.getItems().add(deleteFood);

            final MenuItem updateFood = new MenuItem("Modifica");
            updateFood.setOnAction(event -> {
                contextMenu.hide();
                /*try {
                    AccessorSceneManager.loadUpdateBus(row.getItem());
                } catch (IOException ex) {
                    System.err.println("Can't load updateFood window");
                    ex.printStackTrace();
                }*/
                refreshTable();
                System.out.println("modifica");
            });
            contextMenu.getItems().add(updateFood);

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
        /*try {
            AccessorSceneManager.loadAddBus();
        } catch (IOException ex) {
            System.err.println("Can't load addKid window");
            ex.printStackTrace();
        }*/
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
        List<FoodDTO> busesDTOList = new ArrayList<>();

        try {
            busesDTOList = SessionService.getSession().getAllFoods();
        } catch(RemoteException e){
            e.printStackTrace();
        }

        items.clear();
        items.addAll(busesDTOList);
        foodsTable.setItems(items);
    }
}
