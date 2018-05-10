package ChildCareTech.controller;

import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.services.AccessorStageService;
import ChildCareTech.services.MainSceneManager;
import ChildCareTech.services.SessionService;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.*;

public class UpdateDishController {
    @FXML
    protected TextField nameField;
    @FXML
    protected TableView<FoodDTO> availableIngredientsTable;
    @FXML
    protected TableView<FoodDTO> selectedIngredientsTable;

    private DishDTO currentDishDTO;

    public void initData(DishDTO dishDTO){
        this.currentDishDTO = dishDTO;

        nameField.setText(dishDTO.getName());

        List<FoodDTO> availableIngredients = new ArrayList<>();
        try{
            availableIngredients = SessionService.getSession().getAllFoods();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        availableIngredients.removeIf(foodDTO -> {
            for(FoodDTO f : currentDishDTO.getFoods())
                if(f.getId() == foodDTO.getId()) return true;

            return false;
        });

        availableIngredientsTable.getItems().clear();
        availableIngredientsTable.getItems().addAll(availableIngredients);

        selectedIngredientsTable.getItems().clear();
        selectedIngredientsTable.getItems().addAll(currentDishDTO.getFoods());
    }

    @FXML
    protected void updateButtonAction(ActionEvent event){
        Set<FoodDTO> foods = new HashSet<>(selectedIngredientsTable.getItems());
        currentDishDTO.setFoods(foods);
        currentDishDTO.setName(nameField.getText());
        try {
            SessionService.getSession().updateDish(currentDishDTO);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        try {
            AccessorStageService.close();
            MainSceneManager.loadDish();
        } catch (IOException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void addIngredientButtonAction(ActionEvent event){
        int rowIndex = availableIngredientsTable.getSelectionModel().getFocusedIndex();

        selectedIngredientsTable.getItems().add(availableIngredientsTable.getItems().get(rowIndex));
        availableIngredientsTable.getItems().remove(rowIndex);
    }

    @FXML
    protected void removeIngredientButtonAction(ActionEvent event){
        int rowIndex = selectedIngredientsTable.getSelectionModel().getFocusedIndex();

        availableIngredientsTable.getItems().add(selectedIngredientsTable.getItems().get(rowIndex));
        selectedIngredientsTable.getItems().remove(rowIndex);
    }
}
