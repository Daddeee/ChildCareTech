package ChildCareTech.controller;

import ChildCareTech.Client;
import ChildCareTech.common.DTO.DishDTO;
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
import javafx.scene.control.Tooltip;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KitchenController implements AccessorWindowController, TableWindowControllerInterface {
    @FXML
    private TableView<DishDTO> dishesTable;
    @FXML
    private TableColumn<DishDTO, String> nameColumn;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button detailsButton;
    @FXML
    private Button foodsButton;

    private AccessorWindowService accessorWindowService;
    private AccessorWindowService childWindowService;
    private ObservableList<DishDTO> dishes = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        childWindowService = new AccessorWindowService(this);
        initMenu();
        dishesTable.setItems(dishes);
        refreshTable();
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        initButtonsToolTips();
    }
    @FXML
    public void addButtonAction(ActionEvent event) {
        try {
            childWindowService.loadAddDishWindow();
        } catch (IOException ex) {
            System.err.println("Can't load addDish window");
            ex.printStackTrace();
        }
    }
    @FXML
    public void deleteButtonAction(ActionEvent event) {
        DishDTO selected = dishesTable.getSelectionModel().getSelectedItem();
        if(selected == null) return;
        try {
            Client.getSessionService().getSession().deleteDish(selected);
            refreshTable();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    public void detailsButtonAction(ActionEvent event) {
        DishDTO selected = dishesTable.getSelectionModel().getSelectedItem();
        if(selected == null) return;
        try{
            childWindowService.loadShowDishWindow(selected);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    public void editButtonAction(ActionEvent event) {
        DishDTO selected = dishesTable.getSelectionModel().getSelectedItem();
        if(selected == null) return;
        try{
            childWindowService.loadUpdateDishWindow(selected);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    public void foodsButtonAction(ActionEvent event) {
        try{
            childWindowService.loadFoodsListWindow();
        } catch (IOException e){
            System.err.println("error loading foods window");
            e.printStackTrace();
        }
    }

    public void setAccessorWindowService(AccessorWindowService accessorWindowService) {
        this.accessorWindowService = accessorWindowService;
        this.accessorWindowService.setOnCloseAction(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                refreshParentTable();
                clearInstance();
            }
        });
    }
    public void initMenu() {
        deleteButton.setDisable(true);
        detailsButton.setDisable(true);
        editButton.setDisable(true);

        dishesTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                deleteButton.setDisable(false);
                detailsButton.setDisable(false);
                editButton.setDisable(false);
            }
            else {
                deleteButton.setDisable(true);
                detailsButton.setDisable(true);
                editButton.setDisable(true);
            }
        });
    }
    public void refreshTable() {
        List<DishDTO> dishesList = new ArrayList<>();
        try{
            dishesList = Client.getSessionService().getSession().getAllDishes();
        } catch(Exception e){
            e.printStackTrace();
        }
        dishes.clear();
        dishes.addAll(dishesList);
    }
    public void refreshParentTable() {
        accessorWindowService.refreshTable();
    }
    public void notifyUpdate() { }
    public void clearInstance() {
        ActiveControllersList.removeDishController(this);
    }
    private void initButtonsToolTips() {
        addButton.setTooltip(new Tooltip("Registrazione nuovo piatto"));
        deleteButton.setTooltip(new Tooltip("Cancellazione piatto"));
        detailsButton.setTooltip(new Tooltip("Mostra dettagli del piatto"));
        editButton.setTooltip(new Tooltip("Modifica piatto"));
        foodsButton.setTooltip(new Tooltip("Gestione della dispensa"));
    }
}
