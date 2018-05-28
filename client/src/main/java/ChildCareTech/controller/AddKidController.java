package ChildCareTech.controller;

import ChildCareTech.Client;
import ChildCareTech.common.DTO.AdultDTO;
import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.PediatristDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.Sex;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.services.*;
import ChildCareTech.services.ObservableDTOs.ObservableAdult;
import ChildCareTech.services.ObservableDTOs.ObservablePediatrist;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class AddKidController implements AccessorWindowController{

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

    protected ToggleGroup group = new ToggleGroup();
    protected PersonDTO person;
    protected KidDTO kid;
    protected ObservableList<ObservableAdult> adults = FXCollections.observableArrayList();
    protected ObservableList<ObservablePediatrist> pediatrists = FXCollections.observableArrayList();
    protected AccessorWindowService accessorWindowService;
    protected AlertWindowService alertWindowService;

    protected final AdultDTO nullAdult = new AdultDTO(
            0,
            new PersonDTO(
                    0,
                    "",
                    "-",
                    "",
                    null,
                    null,
                    "",
                    "",
                    null
            ),
            null
    );

    @FXML
    public void initialize() {
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
        alertWindowService = new AlertWindowService();
    }

    @FXML
    public void saveButtonAction(ActionEvent event) {
        if(fiscalCodeField.getText().equals("") ||
                firstNameField.getText().equals("") ||
                lastNameField.getText().equals("") ||
                addressField.getText().equals("") ||
                pediatristComboBox.getValue() == null) {
            alertWindowService.loadWindow("Non tutti i campi obbligatori sono stati compilati.");
            return;
        }
        if(fiscalCodeField.getText().length() != 16) {
            alertWindowService.loadWindow("Codice fiscale non valido.");
            return;
        }
        Sex sex;
        if (maleButton.isSelected())
            sex = Sex.MALE;
        else
            sex = Sex.FEMALE;
        person = new PersonDTO(0, fiscalCodeField.getText(), firstNameField.getText(), lastNameField.getText(), birthDatePicker.getValue(), sex, addressField.getText(), null, null);

        AdultDTO firstTutor = null;
        if(firstTutorComboBox.getValue() != null) {
            firstTutor = firstTutorComboBox.getValue().getDTO();
            if (firstTutor.getPerson().getFiscalCode().equals(""))
                firstTutor = null;
        }

        AdultDTO secondTutor = null;
        if(secondTutorComboBox.getValue() != null) {
            secondTutor = secondTutorComboBox.getValue().getDTO();
            if (secondTutor.getPerson().getFiscalCode().equals(""))
                secondTutor = null;
        }

        kid = new KidDTO(0, person, firstTutor, secondTutor, pediatristComboBox.getValue().getDTO(), null);
        try {
            Client.getSessionService().getSession().saveKid(kid);
            accessorWindowService.close();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        } catch(AddFailedException ex) {
            alertWindowService.loadWindow(ex.getMessage());
            ex.printStackTrace();
        }
        refreshKidAnagraphics();
    }

    @FXML
    public void cancelButtonAction(ActionEvent event) {
        accessorWindowService.close();

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
            adultDTOList = Client.getSessionService().getSession().getAllAdults();
            adultDTOList.add(nullAdult);

            pediatristDTOList =  Client.getSessionService().getSession().getAllPediatrists();
        } catch(RemoteException ex) {
            alertWindowService.loadWindow(ex.getMessage());
        }
        for(AdultDTO adult : adultDTOList) {
            adults.add(new ObservableAdult(adult));
        }
        for(PediatristDTO pediatrist : pediatristDTOList) {
            pediatrists.add(new ObservablePediatrist(pediatrist));
        }
    }


    protected void refreshKidAnagraphics() {
        List<NewKidAnagraphicsController> list = ActiveControllersList.getKidAnagraphicControllersList();
        for( NewKidAnagraphicsController nkac : list) {
            nkac.refreshTable();
        }
    }

    @Override
    public void setAccessorWindowService(AccessorWindowService accessorWindowService) {
        this.accessorWindowService = accessorWindowService;
    }
}
