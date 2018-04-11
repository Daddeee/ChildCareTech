package ChildCareTech.controller;

import ChildCareTech.common.DTO.AdultDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.Sex;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.services.AccessorSceneManager;
import ChildCareTech.services.AccessorStageService;
import ChildCareTech.services.MainSceneManager;
import ChildCareTech.services.SessionService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.rmi.RemoteException;

public class AddAdultController {

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
    private TextField phoneField;
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
    private AdultDTO adult;
    private AdultAnagraphicsController adultAnagController;

    @FXML
    public void initialize() {
        adultAnagController = MainSceneManager.getAdultAnagController();
        maleButton.setToggleGroup(group);
        femaleButton.setToggleGroup(group);
        maleButton.arm();
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
        if (maleButton.isArmed())
            sex = Sex.MALE;
        else
            sex = Sex.FEMALE;
        person = new PersonDTO(firstNameField.getText(), lastNameField.getText(), fiscalCodeField.getText(), birthDatePicker.getValue(), sex, addressField.getText(), phoneField.getText());
        adult = new AdultDTO(person, null);
        try {
            SessionService.getSession().saveAdult(adult);
        } catch (RemoteException ex) {
            System.err.println("error remote");
            ex.printStackTrace();
        } catch(AddFailedException ex) {
            ex.printStackTrace();
        }
        adultAnagController.refreshTable();
        //AccessorStageService.close();
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
