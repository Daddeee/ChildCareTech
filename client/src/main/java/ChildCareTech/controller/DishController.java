package ChildCareTech.controller;

import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.services.AccessorSceneManager;
import ChildCareTech.services.MainSceneManager;
import ChildCareTech.services.SessionService;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class DishController {
    @FXML
    protected TableView<DishDTO> dishesTable;

    public void initialize(){
        List<DishDTO> dishes = Collections.emptyList();
        try{
            dishes = SessionService.getSession().getAllDishes();
        } catch(Exception e){
            e.printStackTrace();
        }

        dishesTable.getItems().clear();
        dishesTable.getItems().addAll(dishes);

        dishesTable.setRowFactory(dishDTOTableView -> {
            final TableRow<DishDTO> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();

            final MenuItem updateDishItem = new MenuItem("Modifica");
            updateDishItem.setOnAction(event -> {
                contextMenu.hide();
                try{
                    AccessorSceneManager.loadUpdateDish(row.getItem());
                } catch (IOException e){
                    e.printStackTrace();
                }
            });
            contextMenu.getItems().add(updateDishItem);

            final MenuItem showDishItem = new MenuItem("Dettagli");
            showDishItem.setOnAction(event -> {
                contextMenu.hide();
                try{
                    AccessorSceneManager.loadShowDish(row.getItem());
                } catch (IOException e){
                    e.printStackTrace();
                }
            });
            contextMenu.getItems().add(showDishItem);

            final MenuItem deleteDishItem = new MenuItem("Elimina");
            deleteDishItem.setOnAction(event -> {
                contextMenu.hide();
                try {
                    SessionService.getSession().deleteDish(row.getItem());
                    MainSceneManager.loadDish();
                } catch (IOException e){
                    e.printStackTrace();
                }
            });
            contextMenu.getItems().add(deleteDishItem);

            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                            .then((ContextMenu) null)
                            .otherwise(contextMenu)
            );

            return row;
        });
    }

    @FXML
    public void addDishButtonAction(ActionEvent event) {
        try {
            AccessorSceneManager.loadAddDish();
        } catch (IOException ex) {
            System.err.println("Can't load addTrip window");
            ex.printStackTrace();
        }
    }

    @FXML
    public void addDrinkButtonAction(ActionEvent event) {
        /*try {
            AccessorSceneManager.loadAddTrip();
        } catch (IOException ex) {
            System.err.println("Can't load addTrip window");
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
}
