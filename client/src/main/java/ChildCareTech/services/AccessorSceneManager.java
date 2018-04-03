package ChildCareTech.services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class AccessorSceneManager {

    private static FXMLLoader loginLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/addKidWindow.fxml"));

    private AccessorSceneManager() { }

    public static void loadAddPerson() throws IOException {
        Scene scene = new Scene(loginLoader.load());
        scene.getStylesheets().add("/style/addKidWindow.css");
        try {
            AccessorStageService.changeScene(scene);
        } catch(NoSuchFieldException ex) {
            AccessorSceneManager.stageError(ex);
        }
    }

    private static void stageError(NoSuchFieldException ex) {
        System.err.println("accessory stage not initialized");
        ex.printStackTrace();
    }
}

    public static void loadRegisterUser() throws IOException {
        try {
            AccessorStageService.changeScene("/view/registerUserWindow.fxml", "style/registerUserWindow.css");
        } catch(NoSuchFieldException ex) {
            ex.printStackTrace();
        }
    }
}
