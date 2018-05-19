package ChildCareTech.controller;

import ChildCareTech.Client;
import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.utils.WebcamQRCodeReader;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.LocalTime;
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
                Client.getSessionService().getSession().saveCheckpoint(fiscalCode, eventDTO, time);
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
