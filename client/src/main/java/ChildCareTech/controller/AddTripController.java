package ChildCareTech.controller;

import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.services.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.rmi.RemoteException;
import java.time.LocalDate;


public class AddTripController {
    @FXML
    private TextField metaField;
    @FXML
    private TextField noteField;
    @FXML
    private DatePicker depDateField;
    @FXML
    private DatePicker arrDateField;
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;
    @FXML
    private Label alertLabel;
    @FXML
    private VBox mainBox;

    @FXML
    public void addRouteButtonAction(ActionEvent e) throws IOException, NoSuchFieldException{
        Parent newRouteNode = new FXMLLoader(AccessorSceneManager.class.getResource("/view/templates/addRouteTemplate.fxml")).load();
        mainBox.getChildren().add(newRouteNode);
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
    public void saveButtonAction(ActionEvent e){
        String meta = metaField.getText();
        String note = noteField.getText();
        LocalDate depDate = depDateField.getValue();
        LocalDate arrDate = arrDateField.getValue();

        TripDTO tripDTO = new TripDTO(meta, note, depDate, arrDate, null, null);

        try {
            SessionService.getSession().saveTrip(tripDTO);
        } catch (RemoteException ex) {
            System.err.println("error remote");
            ex.printStackTrace();
        } catch (AddFailedException ex) {
            alertLabel.setText("Salvataggio non riuscito: " + ex.getMessage());
            return;
        }

        try {
            AccessorStageService.close();
            MainSceneManager.loadTrips();
        } catch (NoSuchFieldException ex) {
            System.err.println("Stage not initialized");
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
