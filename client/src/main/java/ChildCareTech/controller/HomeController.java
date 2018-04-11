package ChildCareTech.controller;

import ChildCareTech.services.MainSceneManager;
import ChildCareTech.services.SessionService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sun.applet.Main;

import java.io.IOException;

public class HomeController {

    @FXML
    private Button kidsAnagraphicsButton;
    @FXML
    private Button adultsAnagraphicsBUtton;

    public HomeController() { }

    @FXML
    protected void kidAnagraphicsButtonAction(ActionEvent action) {
        try {
            MainSceneManager.loadKidAnagraphics();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    protected void adultAnagraphicsButtonAction(ActionEvent action) {
        try {
            MainSceneManager.loadAdultAnagraphics();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    protected void tripButtonAction(ActionEvent action){
        try {
            MainSceneManager.loadTrips();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    protected void busButtonAction(ActionEvent action){
        try {
            MainSceneManager.loadBuses();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    protected void logoutButtonAction(ActionEvent action) {
        SessionService.logoutAttempt();
        try {
            MainSceneManager.loadLogin();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
