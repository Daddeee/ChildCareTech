package ChildCareTech.controller;

import ChildCareTech.services.AccessorSceneManager;
import ChildCareTech.services.AccessorStageService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;


public class Anagraphic {

    @FXML
    private ListView anagList;

    @FXML
    private Button saveButton;

    @FXML
    private Button addButton;

    private ObservableList<String> items = FXCollections.observableArrayList();


    public Anagraphic() {

    }

    @FXML
    public void initialize() {

    }

    @FXML
    public void addButtonAction(ActionEvent event) {
        try {
            AccessorSceneManager.loadAddPerson();
        } catch(IOException ex) {
            System.err.println("Can't load addPerson window");
            ex.printStackTrace();
        }

    }

    @FXML
    public void saveButtonAction(ActionEvent event) {

    }
}
