package ChildCareTech.controller;

import ChildCareTech.services.AccessorWindowService;
import ChildCareTech.services.ObservableDTOs.ObservableAdult;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ShowAdultController implements AccessorWindowController{
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

    public void initData(ObservableAdult observableAdult) {
        firstNameLabel.setText(observableAdult.getFirstName());
        lastNameLabel.setText(observableAdult.getLastName());
        cfLabel.setText(observableAdult.getFiscalCode());
        bDateLabel.setText(observableAdult.getBirthDate().toString());
        sexLabel.setText(observableAdult.getSex().toString());
        addressLabel.setText(observableAdult.getAddress());
        phoneNumberLabel.setText(observableAdult.getPhoneNumber());
    }

    public void setAccessorWindowService(AccessorWindowService accessorWindowService) { }
}
