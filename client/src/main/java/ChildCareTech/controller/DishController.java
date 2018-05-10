package ChildCareTech.controller;

import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.services.AccessorSceneManager;
import ChildCareTech.services.MainSceneManager;
import ChildCareTech.services.SessionService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
