package ChildCareTech.controller;

import ChildCareTech.common.DTO.BusDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ShowBusController {
    @FXML
    private Label licensePlateLabel;
    @FXML
    private Label capacityLabel;

    public void initData(BusDTO busDTO){
        licensePlateLabel.setText(busDTO.getLicensePlate());
        capacityLabel.setText(Integer.toString(busDTO.getCapacity()));
    }
}
