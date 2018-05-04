package ChildCareTech.controller;

import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.services.AccessorSceneManager;
import ChildCareTech.services.AccessorStageService;
import ChildCareTech.services.SessionService;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Collections;

public class AllergiesController {
    @FXML
    protected ComboBox<FoodDTO> availableAllergies;
    @FXML
    protected TableView<FoodDTO> foodsTable;

    private PersonDTO currentPersonDTO;

    public void initialize(){
        foodsTable.setRowFactory(tripDTOTableView -> {
            final TableRow<FoodDTO> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();

            final MenuItem deleteAllergy = new MenuItem("Elimina");
            deleteAllergy.setOnAction(event -> {
                contextMenu.hide();
                try {
                    SessionService.getSession().removeAllergy(currentPersonDTO, row.getItem());
                } catch (RemoteException ex) {
                    System.err.println("error remote");
                    ex.printStackTrace();
                }
                refresh();
            });
            contextMenu.getItems().add(deleteAllergy);

            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                            .then((ContextMenu) null)
                            .otherwise(contextMenu)
            );

            return row;
        });
    }

    public void initData(PersonDTO personDTO){
        currentPersonDTO = personDTO;

        refresh();
    }

    @FXML
    protected void addAllergyButtonAction(ActionEvent e){
        try{
            FoodDTO foodDTO = availableAllergies.getValue();
            SessionService.getSession().addAllergy(currentPersonDTO, foodDTO);

            refresh();
        } catch(Exception ex){
            ex.printStackTrace();
        }

        refresh();
    }

    private void refresh() {
        try{
            currentPersonDTO = SessionService.getSession().getPerson(currentPersonDTO.getFiscalCode());
        } catch (RemoteException e){
            e.printStackTrace();
        }
        refreshTable();
        refreshAvailableAllergies();
    }

    private void refreshTable(){
        foodsTable.getItems().clear();
        foodsTable.getItems().addAll(currentPersonDTO.getAllergies());
    }

    private void refreshAvailableAllergies(){
        Collection<FoodDTO> availableFoods = Collections.emptyList();
        try{
            availableFoods = SessionService.getSession().getAvailableFoods(currentPersonDTO);
        } catch(RemoteException e){
            e.printStackTrace();
        }
        availableAllergies.getItems().clear();
        availableAllergies.getItems().addAll(availableFoods);
        availableAllergies.getSelectionModel().clearSelection();
    }
}
