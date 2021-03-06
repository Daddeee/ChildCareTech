package ChildCareTech.controller;

import ChildCareTech.Client;
import ChildCareTech.network.RMI.RMIRegistrationService;
import ChildCareTech.network.RMI.RMISessionService;
import ChildCareTech.network.socket.SocketRegistrationService;
import ChildCareTech.network.socket.SocketSessionService;
import ChildCareTech.services.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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
    @FXML
    private VBox root;

    private MainWindowService mainWindowService;
    private AccessorWindowService accessorWindowService;

    @FXML
    public void initialize() {
        accessorWindowService = new AccessorWindowService(new TableWindowControllerInterface() {
            @Override
            public void refreshTable() { }
            @Override
            public void notifyUpdate() { }
        });
        root.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
            if (ev.getCode() == KeyCode.ENTER) {
                loginButtonAction(null);
                ev.consume();
            }
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
                mainWindowService.setUserName(userNameField.getText());
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
            if(connectivity.getSelectionModel().getSelectedItem().equals("RMI"))
                Client.setRegistrationService(new RMIRegistrationService());
            else if(connectivity.getSelectionModel().getSelectedItem().equals("Socket")) {
                try {
                    Client.setRegistrationService(new SocketRegistrationService());
                } catch (IOException e) {
                    alertBox.setText("Socket error");
                    e.printStackTrace();
                    return;
                }
            } else {
                alertBox.setText("Connectivity selection error");
                return;
            }

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
