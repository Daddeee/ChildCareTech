package ChildCareTech.controller;

import ChildCareTech.services.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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

    private MainWindowService mainWindowService;
    private AccessorWindowService accessorWindowService;

    @FXML
    public void initialize() {
        accessorWindowService = new AccessorWindowService(new TableWindowControllerInterface() {
            @Override
            public void refreshTable() { }
        });
    }

    @FXML
    protected void loginButtonAction(ActionEvent event) {

        alertBox.setText("");
        if (userNameField.getText().equals("") || passwordField.getText().equals("")) {
            alertBox.setText("Empty fields!");
            return;
        }

        SessionService.loginAttempt(userNameField.getText(), passwordField.getText());

        try {
            if (!SessionService.isNull()){
                if(SessionService.getSession().isFirstEverStartup())
                    mainWindowService.loadDayGenerationWindow();
                else
                    mainWindowService.loadMainWindow();
            }
            else alertBox.setText(SessionService.getLoginErrorMessage());
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
