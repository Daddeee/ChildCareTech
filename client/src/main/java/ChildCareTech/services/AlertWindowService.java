package ChildCareTech.services;

import ChildCareTech.controller.AlertController;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class AlertWindowService {
    private Stage stage;
    private AlertController controller;
    private FXMLLoader loader;
    private Scene scene;
    private boolean ready = false;

    public AlertWindowService() {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        loader = new FXMLLoader(AccessorWindowService.class.getResource(ResourcesPaths.getAlertWindowFXMLPath()));
        try {
            scene = new Scene(loader.load());
            ready = true;
        } catch(IOException ex) {
            System.err.println("error loading alert window");
            ex.printStackTrace();
        }
    }

    public void close() {
        stage.close();
    }
    public void show() {
        stage.show();
    }
    public void hide() {
        stage.hide();
    }
    public void setOnCloseAction(EventHandler<WindowEvent> eventEventHandler) {
        stage.setOnCloseRequest(eventEventHandler);
    }
    public void loadWindow(String message) {
        if(ready) {
            scene.getStylesheets().add(ResourcesPaths.getAlertWindowCSSPath());
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
