package ChildCareTech.controller;

import ChildCareTech.Client;
import ChildCareTech.common.DTO.*;
import ChildCareTech.common.Sex;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.services.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.rmi.RemoteException;
import java.util.List;

public class AddAdultController implements AccessorWindowController{

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
    protected TextField phoneField;
    @FXML
    protected RadioButton maleButton;
    @FXML
    protected RadioButton femaleButton;
    @FXML
    protected RadioButton noneSelecter;
    @FXML
    protected RadioButton staffSelecter;
    @FXML
    protected RadioButton supplierSelecter;
    @FXML
    protected RadioButton pediatristSelecter;
    @FXML
    protected Button cancelButton;
    @FXML
    protected Button saveButton;
    @FXML
    protected Label alertLabel;

    protected ToggleGroup sexGroup = new ToggleGroup();
    protected ToggleGroup roleGroup = new ToggleGroup();
    protected AccessorWindowService accessorWindowService;

    @FXML
    public void initialize() {
        maleButton.setToggleGroup(sexGroup);
        femaleButton.setToggleGroup(sexGroup);
        maleButton.fire();

        noneSelecter.setToggleGroup(roleGroup);
        staffSelecter.setToggleGroup(roleGroup);
        supplierSelecter.setToggleGroup(roleGroup);
        pediatristSelecter.setToggleGroup(roleGroup);
        noneSelecter.fire();
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
        person = new PersonDTO(0, fiscalCodeField.getText(), firstNameField.getText(), lastNameField.getText(), birthDatePicker.getValue(), sex, addressField.getText(), phoneField.getText(), null);
        if(roleGroup.getSelectedToggle().equals(noneSelecter)) {
            adult = new AdultDTO(0, person, null);
            try {
                Client.getSessionService().getSession().saveAdult(adult);
                accessorWindowService.close();
            } catch (RemoteException ex) {
                System.err.println("error remote");
                ex.printStackTrace();
            } catch (AddFailedException ex) {
                alertLabel.setText(ex.getMessage());
                ex.printStackTrace();
            }
        }
        if(roleGroup.getSelectedToggle().equals(staffSelecter)) {
            staff = new StaffDTO(0, person, null);
            try {
                Client.getSessionService() .getSession().saveStaff(staff);
                accessorWindowService.close();
            } catch (RemoteException ex) {
                System.err.println("error remote");
                ex.printStackTrace();
            } catch(AddFailedException ex) {
                alertLabel.setText(ex.getMessage());
                ex.printStackTrace();
            }
        }
        if(roleGroup.getSelectedToggle().equals(supplierSelecter)) {
            supplier = new SupplierDTO(0, person, null, null);
            try {
                Client.getSessionService().getSession().saveSupplier(supplier);
                accessorWindowService.close();
            } catch (RemoteException ex) {
                System.err.println("error remote");
                ex.printStackTrace();
            } catch(AddFailedException ex) {
                alertLabel.setText(ex.getMessage());
                ex.printStackTrace();
            }
        }
        if(roleGroup.getSelectedToggle().equals(pediatristSelecter)){
            pediatrist = new PediatristDTO(0, person, null, null);
            try {
                Client.getSessionService().getSession().savePediatrist(pediatrist);
                accessorWindowService.close();
            } catch (RemoteException ex) {
                System.err.println("error remote");
                ex.printStackTrace();
            } catch(AddFailedException ex) {
                alertLabel.setText(ex.getMessage());
                ex.printStackTrace();
            }
        }
        refreshAdultAnagraphics();
    }

    @FXML
    protected void cancelButtonAction(ActionEvent event) {
        accessorWindowService.close();
    }

    protected void refreshAdultAnagraphics() {
        List<NewAdultAnagraphicsController> list = ActiveControllersList.getAdultAnagraphicControllersList();
        for( NewAdultAnagraphicsController naac : list) {
            naac.refreshTable();
        }
    }

    @Override
    public void setAccessorWindowService(AccessorWindowService accessorWindowService) {
        this.accessorWindowService = accessorWindowService;
    }
}