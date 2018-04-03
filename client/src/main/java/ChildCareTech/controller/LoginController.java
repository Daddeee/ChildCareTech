package ChildCareTech.controller;

import ChildCareTech.common.UserSessionFactory;
import ChildCareTech.services.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.xml.soap.Text;
import java.io.IOException;

public class LoginController {
    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label alertBox;

    @FXML
    private Button loginButton;

    @FXML
    private TextField addUserNameField;

    @FXML
    private PasswordField addPasswordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label registrationAlertBox;

    public LoginController() { }

    @FXML
    protected void loginButtonAction(ActionEvent event) {

        alertBox.setText("");
        if(userNameField.getText().equals("") || passwordField.getText().equals("")){
            alertBox.setText("Empty fields!");
            return;
        }

        SessionService.loginAttempt(userNameField.getText(), passwordField.getText());

        try {
            if(!SessionService.isNull()) MainSceneManager.loadHome(); //!SessionService.isNull()
            else alertBox.setText(SessionService.getLoginErrorMessage());
        } catch (IOException e) {
            e.printStackTrace();
            alertBox.setText(e.getMessage());
        }
    }

    @FXML
    protected void registerButtonAction(ActionEvent event){
        try {
            AccessorSceneManager.loadRegisterUser();
        } catch (IOException e) {
            e.printStackTrace();
            alertBox.setText(e.getMessage());
        }
    }

    @FXML
    protected void doRegisterButtonAction(ActionEvent event){
        boolean status;

        if(addUserNameField.getText().equals("") || addPasswordField.getText().equals("")){
            registrationAlertBox.setText("Empty fields!");
            return;
        }

        if(!addPasswordField.getText().equals(confirmPasswordField.getText())){
            registrationAlertBox.setText("Password not matching!");
            return;
        }

        status = RegistrationService.registerAttempt(addUserNameField.getText(), addPasswordField.getText());

        try {
            if(status) {
                AccessorStageService.close();
                MainSceneManager.loadLogin();
            }
            else registrationAlertBox.setText(RegistrationService.getRegistrationErrorMessage());
        } catch (IOException | NoSuchFieldException e) {
            e.printStackTrace();
            registrationAlertBox.setText(e.getMessage());
        }

    }
}
