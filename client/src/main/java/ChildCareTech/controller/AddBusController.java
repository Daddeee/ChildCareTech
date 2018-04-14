package ChildCareTech.controller;

import ChildCareTech.common.DTO.BusDTO;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.services.AccessorStageService;
import ChildCareTech.services.MainSceneManager;
import ChildCareTech.services.SessionService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.rmi.RemoteException;

public class AddBusController {
    @FXML
    private TextField licensePlateField;

    @FXML
    private TextField capacityField;

    @FXML
    private Label alertLabel;

    @FXML
    protected void saveButtonAction(ActionEvent e){
        String licensePlate = licensePlateField.getText();
        int capacity;
        BusDTO busDTO;

        try{
            capacity = Integer.parseInt(capacityField.getText());
            busDTO = new BusDTO(0, licensePlate, null, null, capacity);
            SessionService.getSession().saveBus(busDTO);
        } catch (NumberFormatException ex){
            alertLabel.setText("Salvataggio non riuscito: la capacità deve essere un numero");
            return;
        } catch(RemoteException | AddFailedException ex) {
            alertLabel.setText("Salvataggio non riuscito:" + ex.getMessage());
            return;
        }


        try {
            AccessorStageService.close();
            MainSceneManager.loadBuses();
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
