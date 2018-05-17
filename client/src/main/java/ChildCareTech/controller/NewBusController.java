package ChildCareTech.controller;

import ChildCareTech.common.DTO.BusDTO;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.services.AccessorWindowService;
import ChildCareTech.services.SessionService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;


public class NewBusController implements AccessorWindowController {
    @FXML
    private TableView<BusDTO> busesTable;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button detailsButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button vButton;
    @FXML
    private Button xButton;
    @FXML
    private HBox editZone;
    @FXML
    private TextField plateText;
    @FXML
    private ComboBox sitsSelection;
    @FXML
    private HBox actionZone;
    @FXML
    private TableColumn<BusDTO, String> licensePlateColumn;
    @FXML
    private TableColumn<BusDTO, Integer> capacityColumn;

    private ObservableList<BusDTO> items = FXCollections.observableArrayList();
    private AccessorWindowService accessorWindowService;
    private ObservableList<Integer> sits = FXCollections.observableArrayList();
    private boolean editFlag = false;
    int oldId;

    @FXML
    public void initialize() {
        busesTable.setItems(items);
        initMenu();
        refreshTable();
    }

    @FXML
    private void addButtonAction(ActionEvent event) {
        fireEdit();
    }
    @FXML
    private void vButtonAction(ActionEvent event) {
        String licensePlate = plateText.getText();
        Integer sitsNumber = (Integer)sitsSelection.getSelectionModel().getSelectedItem();
        if(sitsNumber != null || !plateText.getText().equals("")) {
            BusDTO busDTO;
            try{
                if(editFlag) {
                    busDTO = new BusDTO(oldId, licensePlate, null, null, sitsNumber);
                    SessionService.getSession().updateBus(busDTO);
                }
                else {
                    busDTO = new BusDTO(0, licensePlate, null, null, sitsNumber);
                    SessionService.getSession().saveBus(busDTO);
                }
                fireAction();
                refreshTable();
                sitsSelection.getSelectionModel().clearSelection();
                plateText.clear();
            } catch (NumberFormatException ex){
                System.out.println(ex.getMessage());
            } catch(RemoteException | AddFailedException ex) {
                System.out.println(ex.getMessage());
            } catch (UpdateFailedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    @FXML
    private void xButtonAction(ActionEvent event) {
        fireAction();
        sitsSelection.getSelectionModel().clearSelection();
        plateText.clear();
    }
    @FXML
    private void deleteButtonAction(ActionEvent event) {
        try {
            SessionService.getSession().removeBus((BusDTO)busesTable.getSelectionModel().getSelectedItem());
        } catch (RemoteException ex) {
            System.err.println("error remote");
            ex.printStackTrace();
        }
        refreshTable();
    }
    @FXML
    private void editButtonAction(ActionEvent event) {
        editFlag = true;
        BusDTO bus = busesTable.getSelectionModel().getSelectedItem();
        fireEdit();
        plateText.setText(bus.getLicensePlate());
        sitsSelection.setValue(bus.getCapacity());
        oldId = bus.getId();
    }
    @FXML
    private void detailsButtonAction(ActionEvent event) {

    }

    public void setAccessorWindowService(AccessorWindowService accessorWindowService) {
        this.accessorWindowService = accessorWindowService;
    }

    public void initMenu() {
        editZone.setDisable(true);
        editButton.setDisable(true);
        detailsButton.setDisable(true);
        deleteButton.setDisable(true);

        for(int i = 1; i<=80; i++)
            sits.add(i);
        sitsSelection.setItems(sits);
        sitsSelection.setVisibleRowCount(10);

        busesTable.setMouseTransparent(false);

        busesTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                editButton.setDisable(false);
                detailsButton.setDisable(false);
                deleteButton.setDisable(false);
            }
            else {
                editButton.setDisable(true);
                detailsButton.setDisable(true);
                deleteButton.setDisable(true);
            }
        });
    }
    private void fireAction() {
        editZone.setDisable(true);
        actionZone.setDisable(false);
        busesTable.setDisable(false);
    }
    private void fireEdit() {
        editZone.setDisable(false);
        actionZone.setDisable(true);
        busesTable.setDisable(true);
    }

    private void refreshTable(){
        List<BusDTO> busesDTOList = new ArrayList<>();

        try {
            busesDTOList = SessionService.getSession().getAllBuses();
        } catch(RemoteException e){
            e.printStackTrace();
        }

        items.clear();
        items.addAll(busesDTOList);
        busesTable.setItems(items);
    }
}
