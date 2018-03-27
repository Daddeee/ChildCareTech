package ChildCareTech.controller;

import ChildCareTech.services.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class HomeWindow {

    @FXML
    private Button anagraphicsButton;

    public HomeWindow() {

    }

    @FXML
    protected void anagraphicsButtonAction(ActionEvent action) {
        try{
            SceneManager.loadAnagraphics();
        } catch(IOException ex) {
            System.err.println("Can't render anagraphics window");
            ex.printStackTrace();
        }
    }
}
