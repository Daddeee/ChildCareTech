package ChildCareTech.controller;

import ChildCareTech.common.UserSession;
import ChildCareTech.common.UserSessionFactory;
import ChildCareTech.util.LoginUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {

    private Stage primaryStage;
    private Parent root;
    UserSessionFactory sessionFactory = null;
    UserSession session = null;

    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label alertBox;

    @FXML
    private Button logintButton;

    public Login() { }

    public void render(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        root = FXMLLoader.load(getClass().getResource("/view/loginWindow.fxml"));
        primaryStage.setTitle("ChildCareTech");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    protected void loginButtonAction(ActionEvent event) {

        alertBox.setText("");
        if(userNameField.getText().equals("") || passwordField.getText().equals("")){
            alertBox.setText("Empty fields!");
            return;
        }

        session = LoginUtil.loginAttempt(sessionFactory, userNameField.getText(), passwordField.getText());

        alertBox.setText("Login: " + Boolean.toString(!(session == null)));
    }
}
