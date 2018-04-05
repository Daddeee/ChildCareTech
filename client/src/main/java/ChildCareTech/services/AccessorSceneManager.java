package ChildCareTech.services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AccessorSceneManager {

    private static FXMLLoader loginLoader;
    private static FXMLLoader registerUserLoader;
    private static Scene addKidScene;
    private static Scene registerUserScene;

    static {
        loginLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/addKidWindow.fxml"));
        registerUserLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/registerUserWindow.fxml"));
        addKidScene = sceneInit(loginLoader, "/style/addKidWindow.css");
        registerUserScene = sceneInit(registerUserLoader, "style/registerUserWindow.css");
    }

    private AccessorSceneManager() {
    }

    public static void loadAddPerson() throws IOException {
        try {
            AccessorStageService.changeScene(addKidScene);
        } catch (NoSuchFieldException ex) {
            AccessorSceneManager.stageError(ex);
        }
    }

    private static void stageError(NoSuchFieldException ex) {
        System.err.println("accessory stage not initialized");
        ex.printStackTrace();
    }

    public static void loadRegisterUser() throws IOException {
        try {
            AccessorStageService.changeScene(registerUserScene);
        } catch (NoSuchFieldException ex) {
            ex.printStackTrace();
        }
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
