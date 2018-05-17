package ChildCareTech.controller;

import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.services.*;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddDishController implements AccessorWindowController{
    @FXML
    protected TextField nameField;
    @FXML
    protected TableView<FoodDTO> availableIngredientsTable;
    @FXML
    protected TableView<FoodDTO> selectedIngredientsTable;

    private AccessorWindowService accessorWindowService;

    public void initialize() {
        List<FoodDTO> availableIngredients = Collections.emptyList();
        try{
            availableIngredients = SessionService.getSession().getAllFoods();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        availableIngredientsTable.getItems().clear();
        availableIngredientsTable.getItems().addAll(availableIngredients);
    }

    @FXML
    protected void saveButtonAction(ActionEvent event){
        Set<FoodDTO> foods = new HashSet<>(selectedIngredientsTable.getItems());
        DishDTO dishDTO = new DishDTO(0, nameField.getText(), null, foods);

        try {
            SessionService.getSession().createDish(dishDTO);
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
