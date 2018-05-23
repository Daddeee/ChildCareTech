package ChildCareTech.controller;

import ChildCareTech.common.DTO.BusDTO;
import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.DTO.RouteDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.services.AccessorWindowService;
import ChildCareTech.services.QRCodeReaderSingleton;
import ChildCareTech.utils.ReportTableData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;

public class TripPresenceRegistrationController implements AccessorWindowController, TableWindowControllerInterface {
    @FXML
    protected TableView<ReportTableData> reportTable;
    @FXML
    protected ComboBox<BusDTO> busComboBox;
    @FXML
    protected AnchorPane QRPane;
    @FXML
    protected TextArea logArea;
    @FXML
    protected ToggleButton QRStatus;

    protected ObservableList<ReportTableData> reports = FXCollections.observableArrayList();
    protected ObservableList<BusDTO> buses = FXCollections.observableArrayList();
    private AccessorWindowService accessorWindowService;
    private BusDTO selectedBus;
    private RouteDTO currentRoute;
    private TripDTO currentTrip;
    private EventDTO currentEvent;

    @FXML
    public void initialize() {
        reportTable.setItems(reports);
        busComboBox.setItems(buses);
    }
    @FXML
    public void QRStatusAction(ActionEvent event) {
         if(QRStatus.isSelected()){
             if(selectedBus == null) {
                 //gestione errore
                 return;
             }
             loadQRreader();
             QRStatus.setText("Turn OFF");
         }
    }
    @FXML
    public void busSelectionAction(ActionEvent event) {
        selectedBus = busComboBox.getValue();
    }

    public void setAccessorWindowService(AccessorWindowService accessorWindowService) {
        this.accessorWindowService = accessorWindowService;
    }
    public void initData(TripDTO tripDTO, RouteDTO routeDTO) {
        currentRoute = routeDTO;
        currentTrip = tripDTO;
        buses.clear();
        buses.addAll(tripDTO.getBuses());
    }
    public void refreshTable() {

    }
    private void loadQRreader() {
        Node node;
        QRPane.getChildren().clear();
        node = QRCodeReaderSingleton.getQRNode();
        QRPane.getChildren().add(node);
        anchorChild(QRPane, node);
    }
    public void anchorChild(AnchorPane anchorPane, Node node) {
        anchorPane.setBottomAnchor(node, 0.0);
        anchorPane.setLeftAnchor(node, 0.0);
        anchorPane.setTopAnchor(node, 0.0);
        anchorPane.setRightAnchor(node, 0.0);
    }
    public void notifyUpdate() { }
}
