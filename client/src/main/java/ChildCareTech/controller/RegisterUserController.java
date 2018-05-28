package ChildCareTech.controller;

import ChildCareTech.Client;
import ChildCareTech.services.AccessorWindowService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    @FXML
    private Label confirmationLabel;

    private AccessorWindowService accessorWindowService;

    @FXML
    public void initialize() {
        confirmationLabel.setText("");
        confirmPasswordField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(confirmPasswordField.getText().equals(addPasswordField.getText()))
                    confirmationLabel.setText("√");
                else
                    confirmationLabel.setText("");
            }
        });
    }

    @FXML
    private void registerButtonAction(ActionEvent event) {
        boolean status;

        if (addUserNameField.getText().equals("") || addPasswordField.getText().equals("")) {
            registrationAlertBox.setText("Dati mancanti!");
            return;
        }

        if (!addPasswordField.getText().equals(confirmPasswordField.getText())) {
            registrationAlertBox.setText("Le password non corrispondono!");
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
