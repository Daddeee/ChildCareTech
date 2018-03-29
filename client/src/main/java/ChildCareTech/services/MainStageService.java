package ChildCareTech.services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainStageService {
    private static Stage mainStage;

    private MainStageService() { }

    public static Stage getStage() throws NoSuchFieldException {
        if(mainStage == null)
            throw new NoSuchFieldException("Stage not initialized");
        return mainStage;
    }

    public static void setStage(Stage stage) {
        mainStage = stage;
    }

    public static void close() throws NoSuchFieldException {
        if(mainStage == null)
            throw new NoSuchFieldException("Stage not initialized");
        mainStage.close();
    }

    public static void show() throws NoSuchFieldException {
        if(mainStage == null)
            throw new NoSuchFieldException("Stage not initialized");
        mainStage.show();
    }

    public static void setScene(Scene scene) throws NoSuchFieldException {
        if(mainStage == null)
            throw new NoSuchFieldException("Stage not initialized");
        mainStage.setScene(scene);
    }

    public static void setTitle(String title) throws NoSuchFieldException {
        if(mainStage == null)
            throw new NoSuchFieldException("Stage not initialized");
        mainStage.setTitle(title);
    }

    public static void setResizable(boolean bool) throws NoSuchFieldException {
        if(mainStage == null)
            throw new NoSuchFieldException("Stage not initialized");
        mainStage.setResizable(bool);
    }

    public static void hide() throws NoSuchFieldException {
        if(mainStage == null)
            throw new NoSuchFieldException("Stage not initialized");
        mainStage.hide();
    }

    public static double getHeight() throws NoSuchFieldException {
        if(mainStage == null)
            throw new NoSuchFieldException("Stage not initialized");
        return mainStage.getHeight();
    }

    public static double getWidth() throws NoSuchFieldException {
        if(mainStage == null)
            throw new NoSuchFieldException("Stage not initialized");
        return mainStage.getWidth();
    }

    public static void changeScene(Scene scene) throws NoSuchFieldException {
        if(mainStage == null)
            throw new NoSuchFieldException("Stage not initialized");
        MainStageService.hide();
        MainStageService.setScene(scene);
        MainStageService.show();
    }

    public static void changeScene(String fxmlPath, String cssPath) throws IOException, NoSuchFieldException {
        if(mainStage == null)
            throw new NoSuchFieldException("Stage not initialized");
        Parent root = FXMLLoader.load(MainStageService.class.getResource(fxmlPath));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(cssPath);
        MainStageService.changeScene(scene);
    }
}
