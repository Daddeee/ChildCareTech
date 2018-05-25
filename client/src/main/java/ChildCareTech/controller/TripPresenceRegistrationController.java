package ChildCareTech.controller;

import ChildCareTech.Client;
import ChildCareTech.common.DTO.*;
import ChildCareTech.common.EventStatus;
import ChildCareTech.common.exceptions.CameraBusyException;
import ChildCareTech.services.AccessorWindowService;
import ChildCareTech.utils.NewWebcamQRCodeReader;
import ChildCareTech.utils.ReportTableData;
import com.github.sarxos.webcam.WebcamPanel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.WindowEvent;

import java.rmi.RemoteException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TripPresenceRegistrationController implements AccessorWindowController, TableWindowControllerInterface, CheckPointControllerInterface {
    @FXML
    protected TableView<ReportTableData> reportTable;
    @FXML
    protected ComboBox<BusDTO> busComboBox;
    @FXML
    protected AnchorPane QRPane;
    @FXML
    protected TextArea logArea;
    @FXML
    protected Button QRStatus;
    @FXML
    protected ImageView imageBox;

    protected ObservableList<ReportTableData> reports = FXCollections.observableArrayList();
    protected ObservableList<BusDTO> buses = FXCollections.observableArrayList();
    private AccessorWindowService accessorWindowService;
    private BusDTO selectedBus;
    private RouteDTO currentRoute;
    private TripDTO currentTrip;
    private EventDTO currentEvent;
    private WebcamPanel webcamPanel;
    private NewWebcamQRCodeReader webcamQRCodeReader;
    private boolean on;
    private SwingNode node;
    private HashSet<String> codesToScan = new HashSet<>();

    @FXML
    public void initialize() {
        reportTable.setItems(reports);
        busComboBox.setItems(buses);
        on = false;
    }
    @FXML
    public void QRStatusAction(ActionEvent event) {
         if(!on){
             if(selectedBus == null) {
                 //gestione errore
                 return;
             }
             loadQRreader();
             QRStatus.setText("Turn OFF");
             on = true;
         }
         else {
             shutDownWebcam();
             QRStatus.setText("Turn ON");
             QRPane.getChildren().clear();
             QRPane.getChildren().add(imageBox);
             imageBox.setImage(new Image("/image/frame.png"));
             on = false;
         }
    }
    @FXML
    public void busSelectionAction(ActionEvent event) {
        selectedBus = busComboBox.getValue();
    }

    public void setAccessorWindowService(AccessorWindowService accessorWindowService) {
        this.accessorWindowService = accessorWindowService;
        accessorWindowService.setOnCloseAction(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                shutDownWebcam();
            }
        });
    }
    public void initData(TripDTO tripDTO, RouteDTO routeDTO) {
        currentRoute = routeDTO;
        currentTrip = tripDTO;
        buses.clear();
        buses.addAll(tripDTO.getBuses());
        initEvent(routeDTO);
        refreshTable();

    }
    public void refreshTable() {
        List<CheckpointDTO> checkpointDTOList = new ArrayList<>();
        try {
            checkpointDTOList.addAll(Client.getSessionService().getSession().getEventCheckpoints(currentEvent));
        } catch(RemoteException ex) {
            //gestione
            ex.printStackTrace();
        }
        reports.clear();
        for(CheckpointDTO checkpointDTO : checkpointDTOList) {
            reports.add(new ReportTableData(checkpointDTO.getPerson().getFiscalCode(), checkpointDTO.getPerson().getFirstName(), checkpointDTO.getPerson().getLastName(), checkpointDTO.getTime().toString()));
        }
        for(TripPartecipationDTO tripPartecipationDTO : currentTrip.getTripPartecipations()) {
            codesToScan.add(tripPartecipationDTO.getPerson().getFiscalCode());
        }
        for(ReportTableData reportTableData : reports) {
            if(codesToScan.remove(reportTableData.getFiscalCode()));
        }
    }
    private void loadQRreader() {
        try {
            webcamQRCodeReader = new NewWebcamQRCodeReader(this);
        } catch(CameraBusyException ex) {
            //gestione
            ex.printStackTrace();
        }
    }
    public void anchorChild(AnchorPane anchorPane, Node node) {
        anchorPane.setBottomAnchor(node, 0.0);
        anchorPane.setLeftAnchor(node, 0.0);
        anchorPane.setTopAnchor(node, 0.0);
        anchorPane.setRightAnchor(node, 0.0);
    }
    public void saveCheckPoint(String code) {
        try{
            LocalTime time = LocalTime.now();
            if(codesToScan.contains(code)) {
                codesToScan.remove(code);
                Client.getSessionService().getSession().saveTripCheckpoint(code, currentEvent, time, selectedBus.getLicensePlate(), currentTrip);
                logArea.appendText(code + " Registrato correttamente alle " + time + "\n");
                refreshTable();
            }
        } catch (Exception e){
            e.printStackTrace();
            //gestione
        }
    }
    public void setPane(WebcamPanel webcamPanel) {
        this.webcamPanel = webcamPanel;
        webcamPanel.setMirrored(true);
        QRPane.getChildren().clear();
        node = new SwingNode();
        node.setContent(webcamPanel);
        QRPane.getChildren().add(node);
        anchorChild(QRPane, node);
    }
    public void shutDownWebcam() {
        if(webcamQRCodeReader != null) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    webcamQRCodeReader.shutDown();
                    webcamPanel.stop();
                }
            });
            thread.start();
        }
    }
    public void initEvent(RouteDTO routeDTO) {
        if(routeDTO.getDepartureEvent() != null && routeDTO.getDepartureEvent().getEventStatus().equals(EventStatus.OPEN)) {
            currentEvent = routeDTO.getDepartureEvent();
            return;
        }
        if(routeDTO.getArrivalEvent() != null && routeDTO.getArrivalEvent().getEventStatus().equals(EventStatus.OPEN)) {
            currentEvent = routeDTO.getArrivalEvent();
        }
    }

    public void notifyUpdate() { }
}
