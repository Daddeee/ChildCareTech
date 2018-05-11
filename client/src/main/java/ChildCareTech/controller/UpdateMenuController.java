package ChildCareTech.controller;

import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.common.DTO.MealDTO;
import ChildCareTech.common.DTO.MenuDTO;
import ChildCareTech.services.AccessorStageService;
import ChildCareTech.services.MainSceneManager;
import ChildCareTech.services.SessionService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.rmi.RemoteException;
import java.util.*;

public class UpdateMenuController {
    @FXML
    protected TableView<DishDTO> availableDishesTable;
    @FXML
    protected TableView<DishDTO> selectedDishesTable;

    private MealDTO currentMealDTO;

    public void initData(MealDTO mealDTO){
        currentMealDTO = mealDTO;
        List<DishDTO> availableDishes = new ArrayList<>();
        try{
            availableDishes = SessionService.getSession().getAllDishes();
        } catch (RemoteException e){
            e.printStackTrace();
        }

        availableDishes.removeIf(dishDTO -> {
            for(DishDTO d : currentMealDTO.getMenu().getDishes())
                if(d.getId() == dishDTO.getId()) return true;

            return false;
        });

        availableDishesTable.getItems().clear();
        availableDishesTable.getItems().addAll(availableDishes);

        selectedDishesTable.getItems().clear();
        selectedDishesTable.getItems().addAll(currentMealDTO.getMenu().getDishes());
    }

    @FXML
    protected void addDishButtonAction(ActionEvent event){
        try {
            int rowIndex = availableDishesTable.getSelectionModel().getFocusedIndex();
            SessionService.getSession().addDishToMenu(currentMealDTO.getMenu(), availableDishesTable.getItems().get(rowIndex));
            selectedDishesTable.getItems().add(availableDishesTable.getItems().get(rowIndex));
            availableDishesTable.getItems().remove(rowIndex);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void removeDishButtonAction(ActionEvent event){
        try {
            int rowIndex = selectedDishesTable.getSelectionModel().getFocusedIndex();
            SessionService.getSession().removeDishFromMenu(currentMealDTO.getMenu(), selectedDishesTable.getItems().get(rowIndex));
            availableDishesTable.getItems().add(selectedDishesTable.getItems().get(rowIndex));
            selectedDishesTable.getItems().remove(rowIndex);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void updateButtonAction(ActionEvent event){
        try{
            AccessorStageService.close();
            MainSceneManager.loadCanteen();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
