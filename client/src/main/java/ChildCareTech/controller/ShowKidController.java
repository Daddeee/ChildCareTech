package ChildCareTech.controller;

import ChildCareTech.services.ObservableDTOs.ObservableKid;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ShowKidController {
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label cfLabel;
    @FXML
    private Label bDateLabel;
    @FXML
    private Label sexLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label cfFirstTutorLabel;
    @FXML
    private Label cfSecondTutorLabel;
    @FXML
    private Label cfPediatristLabel;

    public ShowKidController() { }

    public void initData(ObservableKid observableKid) {
        firstNameLabel.setText(observableKid.getFirstName());
        lastNameLabel.setText(observableKid.getLastName());
        cfLabel.setText(observableKid.getFiscalCode());
        bDateLabel.setText(observableKid.getBirthDate().toString());
        sexLabel.setText(observableKid.getSex().toString());
        addressLabel.setText(observableKid.getAddress());
        cfFirstTutorLabel.setText(observableKid.getFirstTutorFC());
        cfSecondTutorLabel.setText(observableKid.getSecondTutorFC());
        cfPediatristLabel.setText(observableKid.getPediatristCF());
    }
}
