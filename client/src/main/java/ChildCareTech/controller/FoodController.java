package ChildCareTech.controller;

import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.services.AccessorSceneManager;
import ChildCareTech.services.MainSceneManager;
import ChildCareTech.services.SessionService;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class FoodController {
    @FXML
    private TableView<FoodDTO> foodsTable;

    private ObservableList<FoodDTO> items = FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        refreshTable();

        TableColumn<FoodDTO, Boolean> isDrinkColumn  =new TableColumn<>("Bevanda?");
        isDrinkColumn.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().isDrink()));
        foodsTable.getColumns().add(isDrinkColumn);

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
        try {
            AccessorSceneManager.loadAddFood();
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
