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

public class Login {

    UserSessionFactory sessionFactory = null;

    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label alertBox;

    @FXML
    private Button loginButton;

    public Login() { }

    @FXML
    protected void loginButtonAction(ActionEvent event) {

        alertBox.setText("");
        if(userNameField.getText().equals("") || passwordField.getText().equals("")){
            alertBox.setText("Empty fields!");
            return;
        }

        SessionService.loginAttempt(sessionFactory, userNameField.getText(), passwordField.getText());

        if(true) {   //!SessionService.isNull()
            try{
                MainSceneManager.loadHome();
            } catch(IOException ex) {
                System.err.println("Can't render home window");
                ex.printStackTrace();
                alertBox.setText("Can't load Home");
            }

        }
        else {
            alertBox.setText("Wrong username/password combination");
        }
    }
}
