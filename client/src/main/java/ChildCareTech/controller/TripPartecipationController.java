package ChildCareTech.controller;

import ChildCareTech.common.DTO.BusDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.services.SessionService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TripPartecipationController {
    @FXML
    protected TableView<BusDTO> busesTable;
    @FXML
    protected ComboBox<BusDTO> availableBusesComboBox;

    private TripDTO currentTripDTO;

    public void initData(TripDTO tripDTO){
        currentTripDTO = tripDTO;
        refreshBusesTable();
        refreshAvailableBusesComboBox();
    }

    @FXML
    protected void addBusButtonAction(ActionEvent event){
        BusDTO addedBus = availableBusesComboBox.getValue();
        currentTripDTO.getBuses().add(addedBus);

        try{
            SessionService.getSession().updateTrip(currentTripDTO);
        } catch(RemoteException | UpdateFailedException e){
            e.printStackTrace();
        }

        refreshBusesTable();
        refreshAvailableBusesComboBox();
    }

    private void refreshBusesTable(){
        busesTable.getItems().clear();
        busesTable.getItems().addAll(currentTripDTO.getBuses());
    }

    private void refreshAvailableBusesComboBox(){
        Collection<BusDTO> availableBuses = Collections.emptyList();
        try{
            availableBuses = SessionService.getSession().getAvailableBuses(currentTripDTO);
        } catch(RemoteException e){
            e.printStackTrace();
        }
        availableBusesComboBox.getItems().clear();
        availableBusesComboBox.getItems().addAll(availableBuses);
        availableBusesComboBox.getSelectionModel().clearSelection();
    }
}
