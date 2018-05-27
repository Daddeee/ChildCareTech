package ChildCareTech.services;

import ChildCareTech.controller.AlertController;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

/**
 *This class provides a simple window management service for alert oriented communications.
 */
public class AlertWindowService {
    private Stage stage;
    private AlertController controller;
    private FXMLLoader loader;
    private Scene scene;
    private boolean ready = false;

    /**
     * This constructor creates a new stage, sets its modality to APPLICATION_MODAL, locks its dimensions, tries to create
     * the loader for the alert window and to load it into the scene attribute.
     */
    public AlertWindowService() {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        loader = new FXMLLoader(AccessorWindowService.class.getResource(ResourcesPaths.getAlertWindowFXMLPath()));
        try {
            scene = new Scene(loader.load());
            scene.getStylesheets().add(ResourcesPaths.getAlertWindowCSSPath());
            ready = true;
        } catch(IOException ex) {
            System.err.println("error loading alert window");
            ex.printStackTrace();
        }
    }

    /**
     * Closes the alert window's stage.
     */
    public void close() {
        stage.close();
    }
    public void show() {
        stage.show();
    }
    public void hide() {
        stage.hide();
    }

    /**
     * Sets a method that will be called by the closing of the alert window's stage.
     * @param eventEventHandler the eventHandler that contains the method.
     */
    public void setOnCloseAction(EventHandler<WindowEvent> eventEventHandler) {
        stage.setOnCloseRequest(eventEventHandler);
    }

    /**
     * Shows the alert window displaying the string message passed as parameter.
     * @param message the message to show in the alert window.
     */
    public void loadWindow(String message) {
        if(ready) {
            stage.setScene(scene);
            controller = loader.getController();
            controller.setAlertWindowService(this);
            controller.setMessage(message);
            stage.show();
        }
        else {
            System.err.println("AlertWindowService non disponibile");
        }
    }

}
