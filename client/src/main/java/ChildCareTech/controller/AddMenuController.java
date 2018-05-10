package ChildCareTech.controller;

import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.common.DTO.MealDTO;
import ChildCareTech.common.DTO.MenuDTO;
import ChildCareTech.services.SessionService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Set;

public class AddMenuController {
    @FXML
    protected TableView<DishDTO> availableDishesTable;
    @FXML
    protected TableView<DishDTO> selectedDishesTable;

    private MealDTO currentMealDTO;

    public void initData(MealDTO mealDTO){
        currentMealDTO = mealDTO;

        try{
            availableDishesTable.getItems().clear();
            availableDishesTable.getItems().addAll(SessionService.getSession().getAllDishes());
        } catch (RemoteException e){
            e.printStackTrace();
        }
    }

    @FXML
    protected void addDishButtonAction(ActionEvent event){
        int rowIndex = availableDishesTable.getSelectionModel().getFocusedIndex();

        selectedDishesTable.getItems().add(availableDishesTable.getItems().get(rowIndex));
        availableDishesTable.getItems().remove(rowIndex);
    }

    @FXML
    protected void removeDishButtonAction(ActionEvent event){
        int rowIndex = selectedDishesTable.getSelectionModel().getFocusedIndex();

        availableDishesTable.getItems().add(selectedDishesTable.getItems().get(rowIndex));
        selectedDishesTable.getItems().remove(rowIndex);
    }

    @FXML
    protected void saveButtonAction(ActionEvent event){
        Set<DishDTO> dishes = new HashSet<>(selectedDishesTable.getItems());
        MenuDTO menuDTO = new MenuDTO(0, currentMealDTO, 0, dishes);
        currentMealDTO.set
    }
}
