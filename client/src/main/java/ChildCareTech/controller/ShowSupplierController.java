package ChildCareTech.controller;

import ChildCareTech.common.DTO.SupplyDTO;
import ChildCareTech.services.ObservableDTOs.ObservableSupplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.Set;

public class ShowSupplierController extends ShowAdultController{
    @FXML
    private ListView ordersList;

    private ObservableList<String> orders = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        ordersList.setItems(orders);
    }

    public void initData(ObservableSupplier observableSupplier) {
        firstNameLabel.setText(observableSupplier.getFirstName());
        lastNameLabel.setText(observableSupplier.getLastName());
        cfLabel.setText(observableSupplier.getFiscalCode());
        bDateLabel.setText(observableSupplier.getBirthDate().toString());
        sexLabel.setText(observableSupplier.getSex().toString());
        addressLabel.setText(observableSupplier.getAddress());
        phoneNumberLabel.setText(observableSupplier.getPhoneNumber());
        initSuppliesList(observableSupplier.getSupplies());
    }

    private void initSuppliesList(Set<SupplyDTO> supplies) {
        for(SupplyDTO supplyDTO : supplies) {
            String supplyText = supplyDTO.getFood()+" "+supplyDTO.getQuantity()+" "+supplyDTO.getDate();
            orders.add(supplyText);
        }
    }
}
