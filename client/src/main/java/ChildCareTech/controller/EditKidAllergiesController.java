package ChildCareTech.controller;

import ChildCareTech.Client;
import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.services.AccessorWindowService;
import ChildCareTech.services.AlertWindowService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EditKidAllergiesController implements AccessorWindowController {
    @FXML
    private TableView<FoodDTO> allergiesTable;
    @FXML
    private TableView<FoodDTO> foodsTable;
    @FXML
    private TableColumn<FoodDTO, String> nameColumnA;
    @FXML
    private TableColumn<FoodDTO, String> nameColumnF;
    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button saveButton;

    private KidDTO kidDTO;
    private AccessorWindowService accessorWindowService;
    private AlertWindowService alertWindowService;
    private ObservableList<FoodDTO> allergies = FXCollections.observableArrayList();
    private ObservableList<FoodDTO> foods = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        initMenu();
        initTable();
        alertWindowService = new AlertWindowService();
    }

    @FXML
    public void saveButtonAction(ActionEvent event) {
        Set<FoodDTO> newAllergies = new HashSet<>();
        Set<FoodDTO> oldAllergies = kidDTO.getPerson().getAllergies();
        KidDTO newKid;
        PersonDTO newPerson;
        newAllergies.addAll(allergies);
        try {
            for(FoodDTO newAllergy : newAllergies) {
                if(!oldAllergies.contains(newAllergy))
                    Client.getSessionService().getSession().addAllergy(kidDTO.getPerson(), newAllergy);
            }
            for(FoodDTO oldAllergy : oldAllergies) {
                if(!newAllergies.contains(oldAllergy))
                    Client.getSessionService().getSession().removeAllergy(kidDTO.getPerson(), oldAllergy);
            }
            accessorWindowService.close();
            accessorWindowService.refreshTable();
        } catch (RemoteException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        } catch (AddFailedException ex) {
            alertWindowService.loadWindow(ex.getMessage());
            ex.printStackTrace();
        }
    }
    @FXML
    public void addButtonAction(ActionEvent event ) {
        FoodDTO selected = foodsTable.getSelectionModel().getSelectedItem();
        if(selected == null) return;
        allergies.add(selected);
        foods.remove(selected);
    }
    @FXML
    public void removeButtonAction(ActionEvent event ) {
        FoodDTO selected = allergiesTable.getSelectionModel().getSelectedItem();
        if(selected == null) return;
        foods.add(selected);
        allergies.remove(selected);
    }

    private void initMenu() {
        removeButton.setDisable(true);
        addButton.setDisable(true);

        allergiesTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                removeButton.setDisable(false);
                addButton.setDisable(true);
            }
            else {
                removeButton.setDisable(true);
                addButton.setDisable(true);
            }
        });
        foodsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
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
    private void initTable() {
        List<FoodDTO> tempFoods = new ArrayList<>();

        nameColumnA.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        nameColumnF.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));

        allergiesTable.setItems(allergies);
        foodsTable.setItems(foods);

        try {
            tempFoods = Client.getSessionService().getSession().getAllFoods();
        } catch(RemoteException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        foods.clear();
        allergies.clear();
        foods.addAll(tempFoods);

    }

    public void initAllergies(KidDTO kidDTO) {
        if(kidDTO == null)
            return;
        this.kidDTO = kidDTO;
        allergies.addAll(kidDTO.getPerson().getAllergies());
        foods.removeAll(allergies);
    }

    public void setAccessorWindowService(AccessorWindowService accessorWindowService) {
        this.accessorWindowService = accessorWindowService;
    }
}
