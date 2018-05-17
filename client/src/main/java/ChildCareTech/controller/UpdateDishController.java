package ChildCareTech.controller;

import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.services.AccessorWindowService;
import ChildCareTech.services.SessionService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.rmi.RemoteException;
import java.util.*;

public class UpdateDishController implements AccessorWindowController{
    @FXML
    protected TextField nameField;
    @FXML
    protected TableView<FoodDTO> availableIngredientsTable;
    @FXML
    protected TableView<FoodDTO> selectedIngredientsTable;

    private DishDTO currentDishDTO;
    private AccessorWindowService accessorWindowService;

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
            accessorWindowService.close();
            accessorWindowService.refreshTable();
        } catch (RemoteException e) {
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
    public void setAccessorWindowService(AccessorWindowService accessorWindowService) {
        this.accessorWindowService = accessorWindowService;
    }
}
