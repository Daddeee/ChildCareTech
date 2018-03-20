package ChildCareTech.controller;

import ChildCareTech.common.UserSession;
import ChildCareTech.common.UserSessionFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class LoginController {
    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Text actionTarget;

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        UserSessionFactory userSessionFactory = null;
        UserSession userSession = null;

        try{
            userSessionFactory = (UserSessionFactory) Naming.lookup("rmi://localhost:1099/session_factory");

            userSession = userSessionFactory.login(userNameField.getText(), passwordField.getText());

            actionTarget.setText(Boolean.toString(!(userSession == null)));
        } catch (MalformedURLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (RemoteException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (NotBoundException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
