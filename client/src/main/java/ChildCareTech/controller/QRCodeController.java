package ChildCareTech.controller;

import ChildCareTech.common.DTO.CheckpointDTO;
import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.services.SessionService;
import ChildCareTech.utils.WebcamQRCodeReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.collections.ObservableSet;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;

public class QRCodeController {
    @FXML
    protected SwingNode qrSwingNode;
    @FXML
    protected Label alertLabel;

    private EventDTO eventDTO;

    private HashSet<String> scannedCodes = new HashSet<>();
    private WebcamQRCodeReader reader = new WebcamQRCodeReader(this );

    public void initialize(){
        this.qrSwingNode.setContent(reader);
    }

    public void initData(EventDTO eventDTO) {
        this.eventDTO = eventDTO;
    }

    public void saveCheckPoint(String fiscalCode) {
        try{
            LocalTime time = LocalTime.now();
            if(!scannedCodes.contains(fiscalCode)) {
                scannedCodes.add(fiscalCode);
                SessionService.getSession().saveCheckpoint(fiscalCode, eventDTO, time);
                alertLabel.setText(fiscalCode + " Registrato correttamente alle " + time);
            }
        } catch (Exception e){
            alertLabel.setText(e.getMessage());
        }
    }

    public void shutDown(){
        reader.shutDown();
    }
}
