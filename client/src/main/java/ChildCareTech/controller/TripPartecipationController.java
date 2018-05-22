package ChildCareTech.controller;

import ChildCareTech.Client;
import ChildCareTech.common.DTO.BusDTO;
import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.DTO.TripPartecipationDTO;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.services.AccessorWindowService;
import ChildCareTech.utils.BusDTOWithResidualCapacity;
import ChildCareTech.utils.TripPartecipationData;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.rmi.RemoteException;
import java.util.*;

public class TripPartecipationController implements AccessorWindowController{
    @FXML
    protected TableView<BusDTOWithResidualCapacity> busesTable;
    @FXML
    protected ComboBox<BusDTO> availableBusesComboBox;
    @FXML
    protected TableView<TripPartecipationData> tripPartecipationTable;
    @FXML
    protected ComboBox<BusDTO> selectableBusesComboBox;
    @FXML
    protected ComboBox<KidDTO> selectableKidsComboBox;
    @FXML
    protected Button removeBusButton;
    @FXML
    protected Button removePartecipationButton;

    private TripDTO currentTripDTO;
    private AccessorWindowService accessorWindowService;

    public void initData(TripDTO tripDTO){
        currentTripDTO = tripDTO;
        refresh();
    }

    public void initialize(){
        initMenu();
    }

    @FXML
    protected void addTripPartecipationAction(ActionEvent event) {
        BusDTO selectedBus = selectableBusesComboBox.getValue();
        KidDTO selectedKid = selectableKidsComboBox.getValue();

        if(selectedBus == null || selectedKid == null) return;

        for (BusDTOWithResidualCapacity brc : busesTable.getItems()) {
            if (brc.getLicensePlate().equals(selectedBus.getLicensePlate()))
                if (brc.getResidualCapacity() <= 0) {
                    //gestione
                    return;
                }
        }

        TripPartecipationDTO tripPartecipationDTO = new TripPartecipationDTO(
                0,
                selectedKid.getPerson(),
                currentTripDTO,
                selectedBus
        );


        try{
            Client.getSessionService().getSession().saveTripPartecipation(tripPartecipationDTO);
        } catch(RemoteException | AddFailedException e){
            e.printStackTrace();
        }

        refresh();

    }
    @FXML
    protected void removeBusButtonAction(ActionEvent event) {
        BusDTOWithResidualCapacity selected = busesTable.getSelectionModel().getSelectedItem();
        if(selected == null) return;
        removeBus(selected.getBusDTO());

    }
    @FXML
    protected void removePartecipationButtonAction(ActionEvent event) {
        TripPartecipationData selected = tripPartecipationTable.getSelectionModel().getSelectedItem();
        if(selected == null) return;
        removePartecipation(selected.getTripPartecipationDTO());
    }

    @FXML
    protected void addBusButtonAction(ActionEvent event){
        BusDTO addedBus = availableBusesComboBox.getValue();

        if(addedBus == null) return;

        currentTripDTO.getBuses().add(addedBus);

        try{
            Client.getSessionService().getSession().saveTripBusRelation(currentTripDTO, addedBus);
        } catch(RemoteException | AddFailedException e){
            e.printStackTrace();
        }

        refresh();
    }

    private void refresh(){
        try{
            currentTripDTO = Client.getSessionService().getSession().getTrip(currentTripDTO.getId());
        } catch(RemoteException | NoSuchElementException e){
            e.printStackTrace();
        }

        refreshGUI();
    }

    private void refreshGUI(){
        refreshBusesTable();
        refreshAvailableBusesComboBox();
        refreshSelectableBusesComboBox();
        refreshSelectableKidComboBox();
        refreshTripPartecipationTable();
    }

    private void refreshBusesTable(){
        List<BusDTOWithResidualCapacity> elems = new ArrayList<>();

        for(BusDTO b : currentTripDTO.getBuses())
            elems.add(BusDTOWithResidualCapacity.buildFromBusDTO(b));

        for(TripPartecipationDTO t : currentTripDTO.getTripPartecipations()) {
            for (BusDTOWithResidualCapacity b : elems) {
                if (b.getLicensePlate().equals(t.getBus().getLicensePlate())) {
                    b.setResidualCapacity(b.getResidualCapacity() - 1);
                    break;
                }
            }
        }

        busesTable.getItems().clear();
        busesTable.getItems().addAll(elems);
    }

    private void refreshAvailableBusesComboBox(){
        Collection<BusDTO> availableBuses = Collections.emptyList();
        try{
            availableBuses = Client.getSessionService().getSession().getAvailableBuses(currentTripDTO);
        } catch(RemoteException e){
            e.printStackTrace();
        }
        availableBusesComboBox.getItems().clear();
        availableBusesComboBox.getItems().addAll(availableBuses);
        availableBusesComboBox.getSelectionModel().clearSelection();
    }

    private void refreshSelectableBusesComboBox(){
        selectableBusesComboBox.getItems().clear();
        selectableBusesComboBox.getItems().addAll(currentTripDTO.getBuses());
        selectableBusesComboBox.getSelectionModel().clearSelection();
    }
    private void refreshSelectableKidComboBox(){
        Collection<KidDTO> availableKids = Collections.emptyList();
        try{
            availableKids = Client.getSessionService().getSession().getAvailableKids(currentTripDTO);
        } catch(RemoteException e){
            e.printStackTrace();
        }
        selectableKidsComboBox.getItems().clear();
        selectableKidsComboBox.getItems().addAll(availableKids);
        selectableKidsComboBox.getSelectionModel().clearSelection();
    }
    private void refreshTripPartecipationTable(){
        List<TripPartecipationData> tripPartecipationElems = new ArrayList<>();
        for(TripPartecipationDTO t : currentTripDTO.getTripPartecipations())
            tripPartecipationElems.add(TripPartecipationData.buildFromDTO(t));

        tripPartecipationTable.getItems().clear();
        tripPartecipationTable.getItems().addAll(tripPartecipationElems);
    }
    public void setAccessorWindowService(AccessorWindowService accessorWindowService) {
        this.accessorWindowService = accessorWindowService;
    }
    private void removePartecipation(TripPartecipationDTO tripPartecipationDTO) {
        try {
            Client.getSessionService().getSession().removeTripPartecipation(tripPartecipationDTO);
        } catch (RemoteException e){
            e.printStackTrace();
        }
        refresh();
    }
    private void removeBus(BusDTO busDTO) {
        try {
            Client.getSessionService().getSession().removeTripBusRelation(currentTripDTO, busDTO);
        } catch (RemoteException e){
            e.printStackTrace();
        }
        refresh();
    }
    private void initMenu() {
        removeBusButton.setDisable(true);
        removePartecipationButton.setDisable(true);

        busesTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                removeBusButton.setDisable(false);
            }
            else {
                removeBusButton.setDisable(true);
            }
        });

        tripPartecipationTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                removePartecipationButton.setDisable(false);
            }
            else {
                removePartecipationButton.setDisable(true);
            }
        });
    }
}
