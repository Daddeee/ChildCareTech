package ChildCareTech.controller;

import ChildCareTech.services.AccessorSceneManager;
import ChildCareTech.services.AccessorStageService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;

public class SelectAdultKindController {

    @FXML
    private RadioButton normalButton;
    @FXML
    private RadioButton supplierButton;
    @FXML
    private RadioButton pediatristButton;
    @FXML
    private RadioButton staffButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button goButton;

    private ToggleGroup group = new ToggleGroup();

    @FXML
    public void initialize() {
        normalButton.setToggleGroup(group);
        supplierButton.setToggleGroup(group);
        pediatristButton.setToggleGroup(group);
        staffButton.setToggleGroup(group);
        normalButton.fire();
    }

    @FXML
    public void goButtonAction(ActionEvent event) {
        if(normalButton.isSelected()) {
            try {
                AccessorSceneManager.loadAddAdult();
            } catch (IOException ex) {
                System.err.println("Can't load addAdult window");
                ex.printStackTrace();
            }
            return;
        }
        if(supplierButton.isSelected()) {
            try {
                AccessorSceneManager.loadAddSupplier();
            } catch (IOException ex) {
                System.err.println("Can't load addSupplier window");
                ex.printStackTrace();
            }
            return;
        }
        if(pediatristButton.isSelected()) {
            try {
                AccessorSceneManager.loadAddPediatrist();
            } catch (IOException ex) {
                System.err.println("Can't load addPediatrist window");
                ex.printStackTrace();
            }
            return;
        }
        if(staffButton.isSelected()) {
            return;
        }

    }

    @FXML
    public void cancelButtonAction(ActionEvent event) {
        try {
            AccessorStageService.close();
        } catch (NoSuchFieldException ex) {
            System.err.println("Stage not initialized");
            ex.printStackTrace();
        }
    }
}
