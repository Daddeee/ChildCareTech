package ChildCareTech.services;

import ChildCareTech.controller.MainWindowControllerInterface;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class MainWindowService {
    private Stage stage;
    private FXMLLoader loader;

    public MainWindowService() {
        stage = new Stage();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                SessionService.logoutAttempt();
            }
        });
    }
    public MainWindowService(Stage stage) {
        this.stage = stage;
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
    public void setTitle(String string) { stage.setTitle(string); }
    public void setResizable(boolean bool) { stage.setResizable(bool); }
    public double getHeight() {
        return stage.getHeight();
    }
    public double getWidth() {
        return stage.getWidth();
    }
    private void setScene(Scene scene) {
        stage.setScene(scene);
    }
    public void setOnCloseAction(EventHandler<WindowEvent> eventEventHandler) {
        stage.setOnCloseRequest(eventEventHandler);
    }

    public void loadLoginWindow() throws IOException{
        loadWindow(ResourcesPaths.getLoginFXMLPath(), ResourcesPaths.getLoginCSSPath());
        ((MainWindowControllerInterface)loader.getController()).setMainWindowService(this);
    }
    public void loadMainWindow() throws IOException{
        loadWindow(ResourcesPaths.getMainWindowFXMLPath(), ResourcesPaths.getMainWindowCSSPath());
        ((MainWindowControllerInterface)loader.getController()).setMainWindowService(this);
    }
    public void loadDayGenerationWindow() throws IOException{
        loadWindow(ResourcesPaths.getDayGenerationFXMLPath(), ResourcesPaths.getDayGenerationCSSPath());
        ((MainWindowControllerInterface)loader.getController()).setMainWindowService(this);
    }
    private void loadWindow(String fxmlPath, String cssPath) throws IOException {
        loader = new FXMLLoader(AccessorWindowService.class.getResource(fxmlPath));
        try {
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(cssPath);
            stage.setScene(scene);
        } catch(LoadException ex) {
            System.err.println("error loading fxml file: "+fxmlPath+", "+cssPath);
            ex.printStackTrace();
        }
        stage.show();
    }
}
