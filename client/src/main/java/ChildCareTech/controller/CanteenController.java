package ChildCareTech.controller;

import ChildCareTech.services.AccessorSceneManager;
import ChildCareTech.services.MainSceneManager;
import ChildCareTech.services.SessionService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.rmi.RemoteException;

public class CanteenController {
    @FXML
    protected ComboBox<String> selectCanteen;

    public void initialize() {
        try{
            selectCanteen.getItems().clear();
            selectCanteen.getItems().addAll(SessionService.getSession().getAllCanteenNames());
        } catch (RemoteException e){
            e.printStackTrace();
        }
    }

    @FXML
    protected void addButtonAction(ActionEvent event) {
        try{
            AccessorSceneManager.loadAddCanteen();
        } catch (IOException ex){
            System.err.println("Can't load addKid window");
            ex.printStackTrace();
        }
    }

    @FXML
    protected void backButtonAction(ActionEvent event) {
        try {
            MainSceneManager.loadHome();
        } catch (IOException ex) {
            System.err.println("Can't load addKid window");
            ex.printStackTrace();
        }
    }
}
