package ChildCareTech.controller;

import ChildCareTech.Client;
import ChildCareTech.common.DTO.AdultDTO;
import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.Sex;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.services.ObservableDTOs.ObservableAdult;
import ChildCareTech.services.ObservableDTOs.ObservableKid;
import ChildCareTech.services.ObservableDTOs.ObservablePediatrist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.rmi.RemoteException;


public class EditKidController extends AddKidController {
    private int id = 0;
    private int personId = 0;

    @FXML
    @Override
    public void saveButtonAction(ActionEvent event) {
        Sex sex;
        if (maleButton.isSelected())
            sex = Sex.MALE;
        else
            sex = Sex.FEMALE;
        person = new PersonDTO(personId, fiscalCodeField.getText(), firstNameField.getText(), lastNameField.getText(), birthDatePicker.getValue(), sex, addressField.getText(), null, null);

        AdultDTO firstTutor = firstTutorComboBox.getValue().getDTO();
        if(firstTutor.getPerson().getFiscalCode().equals("")) firstTutor = null;

        AdultDTO secondTutor = secondTutorComboBox.getValue().getDTO();
        if(secondTutor.getPerson().getFiscalCode().equals("")) secondTutor = null;

        kid = new KidDTO(id, person, firstTutor, secondTutor, pediatristComboBox.getValue().getDTO(), null);
        try {
            Client.getSessionService().getSession().updateKid(kid);
            accessorWindowService.close();
        } catch(UpdateFailedException ex) {
            alertLabel.setText(ex.getMessage());
            ex.printStackTrace();
        } catch (RemoteException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        refreshKidAnagraphics();
    }
    public void initData(ObservableKid observableKid) {
        id = observableKid.getId();
        personId = observableKid.getPersonId();
        firstNameField.setText(observableKid.getFirstName());
        lastNameField.setText(observableKid.getLastName());
        fiscalCodeField.setText(observableKid.getFiscalCode());
        birthDatePicker.setValue(observableKid.getBirthDate());
        addressField.setText(observableKid.getAddress());
        if(observableKid.getSex() == Sex.MALE)
            maleButton.fire();
        else
            femaleButton.fire();
        if(observableKid.getFirstTutor() == null)
            firstTutorComboBox.getSelectionModel().select(new ObservableAdult(nullAdult));
        else
            firstTutorComboBox.getSelectionModel().select(new ObservableAdult(observableKid.getFirstTutor()));

        if(observableKid.getSecondTutor() == null)
            secondTutorComboBox.getSelectionModel().select(new ObservableAdult(nullAdult));
        else
            secondTutorComboBox.getSelectionModel().select(new ObservableAdult(observableKid.getSecondTutor()));
        pediatristComboBox.getSelectionModel().select(new ObservablePediatrist(observableKid.getPediatrist()));
    }
}
