package ChildCareTech.controller;

import ChildCareTech.common.DTO.AdultDTO;
import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.services.AccessorWindowService;
import ChildCareTech.services.ObservableDTOs.ObservableKid;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import java.util.Set;

public class ShowKidController implements AccessorWindowController{
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
    @FXML
    private ListView allergiesList;
    @FXML
    private ListView contactsList;

    private ObservableList<String> allergies = FXCollections.observableArrayList();
    private ObservableList<String> contacts = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        allergiesList.setItems(allergies);
        contactsList.setItems(contacts);
    }

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
        initAllergiesList(observableKid.getPerson().getAllergies());
        initContactsList(observableKid.getContacts());
    }
    private void initAllergiesList(Set<FoodDTO> aller) {
        for(FoodDTO food : aller) {
            allergies.add(food.getName());
        }
    }
    private void initContactsList(Set<AdultDTO> cont) {
        for(AdultDTO adult : cont) {
            contacts.add(adult.getPerson().getFirstName()+" "+adult.getPerson().getLastName()+" "+adult.getPerson().getPhoneNumber());
        }
    }

    public void setAccessorWindowService(AccessorWindowService accessorWindowService) { }
}
