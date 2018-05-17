package ChildCareTech.controller;

import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.DTO.SupplyDTO;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.services.AccessorWindowService;
import ChildCareTech.services.SessionService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class FoodsListController implements AccessorWindowController {
    @FXML
    private TableView<FoodDTO> foodsTable;
    @FXML
    private TableColumn<FoodDTO, String> nameColumn;
    @FXML
    private TextField insertField;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;

    private ObservableList<FoodDTO> foods = FXCollections.observableArrayList();
    private AccessorWindowService accessorWindowService;

    @FXML
    public void initialize() {
        initMenu();
        initFoods();
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
    }

    @FXML
    public void addButtonAction(ActionEvent event) {
        String name = insertField.getText();
        if(name == null || name.equals("")) return;
        try {
            SessionService.getSession().saveFood(new FoodDTO(0, name, false, 0, new HashSet<SupplyDTO>(), new HashSet<PersonDTO>()));
            initFoods();
        } catch (RemoteException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        } catch(AddFailedException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    @FXML
    public void deleteButtonAction(ActionEvent event) {
        FoodDTO selected = foodsTable.getSelectionModel().getSelectedItem();
        if (selected == null) return;
        try {
            SessionService.getSession().removeFood(selected);
        } catch (RemoteException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void initMenu() {
        deleteButton.setDisable(true);

        foodsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                deleteButton.setDisable(false);
            }
            else {
                deleteButton.setDisable(true);
            }
        });
    }
    private void initFoods() {
        List<FoodDTO> foodsList = new ArrayList<>();

        foodsTable.setItems(foods);
        try {
            foodsList = SessionService.getSession().getAllFoods();
        } catch (RemoteException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        foods.clear();
        foods.addAll(foodsList);
    }

    public void setAccessorWindowService(AccessorWindowService accessorWindowService) {
        this.accessorWindowService = accessorWindowService;
    }
}
