package ChildCareTech.controller;

import ChildCareTech.Client;
import ChildCareTech.network.RMI.RMISessionService;
import ChildCareTech.network.socket.SocketSessionService;
import ChildCareTech.services.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class LoginController implements MainWindowControllerInterface {
    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label alertBox;

    @FXML
    private Button loginButton;

    @FXML
    private ComboBox<String> connectivity;

    private MainWindowService mainWindowService;
    private AccessorWindowService accessorWindowService;

    @FXML
    public void initialize() {
        accessorWindowService = new AccessorWindowService(new TableWindowControllerInterface() {
            @Override
            public void refreshTable() { }
        });

        connectivity.getSelectionModel().selectFirst();
    }

    @FXML
    protected void loginButtonAction(ActionEvent event) {

        alertBox.setText("");
        if (userNameField.getText().equals("") || passwordField.getText().equals("")) {
            alertBox.setText("Empty fields!");
            return;
        }

        if(connectivity.getSelectionModel().getSelectedItem().equals("RMI"))
            Client.setSessionService(new RMISessionService());
        else if(connectivity.getSelectionModel().getSelectedItem().equals("Socket")) {
            try {
                Client.setSessionService(new SocketSessionService());
            } catch (IOException e) {
                alertBox.setText("Socket error");
                e.printStackTrace();
            }
        } else {
            alertBox.setText("Connectivity selection error");
        }


        Client.getSessionService().loginAttempt(userNameField.getText(), passwordField.getText());

        try {
            if (!Client.getSessionService().isNull()){
                if(Client.getSessionService().getSession().isFirstEverStartup())
                    mainWindowService.loadDayGenerationWindow();
                else
                    mainWindowService.loadMainWindow();
            }
            else alertBox.setText(Client.getSessionService().getLoginErrorMessage());
        } catch (IOException e) {
            //e.printStackTrace();
            alertBox.setText(e.getMessage());
        }
    }

    @FXML
    protected void registerButtonAction(ActionEvent event) {
        try {
            accessorWindowService.loadRegisterUserWindow();
        } catch (IOException e) {
            e.printStackTrace();
            alertBox.setText(e.getMessage());
        }
    }

    public void setMainWindowService(MainWindowService mainWindowService) {
        this.mainWindowService = mainWindowService;
    }
}
