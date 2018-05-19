package ChildCareTech.controller;

import ChildCareTech.Client;
import ChildCareTech.services.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class UserAccountController implements AccessorWindowController{

    @FXML
    private Button logOutButton;

    private AccessorWindowService accessorWindowService;

    @FXML
    private void logOutButtonAction(ActionEvent event) {
        Client.getSessionService().logoutAttempt();
        accessorWindowService.close();
        accessorWindowService.refreshTable(); //trick non oop
    }
    public void setAccessorWindowService(AccessorWindowService accessorWindowService) {
        this.accessorWindowService = accessorWindowService;
    }
}
