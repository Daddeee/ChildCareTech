package ChildCareTech.controller;

import ChildCareTech.Client;
import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.DTO.SupplyDTO;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.services.AccessorWindowService;
import ChildCareTech.services.ActiveControllersList;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class FoodsListController implements TableWindowControllerInterface, AccessorWindowController {
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
        refreshTable();
        foodsTable.setItems(foods);
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
    }

    @FXML
    public void addButtonAction(ActionEvent event) {
        String name = insertField.getText();
        if(name == null || name.equals("")) return;
        try {
            Client.getSessionService().getSession().saveFood(new FoodDTO(0, name, false, new HashSet<SupplyDTO>(), new HashSet<PersonDTO>()));
            refreshTable();
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
            Client.getSessionService().getSession().removeFood(selected);
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

    public void setAccessorWindowService(AccessorWindowService accessorWindowService) {
        this.accessorWindowService = accessorWindowService;
        this.accessorWindowService.setOnCloseAction(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                clearInstance();
            }
        });
    }

    public void refreshTable() {
        List<FoodDTO> foodsList = new ArrayList<>();
        try {
            foodsList = Client.getSessionService().getSession().getAllFoods();
        } catch (RemoteException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        foods.clear();
        foods.addAll(foodsList);
    }
    public void notifyUpdate() { }
    private void clearInstance() {
        ActiveControllersList.removeFoodController(this);
    }
}
