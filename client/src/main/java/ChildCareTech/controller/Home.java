package ChildCareTech.controller;

import ChildCareTech.services.MainSceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class Home {

    @FXML
    private Button anagraphicsButton;

    public Home() {

    }

    @FXML
    protected void anagraphicsButtonAction(ActionEvent action) {
        try{
            MainSceneManager.loadAnagraphics();
        } catch(IOException ex) {
            System.err.println("Can't render anagraphics window");
            ex.printStackTrace();
        }
    }
}
