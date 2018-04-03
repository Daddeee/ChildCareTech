package ChildCareTech.controller;

import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.network.DTO.KidDTOImpl;
import ChildCareTech.network.DTO.PersonDTOImpl;
import ChildCareTech.services.AccessorStageService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AddKidController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField fiscalCodeField;
    @FXML
    private DatePicker birthDatePicker;
    @FXML
    private RadioButton maleButton;
    @FXML
    private RadioButton femaleButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;
    @FXML
    private Label alertLabel;

    private ToggleGroup group = new ToggleGroup();


    @FXML
    public void saveButtonAction(ActionEvent event) {
        if(fiscalCodeField.getText().length()!=16 ||
                firstNameField.getText().equals("") ||
                lastNameField.getText().equals("") ||
                (!maleButton.isArmed() && !femaleButton.isArmed())) {
            alertLabel.setText("invalid input");
        }

    }
    @FXML
    public void cancelButtonAction(ActionEvent event) {
        try {
            AccessorStageService.close();
        } catch(NoSuchFieldException ex) {
            System.err.println("Stage not initialized");
            ex.printStackTrace();
        }

    }
}
