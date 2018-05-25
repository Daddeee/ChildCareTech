package ChildCareTech.controller;

import ChildCareTech.services.AccessorWindowService;
import ChildCareTech.services.AlertWindowService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;


public class AlertController {
    @FXML
    private TextArea message;
    @FXML
    private Button button;

    private AlertWindowService alertWindowService;

    @FXML
    public void initialize() {
        message.setWrapText(true);
        message.setEditable(false);
    }
    @FXML
    public void buttonButtonAction(ActionEvent event) {
        if(alertWindowService != null)
            alertWindowService.close();
    }
    public void setMessage(String message) {
        this.message.setText(message);
    }
    public void setAlertWindowService(AlertWindowService alertWindowService) {
        this.alertWindowService = alertWindowService;
    }

}
