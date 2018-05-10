package ChildCareTech.controller;

import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.services.AccessorStageService;
import ChildCareTech.services.MainSceneManager;
import ChildCareTech.services.MainStageService;
import ChildCareTech.services.SessionService;
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

public class AddDishController {
    @FXML
    protected TextField nameField;
    @FXML
    protected TableView<FoodDTO> availableIngredientsTable;
    @FXML
    protected TableView<FoodDTO> selectedIngredientsTable;

    public void initialize() {
        TableColumn<FoodDTO, Boolean> isDrinkColumn  = new TableColumn<>("Bevanda?");
        isDrinkColumn.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().isDrink()));
        availableIngredientsTable.getColumns().add(isDrinkColumn);
        selectedIngredientsTable.getColumns().add(isDrinkColumn);

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
