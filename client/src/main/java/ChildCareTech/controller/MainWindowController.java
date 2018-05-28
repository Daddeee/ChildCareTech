package ChildCareTech.controller;

import ChildCareTech.services.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainWindowController implements MainWindowControllerInterface {
    @FXML
    private Button userAccountButton;
    @FXML
    private Button kidAnagraphicsButton;
    @FXML
    private Button adultAnagraphicsButton;
    @FXML
    private Button canteenButton;
    @FXML
    private Button tripsButton;
    @FXML
    private Button eventsButton;
    @FXML
    private AnchorPane container;

    private ContainedWindowService containedWindowService;
    private MainWindowService mainWindowService;
    private AccessorWindowService logOutWindowService;

    @FXML
    public void initialize() {
        containedWindowService = new ContainedWindowService(container);
        logOutWindowService = new AccessorWindowService(new TableWindowControllerInterface() {
            @Override
            public void refreshTable() {
                //trick non oop
                mainWindowService.close();
                clearInstance();
                try {
                    mainWindowService.loadLoginWindow();
                } catch (IOException ex) {
                    System.err.println("error loading login");
                    ex.printStackTrace();
                }
            }
            @Override
            public void notifyUpdate() { }
        });
        eventsButtonAction(null);
        initButtonsToolTips();
    }

    @FXML
    public void userAccountButtonAction(ActionEvent event) {
        try {
            logOutWindowService.loadUserAccountWindow(mainWindowService.getUserName());
        } catch(IOException ex) {
            System.err.println("error loading user account window");
            ex.printStackTrace();
        }
    }
    @FXML
    public void kidAnagraphicsButtonAction(ActionEvent event) {
        containedWindowService.loadKidAnagraphics();
    }
    @FXML
    public void adultAnagraphicsButtonAction(ActionEvent event) {
        containedWindowService.loadAdultAnagraphics();
    }
    @FXML
    public void canteenButtonAction(ActionEvent event) {
        containedWindowService.loadCanteenManager();
    }
    @FXML
    public void tripsButtonAction(ActionEvent event) {
        containedWindowService.loadTripList();
    }
    @FXML
    public void eventsButtonAction(ActionEvent event) {
        containedWindowService.loadWorkDayManager();
    }

    public void clearInstance() {
        containedWindowService.clearInstance();
    }

    public void setMainWindowService(MainWindowService mainWindowService) {
        this.mainWindowService = mainWindowService;
    }

    private void initButtonsToolTips() {
        userAccountButton.setTooltip(new Tooltip("Account utente"));
        kidAnagraphicsButton.setTooltip(new Tooltip("Anagrafica bambini"));
        adultAnagraphicsButton.setTooltip(new Tooltip("Anagrafica persone"));
        canteenButton.setTooltip(new Tooltip("Gestione pasti"));
        tripsButton.setTooltip(new Tooltip("Gestione gite"));
        eventsButton.setTooltip(new Tooltip("Gestione eventi"));
    }
}
