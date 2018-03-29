package ChildCareTech.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;



public class Anagraphic {

    @FXML
    private ListView anagList;

    private ObservableList<String> items = FXCollections.observableArrayList();


    public Anagraphic() {

    }

    @FXML
    public void initialize() {
        anagList.setItems(items);
        items.add("uno");
        items.add("due");
    }

    @FXML
    public void addButtonAction(ActionEvent event) {


    }
}
