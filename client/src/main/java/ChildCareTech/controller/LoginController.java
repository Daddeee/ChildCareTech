package ChildCareTech.controller;

import ChildCareTech.common.UserSessionFactory;
import ChildCareTech.services.MainSceneManager;
import ChildCareTech.services.SessionService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    UserSessionFactory sessionFactory = null;

    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label alertBox;

    @FXML
    private Button loginButton;

    public LoginController() { }

    @FXML
    protected void loginButtonAction(ActionEvent event) {

        alertBox.setText("");
        if(userNameField.getText().equals("") || passwordField.getText().equals("")){
            alertBox.setText("Empty fields!");
            return;
        }

        SessionService.loginAttempt(sessionFactory, userNameField.getText(), passwordField.getText());

        try {
            if(!SessionService.isNull()) MainSceneManager.loadHome();
            else alertBox.setText(SessionService.getLoginErrorMessage());
        } catch (IOException e) {
            e.printStackTrace();
            alertBox.setText(e.getMessage());
        }
    }
}
