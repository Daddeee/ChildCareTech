package ChildCareTech.controller;

import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.Sex;
import ChildCareTech.services.AccessorStageService;
import ChildCareTech.services.SessionService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.rmi.RemoteException;

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
    private TextField addressField;
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
    private PersonDTO person;
    private KidDTO kid;

    @FXML
    public void initialize() {
        maleButton.setToggleGroup(group);
        femaleButton.setToggleGroup(group);
        maleButton.fire();
    }

    @FXML
    public void saveButtonAction(ActionEvent event) {
        alertLabel.setText("");
        if (fiscalCodeField.getText().length() != 16 ||
                firstNameField.getText().equals("") ||
                lastNameField.getText().equals("") ||
                addressField.getText().equals("")) {
            alertLabel.setText("invalid input");
            return;
        }
        Sex sex;
        if (maleButton.isSelected())
            sex = Sex.MALE;
        else
            sex = Sex.FEMALE;
        person = new PersonDTO(firstNameField.getText(), lastNameField.getText(), fiscalCodeField.getText(), birthDatePicker.getValue(), sex, addressField.getText(), null);
        kid = new KidDTO(0, person, null, null, null, null);
        try {
            SessionService.getSession().saveKid(kid);
            AccessorStageService.close();
        } catch (RemoteException ex) {
            System.err.println("error closing stage");
            ex.printStackTrace();
        } catch(NoSuchFieldException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void cancelButtonAction(ActionEvent event) {
        try {
            AccessorStageService.close();
        } catch (NoSuchFieldException ex) {
            System.err.println("Stage not initialized");
            ex.printStackTrace();
        }

    }
}
