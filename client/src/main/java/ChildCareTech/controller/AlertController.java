package ChildCareTech.controller;

import ChildCareTech.services.AccessorWindowService;
import ChildCareTech.services.AlertMethodService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class AlertController implements AccessorWindowController {
    @FXML
    private Label message;
    @FXML
    private Button button1;
    @FXML
    private Button button2;

    private AccessorWindowService accessorWindowService;
    private AlertMethodService alertMethodService;

    @FXML
    public void button1ButtonAction(ActionEvent event) {
        alertMethodService.firstButtonAction(accessorWindowService);
    }
    @FXML
    public void button2ButtonAction(ActionEvent event) {
        alertMethodService.secondButtonAction(accessorWindowService);
    }

    public void initData(AlertMethodService alertMethodService) {
        this.alertMethodService = alertMethodService;
        button1.setText(alertMethodService.setFirstButtonLabel());
        button2.setText(alertMethodService.setSecondButtonLabel());
    }

    public void setAccessorWindowService(AccessorWindowService accessorWindowService) {
        this.accessorWindowService = accessorWindowService;
    }
}
