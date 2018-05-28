package ChildCareTech.controller;

import ChildCareTech.Client;
import ChildCareTech.common.DTO.BusDTO;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.services.AccessorWindowService;
import ChildCareTech.services.ActiveControllersList;
import ChildCareTech.services.AlertWindowService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.WindowEvent;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;


public class NewBusController implements AccessorWindowController, TableWindowControllerInterface {
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
    private int oldId;
    private AlertWindowService alertWindowService;

    @FXML
    public void initialize() {
        alertWindowService = new AlertWindowService();
        busesTable.setItems(items);
        initMenu();
        refreshTable();
        initButtonsToolTips();
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
                    Client.getSessionService().getSession().updateBus(busDTO);
                }
                else {
                    busDTO = new BusDTO(0, licensePlate, null, null, sitsNumber);
                    Client.getSessionService().getSession().saveBus(busDTO);
                }
                fireAction();
                refreshTable();
                sitsSelection.getSelectionModel().clearSelection();
                plateText.clear();
            } catch (NumberFormatException ex){
                alertWindowService.loadWindow(ex.getMessage());
                ex.printStackTrace();
            } catch(RemoteException | AddFailedException ex) {
                alertWindowService.loadWindow(ex.getMessage());
                ex.printStackTrace();
            } catch (UpdateFailedException ex) {
                alertWindowService.loadWindow(ex.getMessage());
                ex.printStackTrace();
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
            Client.getSessionService().getSession().removeBus((BusDTO)busesTable.getSelectionModel().getSelectedItem());
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
        this.accessorWindowService.setOnCloseAction(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                cleanInstance();
            }
        });
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

    public void refreshTable(){
        List<BusDTO> busesDTOList = new ArrayList<>();

        try {
            busesDTOList = Client.getSessionService().getSession().getAllBuses();
        } catch(RemoteException e){
            e.printStackTrace();
        }

        items.clear();
        items.addAll(busesDTOList);
        busesTable.setItems(items);
    }
    public void notifyUpdate() { }
    public void cleanInstance() {
        ActiveControllersList.removeBusController(this);
    }
    private void initButtonsToolTips() {
        addButton.setTooltip(new Tooltip("Registrazione nuovo bus"));
        deleteButton.setTooltip(new Tooltip("Rimozione bus"));
        editButton.setTooltip(new Tooltip("Modifica bus"));
        detailsButton.setTooltip(new Tooltip("Mostra dettagli del bus"));
        vButton.setTooltip(new Tooltip("Conferma dati"));
        xButton.setTooltip(new Tooltip("Annulla inserimento"));
    }

}
