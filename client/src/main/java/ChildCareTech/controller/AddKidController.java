package ChildCareTech.controller;

import ChildCareTech.common.DTO.AdultDTO;
import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.PediatristDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.Sex;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.services.AccessorStageService;
import ChildCareTech.services.MainSceneManager;
import ChildCareTech.services.ObservableDTOs.ObservableAdult;
import ChildCareTech.services.ObservableDTOs.ObservablePediatrist;
import ChildCareTech.services.SessionService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class AddKidController {

    @FXML
    protected TextField firstNameField;
    @FXML
    protected TextField lastNameField;
    @FXML
    protected TextField fiscalCodeField;
    @FXML
    protected DatePicker birthDatePicker;
    @FXML
    protected TextField addressField;
    @FXML
    protected RadioButton maleButton;
    @FXML
    protected RadioButton femaleButton;
    @FXML
    protected ComboBox<ObservableAdult> firstTutorComboBox;
    @FXML
    protected ComboBox<ObservableAdult> secondTutorComboBox;
    @FXML
    protected ComboBox<ObservablePediatrist> pediatristComboBox;
    @FXML
    protected Button cancelButton;
    @FXML
    protected Button saveButton;
    @FXML
    protected Label alertLabel;

    protected ToggleGroup group = new ToggleGroup();
    protected PersonDTO person;
    protected KidDTO kid;
    protected ObservableList<ObservableAdult> adults = FXCollections.observableArrayList();
    protected ObservableList<ObservablePediatrist> pediatrists = FXCollections.observableArrayList();
    protected KidAnagraphicsController kidAnagraphicsController;

    @FXML
    public void initialize() {
        kidAnagraphicsController = MainSceneManager.getKidAnagController();
        initLists();
        initComboBoxes();
        firstTutorComboBox.setVisibleRowCount(10);
        secondTutorComboBox.setVisibleRowCount(10);
        pediatristComboBox.setVisibleRowCount(5);
        maleButton.setToggleGroup(group);
        femaleButton.setToggleGroup(group);
        maleButton.fire();
        firstTutorComboBox.setItems(adults);
        secondTutorComboBox.setItems(adults);
        pediatristComboBox.setItems(pediatrists);
    }

    @FXML
    public void saveButtonAction(ActionEvent event) {
        Sex sex;
        if (maleButton.isSelected())
            sex = Sex.MALE;
        else
            sex = Sex.FEMALE;
        person = new PersonDTO(fiscalCodeField.getText(), firstNameField.getText(), lastNameField.getText(), birthDatePicker.getValue(), sex, addressField.getText(), null);
        kid = new KidDTO(0, person, firstTutorComboBox.getValue().getDTO(), secondTutorComboBox.getValue().getDTO(), pediatristComboBox.getValue().getDTO(), null);
        try {
            SessionService.getSession().saveKid(kid);
            AccessorStageService.close();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        } catch(AddFailedException ex) {
            alertLabel.setText(ex.getMessage());
            ex.printStackTrace();
        } catch(NoSuchFieldException ex) {
            System.err.println("error closing stage");
            ex.printStackTrace();
        }
        kidAnagraphicsController.refreshTable();
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
    private void initComboBoxes() {
        StringConverter<ObservableAdult> tutorConverter = new StringConverter<ObservableAdult>() {

            @Override
            public String toString(ObservableAdult adult) {
                return adult.getPerson().getLastName()+
                        " "+adult.getPerson().getFirstName()+
                        " "+adult.getPerson().getFiscalCode();
            }

            @Override
            public ObservableAdult fromString(String string) {
                return null;
            }
        };
        StringConverter<ObservablePediatrist> pediatristConverter = new StringConverter<ObservablePediatrist>() {

            @Override
            public String toString(ObservablePediatrist pediatrist) {
                return pediatrist.getPerson().getLastName()+
                        " "+pediatrist.getPerson().getFirstName()+
                        " "+pediatrist.getPerson().getFiscalCode();
            }

            @Override
            public ObservablePediatrist fromString(String string) {
                return null;
            }
        };
        firstTutorComboBox.setConverter(tutorConverter);
        secondTutorComboBox.setConverter(tutorConverter);
        pediatristComboBox.setConverter(pediatristConverter);
    }
    private void initLists() {
        List<AdultDTO> adultDTOList = new ArrayList<>();
        List<PediatristDTO> pediatristDTOList = new ArrayList<>();
        adults.clear();
        pediatrists.clear();
        try {
            adultDTOList = SessionService.getSession().getAllAdults();
            pediatristDTOList =  SessionService.getSession().getAllPediatrists();
        } catch(RemoteException ex) {
            alertLabel.setText(ex.getMessage());
        }
        for(AdultDTO adult : adultDTOList) {
            adults.add(new ObservableAdult(adult));
        }
        for(PediatristDTO pediatrist : pediatristDTOList) {
            pediatrists.add(new ObservablePediatrist(pediatrist));
        }
    }
}
