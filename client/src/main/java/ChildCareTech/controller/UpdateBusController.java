package ChildCareTech.controller;

import ChildCareTech.common.DTO.BusDTO;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.services.AccessorStageService;
import ChildCareTech.services.MainSceneManager;
import ChildCareTech.services.SessionService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.rmi.RemoteException;

public class UpdateBusController {
    private BusDTO oldBus;

    @FXML
    private Label alertLabel;

    @FXML
    private TextField licensePlateField;

    @FXML
    private TextField capacityField;


    public void initData(BusDTO busDTO){
        oldBus = busDTO;

        licensePlateField.setText(busDTO.getLicensePlate());
        capacityField.setText(Integer.toString(busDTO.getCapacity()));
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

    @FXML
    public void updateButtonAction(ActionEvent e){
        BusDTO updatedBusDTO;

        try{
            updatedBusDTO = new BusDTO(
                    oldBus.getId(),
                    licensePlateField.getText(),
                    null,
                    Integer.parseInt(capacityField.getText())
            );
            SessionService.getSession().updateBus(updatedBusDTO);
        } catch (NumberFormatException ex){
            alertLabel.setText("Salvataggio non riuscito: la capacità deve essere un numero");
            return;
        } catch(RemoteException | UpdateFailedException ex) {
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
}
