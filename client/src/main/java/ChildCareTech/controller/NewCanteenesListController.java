package ChildCareTech.controller;

import ChildCareTech.Client;
import ChildCareTech.common.DTO.CanteenDTO;
import ChildCareTech.services.AccessorWindowService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class NewCanteenesListController implements AccessorWindowController, TableWindowControllerInterface {

    @FXML
    private TableView<String> canteenesTable;
    @FXML
    private TableColumn<String, String> canteenColumn;
    @FXML
    private TextField insertField;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;

    private ObservableList<String> canteenNames = FXCollections.observableArrayList();
    private AccessorWindowService accessorWindowService;
    private AccessorWindowService childAccessor;

    @FXML
    public void initialize() {
        initMenu();
        initCanteenNames();
        canteenColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        childAccessor = new AccessorWindowService(this);
    }

    @FXML
    public void addButtonAction(ActionEvent event) {
        if(!insertField.getText().equals("") && insertField.getText() != null) {
            try {
                childAccessor.loadAddMealsWindow(insertField.getText());
                insertField.setText("");
            } catch (IOException ex) {
                System.err.println("can't load addMeal window");
                ex.printStackTrace();
            }
        }
        else {
            //gestione errore
        }
    }
    @FXML
    public void deleteButtonAction(ActionEvent event) {
        String canteenName = canteenesTable.getSelectionModel().getSelectedItem();
        CanteenDTO canteenDTO;
        try {
            canteenDTO = Client.getSessionService().getSession().getCanteenByName(canteenName);
            Client.getSessionService().getSession().removeCanteen(canteenDTO);
        } catch(RemoteException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        refreshParentTable();
        refreshTable();
    }

    public void setAccessorWindowService(AccessorWindowService accessorWindowService) {
        this.accessorWindowService = accessorWindowService;
    }

    private void initMenu() {
        deleteButton.setDisable(true);

        canteenesTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                deleteButton.setDisable(false);
            }
            else {
                deleteButton.setDisable(true);
            }
        });
    }

    public void initCanteenNames() {
        canteenesTable.setItems(canteenNames);
        refreshTable();
    }

    public void refreshTable() {
        List<String> names = new ArrayList<>();
        try {
            canteenNames.clear();
            names.addAll(Client.getSessionService().getSession().getAllCanteenNames());
            for(String name : names) {
                canteenNames.add(name);
            }
        } catch(RemoteException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    private void refreshParentTable() {
        accessorWindowService.refreshTable();
    }

}
