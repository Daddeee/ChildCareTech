package ChildCareTech.controller;

import ChildCareTech.Client;
import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.services.*;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.*;

public class AddDishController implements AccessorWindowController{
    @FXML
    protected TextField nameField;
    @FXML
    protected TableView<FoodDTO> availableIngredientsTable;
    @FXML
    private TableColumn<FoodDTO, String> availableName;
    @FXML
    protected TableView<FoodDTO> selectedIngredientsTable;
    @FXML
    private TableColumn<FoodDTO, String> selectedName;
    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;

    private AccessorWindowService accessorWindowService;
    private ObservableList<FoodDTO> availableFoods = FXCollections.observableArrayList();
    private ObservableList<FoodDTO> selectedFoods = FXCollections.observableArrayList();

    public void initialize() {
        initMenu();
        initTables();
    }

    @FXML
    protected void saveButtonAction(ActionEvent event){
        Set<FoodDTO> foods = new HashSet<>(selectedIngredientsTable.getItems());
        DishDTO dishDTO = new DishDTO(0, nameField.getText(), null, foods);

        try {
            Client.getSessionService().getSession().createDish(dishDTO);
            accessorWindowService.close();
            accessorWindowService.refreshTable();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void addIngredientButtonAction(ActionEvent event){
        FoodDTO selected = availableIngredientsTable.getSelectionModel().getSelectedItem();
        if(selected == null) return;
        selectedFoods.add(selected);
        availableFoods.remove(selected);
        selectedIngredientsTable.getSelectionModel().select(null);
       /* int rowIndex = availableIngredientsTable.getSelectionModel().getFocusedIndex();

        selectedIngredientsTable.getItems().add(availableIngredientsTable.getItems().get(rowIndex));
        availableIngredientsTable.getItems().remove(rowIndex);
        */
    }

    @FXML
    protected void removeIngredientButtonAction(ActionEvent event){
        FoodDTO selected = selectedIngredientsTable.getSelectionModel().getSelectedItem();
        if(selected == null) return;
        selectedFoods.remove(selected);
        availableFoods.add(selected);
       /* int rowIndex = selectedIngredientsTable.getSelectionModel().getFocusedIndex();

        availableIngredientsTable.getItems().add(selectedIngredientsTable.getItems().get(rowIndex));
        selectedIngredientsTable.getItems().remove(rowIndex);
        */
    }

    public void setAccessorWindowService(AccessorWindowService accessorWindowService) {
        this.accessorWindowService = accessorWindowService;
    }

    private void initMenu() {
        removeButton.setDisable(true);
        addButton.setDisable(true);

        selectedIngredientsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                removeButton.setDisable(false);
                addButton.setDisable(true);
            }
            else {
                removeButton.setDisable(true);
                addButton.setDisable(true);
            }
        });
        availableIngredientsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                removeButton.setDisable(true);
                addButton.setDisable(false);
            }
            else {
                removeButton.setDisable(true);
                addButton.setDisable(true);
            }
        });
    }
    private void initTables() {
        List<FoodDTO> availableFoodsList = new ArrayList<>();

        availableName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        selectedName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));

        try{
            availableFoodsList = Client.getSessionService().getSession().getAllFoods();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        availableFoods.clear();
        availableFoods.addAll(availableFoodsList);
        availableIngredientsTable.setItems(availableFoods);
        selectedIngredientsTable.setItems(selectedFoods);
    }
}
