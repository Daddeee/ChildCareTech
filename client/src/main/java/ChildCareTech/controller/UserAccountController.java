package ChildCareTech.controller;

import ChildCareTech.Client;
import ChildCareTech.services.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class UserAccountController implements AccessorWindowController{

    @FXML
    private Button logOutButton;
    @FXML
    private Label userName;
    @FXML
    private AnchorPane userImagePane;

    private AccessorWindowService accessorWindowService;

    @FXML
    public void initialize() {

    }

    @FXML
    private void logOutButtonAction(ActionEvent event) {
        Client.getSessionService().logoutAttempt();
        accessorWindowService.close();
        accessorWindowService.refreshTable(); //trick non oop
    }
    public void setAccessorWindowService(AccessorWindowService accessorWindowService) {
        this.accessorWindowService = accessorWindowService;
    }
    public void setUserName(String userName) {
        this.userName.setText(userName);
    }
}
