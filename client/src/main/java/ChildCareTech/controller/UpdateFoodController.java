package ChildCareTech.controller;

import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.services.AccessorStageService;
import ChildCareTech.services.MainSceneManager;
import ChildCareTech.services.SessionService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.rmi.RemoteException;

public class UpdateFoodController {
    @FXML
    protected TextField nameField;

    @FXML
    protected RadioButton isDrinkButton;

    @FXML
    protected Label alertLabel;

    private FoodDTO oldDTO;

    public void initData(FoodDTO foodDTO){
        this.oldDTO = foodDTO;

        nameField.setText(oldDTO.getName());
        isDrinkButton.setSelected(oldDTO.isDrink());
    }

    @FXML
    protected void updateButtonAction(ActionEvent e){
        oldDTO.setName(nameField.getText());
        oldDTO.setDrink(isDrinkButton.isSelected());

        try{
            SessionService.getSession().updateFood(oldDTO);
        } catch(RemoteException | UpdateFailedException ex) {
            alertLabel.setText("Aggiornamento non riuscito:" + ex.getMessage());
            return;
        }


        try {
            AccessorStageService.close();
            MainSceneManager.loadFoods();
        } catch (NoSuchFieldException ex) {
            System.err.println("Stage not initialized");
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    public void cancelButtonAction(ActionEvent e){
        try {
            AccessorStageService.close();
        } catch (NoSuchFieldException ex) {
            System.err.println("Stage not initialized");
            ex.printStackTrace();
        }
    }
}
