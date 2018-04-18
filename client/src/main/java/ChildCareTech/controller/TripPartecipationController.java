package ChildCareTech.controller;

import ChildCareTech.common.DTO.BusDTO;
import ChildCareTech.common.DTO.TripDTO;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.util.Collection;

public class TripPartecipationController {
    @FXML
    protected TableView<BusDTO> busesTable;

    public void initData(TripDTO tripDTO){
        refreshTable(tripDTO.getBuses());
    }

    private void refreshTable(Collection<BusDTO> buses){
        busesTable.getItems().clear();
        busesTable.getItems().addAll(buses);
    }
}
