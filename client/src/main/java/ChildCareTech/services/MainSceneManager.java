package ChildCareTech.services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainSceneManager {
    private static FXMLLoader loginLoader;
    private static FXMLLoader homeLoader;
    private static FXMLLoader kidAnagraphicsLoader;
    private static FXMLLoader tripListLoader;
    private static Scene loginScene;
    private static Scene homeScene;
    private static Scene kidAnagraphicsScene;
    private static Scene tripListScene;


    static {
        loginLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/loginWindow.fxml"));
        homeLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/homeWindow.fxml"));
        kidAnagraphicsLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/kidAnagraphicsWindow.fxml"));
        tripListLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/tripsWindow.fxml"));
        loginScene = sceneInit(loginLoader, "style/loginWindow.css");
        homeScene = sceneInit(homeLoader, "/style/homeWindow.css");
        kidAnagraphicsScene = sceneInit(kidAnagraphicsLoader, "/style/anagraphicsWindow.css");
        tripListScene = sceneInit(tripListLoader, "/style/tripsWindow.css");
    }


    private MainSceneManager() {
    }

    public static void loadLogin() throws IOException {
        try {
            MainStageService.changeScene(loginScene);
        } catch (NoSuchFieldException ex) {
            MainSceneManager.stageError(ex);
        }
    }

    public static void loadHome() throws IOException {
        try {
            MainStageService.changeScene(homeScene);
        } catch (NoSuchFieldException ex) {
            MainSceneManager.stageError(ex);
        }

    }

    public static void loadAnagraphics() throws IOException {
        try {
            MainStageService.changeScene(kidAnagraphicsScene);
        } catch (NoSuchFieldException ex) {
            MainSceneManager.stageError(ex);
        }
    }

    public static void loadTrips() throws IOException {
        try {
            MainStageService.changeScene(tripListScene);
        } catch (NoSuchFieldException ex) {
            MainSceneManager.stageError(ex);
        }
    }

    private static void stageError(NoSuchFieldException ex) {
        System.err.println("accessory stage not initialized");
        ex.printStackTrace();
    }

    private static Scene sceneInit(FXMLLoader loader, String cssPath) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
            scene.getStylesheets().add(cssPath);
        } catch (IOException ex) {
            System.err.println("Can't find fxml/css file");
            ex.printStackTrace();
            return errScene();
        }
        return scene;
    }

    private static Scene errScene() {
        AnchorPane pane = new AnchorPane();
        Label label = new Label("fxml/css not found");
        pane.getChildren().add(label);
        return new Scene(pane, 200, 200);
    }
}
