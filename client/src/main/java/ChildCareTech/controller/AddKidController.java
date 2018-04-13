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
import ChildCareTech.services.ObservableDTOs.ObservablePersonInterface;
import ChildCareTech.services.SessionService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

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
    private ComboBox<ObservableAdult> firstTutorComboBox;
    @FXML
    private ComboBox<ObservableAdult> secondTutorComboBox;
    @FXML
    private ComboBox<ObservablePediatrist> pediatristComboBox;
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;
    @FXML
    private Label alertLabel;

    private ToggleGroup group = new ToggleGroup();
    private PersonDTO person;
    private KidDTO kid;
    private ObservableList<ObservableAdult> adults = FXCollections.observableArrayList();
    private ObservableList<ObservablePediatrist> pediatrists = FXCollections.observableArrayList();
    private KidAnagraphicController kidAnagraphicsController;

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
            System.err.println("error closing stage");
            ex.printStackTrace();
        } catch(AddFailedException ex) {
            alertLabel.setText(ex.getMessage());
            ex.printStackTrace();
        } catch(NoSuchFieldException ex) {
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
