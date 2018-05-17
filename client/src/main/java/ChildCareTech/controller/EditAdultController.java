package ChildCareTech.controller;

import ChildCareTech.common.DTO.*;
import ChildCareTech.common.Sex;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.services.ObservableDTOs.ObservablePersonInterface;
import ChildCareTech.services.SessionService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.rmi.RemoteException;

public class EditAdultController extends AddAdultController {

    private int id = 0;
    private int personId = 0;
    private int prevRole = -1;

    @FXML
    public void initialize() {
        maleButton.setToggleGroup(sexGroup);
        femaleButton.setToggleGroup(sexGroup);
        maleButton.fire();

        noneSelecter.setToggleGroup(roleGroup);
        staffSelecter.setToggleGroup(roleGroup);
        supplierSelecter.setToggleGroup(roleGroup);
        pediatristSelecter.setToggleGroup(roleGroup);

        noneSelecter.setDisable(true);
        staffSelecter.setDisable(true);
        supplierSelecter.setDisable(true);
        pediatristSelecter.setDisable(true);
    }

    public void initData(ObservablePersonInterface observablePersonInterface) {
        firstNameField.setText(observablePersonInterface.getFirstName());
        lastNameField.setText(observablePersonInterface.getLastName());
        fiscalCodeField.setText(observablePersonInterface.getFiscalCode());
        birthDatePicker.setValue(observablePersonInterface.getBirthDate());
        addressField.setText(observablePersonInterface.getAddress());
        phoneField.setText(observablePersonInterface.getPhoneNumber());
        id = observablePersonInterface.getId();
        personId = observablePersonInterface.getPersonId();
        if(observablePersonInterface.getSex().equals(Sex.MALE))
            maleButton.fire();
        else
            femaleButton.fire();
        switch(observablePersonInterface.getRole()) {
            case 0: roleGroup.selectToggle(noneSelecter); prevRole = 0; break;
            case 1: roleGroup.selectToggle(pediatristSelecter); prevRole = 1; break;
            case 2: roleGroup.selectToggle(supplierSelecter); prevRole = 2; break;
            case 3: roleGroup.selectToggle(staffSelecter); prevRole = 3; break;
            default: System.err.println("fatal reading adult role"); break;
        }
    }

    @FXML
    protected void saveButtonAction(ActionEvent event) {
        PersonDTO person;
        AdultDTO adult;
        SupplierDTO supplier;
        PediatristDTO pediatrist;
        StaffDTO staff;

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
        person = new PersonDTO(personId, fiscalCodeField.getText(), firstNameField.getText(), lastNameField.getText(), birthDatePicker.getValue(), sex, addressField.getText(), phoneField.getText(), null);
        //if(roleGroup.getSelectedToggle().equals(noneSelecter))
        if(prevRole == 0) {
            adult = new AdultDTO(id, person, null);
            try {
                SessionService.getSession().updateAdult(adult);
                accessorWindowService.close();
            } catch (RemoteException ex) {
                System.err.println("error remote");
                ex.printStackTrace();
            } catch (UpdateFailedException ex) {
                alertLabel.setText(ex.getMessage());
                ex.printStackTrace();
            }
        }
        //if(roleGroup.getSelectedToggle().equals(staffSelecter))
        if(prevRole == 3) {
            staff = new StaffDTO(id, person, null);
            try {
                SessionService.getSession().updateStaffMember(staff);
                accessorWindowService.close();
            } catch (RemoteException ex) {
                System.err.println("error remote");
                ex.printStackTrace();
            } catch(UpdateFailedException ex) {
                alertLabel.setText(ex.getMessage());
                ex.printStackTrace();
            }
        }
        //if(roleGroup.getSelectedToggle().equals(supplierSelecter))
        if(prevRole == 2) {
            supplier = new SupplierDTO(id, person, null, null);
            try {
                SessionService.getSession().updateSupplier(supplier);
                accessorWindowService.close();
            } catch (RemoteException ex) {
                System.err.println("error remote");
                ex.printStackTrace();
            } catch(UpdateFailedException ex) {
                alertLabel.setText(ex.getMessage());
                ex.printStackTrace();
            }
        }
        //if(roleGroup.getSelectedToggle().equals(pediatristSelecter))
        if(prevRole == 1) {
            pediatrist = new PediatristDTO(id, person, null, null);
            try {
                SessionService.getSession().updatePediatrist(pediatrist);
                accessorWindowService.close();
            } catch (RemoteException ex) {
                System.err.println("error remote");
                ex.printStackTrace();
            } catch(UpdateFailedException ex) {
                alertLabel.setText(ex.getMessage());
                ex.printStackTrace();
            }
        }
        refreshAdultAnagraphics();
    }
}
