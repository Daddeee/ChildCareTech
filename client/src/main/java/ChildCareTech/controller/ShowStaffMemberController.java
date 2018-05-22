package ChildCareTech.controller;

import ChildCareTech.services.AccessorWindowService;
import ChildCareTech.services.ObservableDTOs.ObservableStaff;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ShowStaffMemberController extends ShowAdultController{
    @FXML
    protected Label firstNameLabel;
    @FXML
    protected Label lastNameLabel;
    @FXML
    protected Label cfLabel;
    @FXML
    protected Label bDateLabel;
    @FXML
    protected Label sexLabel;
    @FXML
    protected Label addressLabel;
    @FXML
    protected Label phoneNumberLabel;

    public void initData(ObservableStaff observableStaff) {
        firstNameLabel.setText(observableStaff.getFirstName());
        lastNameLabel.setText(observableStaff.getLastName());
        cfLabel.setText(observableStaff.getFiscalCode());
        bDateLabel.setText(observableStaff.getBirthDate().toString());
        sexLabel.setText(observableStaff.getSex().toString());
        addressLabel.setText(observableStaff.getAddress());
        phoneNumberLabel.setText(observableStaff.getPhoneNumber());
    }

    public void setAccessorWindowService(AccessorWindowService accessorWindowService) { }
}
