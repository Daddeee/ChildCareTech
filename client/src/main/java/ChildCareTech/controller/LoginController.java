package ChildCareTech.controller;

import ChildCareTech.common.Session;
import ChildCareTech.common.SessionFactory;
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
        SessionFactory sessionFactory = null;
        Session session = null;

        try{
            sessionFactory = (SessionFactory) Naming.lookup("rmi://localhost:1099/session_factory");

            session = sessionFactory.login(userNameField.getText(), passwordField.getText());

            actionTarget.setText(Boolean.toString(!(session == null)));
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
