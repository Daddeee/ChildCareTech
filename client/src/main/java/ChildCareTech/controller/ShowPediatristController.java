package ChildCareTech.controller;

import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.services.ObservableDTOs.ObservableKid;
import ChildCareTech.services.ObservableDTOs.ObservablePediatrist;
import ChildCareTech.services.SessionService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.Set;

public class ShowPediatristController extends ShowAdultController{
    @FXML
    private ListView patientsList;

    private ObservableList<String> observableKids = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        patientsList.setItems(observableKids);
    }

    public void initData(ObservablePediatrist observablePediatrist) {
        firstNameLabel.setText(observablePediatrist.getFirstName());
        lastNameLabel.setText(observablePediatrist.getLastName());
        cfLabel.setText(observablePediatrist.getFiscalCode());
        bDateLabel.setText(observablePediatrist.getBirthDate().toString());
        sexLabel.setText(observablePediatrist.getSex().toString());
        addressLabel.setText(observablePediatrist.getAddress());
        phoneNumberLabel.setText(observablePediatrist.getPhoneNumber());
        initPatientsList(observablePediatrist.getKids());
    }

    private void initPatientsList(Set<KidDTO> kids) {
        for(KidDTO kidDTO : kids) {
            ObservableKid observableKid = new ObservableKid(kidDTO);
            observableKids.add(observableKid.getFirstName()+" "+observableKid.getLastName()+" "+observableKid.getFiscalCode());
        }
    }
}
