package ChildCareTech.controller;

import ChildCareTech.common.DTO.BusDTO;
import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.DTO.TripPartecipationDTO;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.services.AccessorWindowService;
import ChildCareTech.services.SessionService;
import ChildCareTech.utils.BusDTOWithResidualCapacity;
import ChildCareTech.utils.TripPartecipationData;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

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
    protected Label alertLabel;

    private TripDTO currentTripDTO;
    private AccessorWindowService accessorWindowService;

    public void initData(TripDTO tripDTO){
        currentTripDTO = tripDTO;
        refresh();
    }

    public void initialize(){
        tripPartecipationTable.setRowFactory(tripPartecipationDataTableView -> {
            final TableRow<TripPartecipationData> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();

            final MenuItem removeTripPartecipation = new MenuItem("Elimina");
            removeTripPartecipation.setOnAction(event -> {
                try {
                    SessionService.getSession().removeTripPartecipation(row.getItem().getTripPartecipationDTO());
                } catch (RemoteException e){
                    e.printStackTrace();
                }
                refresh();
            });

            contextMenu.getItems().add(removeTripPartecipation);

            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                            .then((ContextMenu) null)
                            .otherwise(contextMenu)
            );

            return row;
        });

        busesTable.setRowFactory(busesTableView -> {
            final TableRow<BusDTOWithResidualCapacity> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();

            final MenuItem removeTripPartecipation = new MenuItem("Elimina");
            removeTripPartecipation.setOnAction(event -> {
                try {
                    SessionService.getSession().removeTripBusRelation(currentTripDTO, row.getItem().getBusDTO());
                } catch (RemoteException e){
                    e.printStackTrace();
                }
                refresh();
            });

            contextMenu.getItems().add(removeTripPartecipation);

            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                            .then((ContextMenu) null)
                            .otherwise(contextMenu)
            );

            return row;
        });
    }

    @FXML
    protected void addTripPartecipationAction(ActionEvent event) {
        BusDTO selectedBus = selectableBusesComboBox.getValue();
        KidDTO selectedKid = selectableKidsComboBox.getValue();

        for (BusDTOWithResidualCapacity brc : busesTable.getItems()) {
            if (brc.getLicensePlate().equals(selectedBus.getLicensePlate()))
                if (brc.getResidualCapacity() <= 0) {
                    alertLabel.setText("Il bus è già pieno.");
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
            SessionService.getSession().saveTripPartecipation(tripPartecipationDTO);
        } catch(RemoteException | AddFailedException e){
            e.printStackTrace();
        }

        refresh();

    }

    @FXML
    protected void addBusButtonAction(ActionEvent event){
        BusDTO addedBus = availableBusesComboBox.getValue();
        currentTripDTO.getBuses().add(addedBus);

        try{
            SessionService.getSession().saveTripBusRelation(currentTripDTO, addedBus);
        } catch(RemoteException | AddFailedException e){
            e.printStackTrace();
        }

        refresh();
    }

    private void refresh(){
        try{
            currentTripDTO = SessionService.getSession().getTrip(currentTripDTO.getId());
        } catch(RemoteException | NoSuchElementException e){
            e.printStackTrace();
        }

        refreshGUI();
    }

    private void refreshGUI(){
        alertLabel.setText("");
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
            availableBuses = SessionService.getSession().getAvailableBuses(currentTripDTO);
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
            availableKids = SessionService.getSession().getAvailableKids(currentTripDTO);
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
}
