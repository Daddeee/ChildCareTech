package ChildCareTech.controller;

import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.common.exceptions.AddFailedException;
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

public class AddFoodController {
    @FXML
    protected TextField nameField;

    @FXML
    protected RadioButton isDrinkButton;

    @FXML
    protected Label alertLabel;

    @FXML
    protected void saveButtonAction(ActionEvent e){
        String name = nameField.getText();
        boolean isDrink = isDrinkButton.isSelected();
        FoodDTO foodDTO;

        try{
            foodDTO = new FoodDTO(0, name, isDrink, 0, null);
            SessionService.getSession().saveFood(foodDTO);
        } catch(RemoteException | AddFailedException ex) {
            alertLabel.setText("Salvataggio non riuscito:" + ex.getMessage());
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
