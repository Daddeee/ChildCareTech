package ChildCareTech.controller;

import ChildCareTech.Client;
import ChildCareTech.services.AccessorWindowService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterUserController implements AccessorWindowController {
    @FXML
    private TextField addUserNameField;
    @FXML
    private PasswordField addPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Label registrationAlertBox;
    @FXML
    private Button cancelButton;
    @FXML
    private Button registerButton;

    private AccessorWindowService accessorWindowService;

    @FXML
    private void registerButtonAction(ActionEvent event) {
        boolean status;

        if (addUserNameField.getText().equals("") || addPasswordField.getText().equals("")) {
            registrationAlertBox.setText("Empty fields!");
            return;
        }

        if (!addPasswordField.getText().equals(confirmPasswordField.getText())) {
            registrationAlertBox.setText("Password not matching!");
            return;
        }

        status = Client.getRegistrationService().registerAttempt(addUserNameField.getText(), addPasswordField.getText());

        if (status) {
            accessorWindowService.close();
        } else
            registrationAlertBox.setText(Client.getRegistrationService().getRegistrationErrorMessage());

    }
    @FXML
    private void cancelButtonAction(ActionEvent event) {
        accessorWindowService.close();
    }

    public void setAccessorWindowService(AccessorWindowService accessorWindowService) {
        this.accessorWindowService = accessorWindowService;
    }
}
