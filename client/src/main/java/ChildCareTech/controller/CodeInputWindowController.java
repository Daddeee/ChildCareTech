package ChildCareTech.controller;

import ChildCareTech.Client;
import ChildCareTech.common.DTO.CheckpointDTO;
import ChildCareTech.common.DTO.EventDTO;
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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.WindowEvent;

import java.rmi.RemoteException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CodeInputWindowController implements AccessorWindowController, CheckPointControllerInterface{
    @FXML
    private AnchorPane QRPane;
    @FXML
    private ImageView imageBox;
    @FXML
    private Button QRStatus;
    @FXML
    private TextArea logArea;
    @FXML
    private TableView<ReportTableData> reportTable;

    protected ObservableList<ReportTableData> reports = FXCollections.observableArrayList();
    private AccessorWindowService accessorWindowService;
    private boolean on;
    private HashSet<String> scannedCodes = new HashSet<>();
    private EventDTO currentEvent;
    private WebcamPanel webcamPanel;
    private NewWebcamQRCodeReader webcamQRCodeReader;
    private SwingNode node;

    @FXML
    public void initialize() {
        reportTable.setItems(reports);
        on = false;
    }
    @FXML
    public void QRStatusAction(ActionEvent event) {
        if(!on){
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

    public void saveCheckPoint(String code) {
        try{
            LocalTime time = LocalTime.now();
            if(!scannedCodes.contains(code)) {
                scannedCodes.add(code);
                Client.getSessionService().getSession().saveCheckpoint(code, currentEvent, time);
                logArea.appendText(code + " Registrato correttamente alle " + time + "\n");
                refreshTable();
            }
        } catch (Exception e){
            e.printStackTrace();
            //gestione
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
    public void initData(EventDTO eventDTO) {
        currentEvent = eventDTO;
        refreshTable();
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
}
