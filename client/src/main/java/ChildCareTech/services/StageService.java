package ChildCareTech.services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StageService {

    private static Stage primaryStage;

    private StageService() { }

    public static Stage getStage() {
        return primaryStage;
    }

    public static void setStage(Stage stage) {
        primaryStage = stage;
    }

    public static void close() {
        primaryStage.close();
    }

    public static void show() {
        primaryStage.show();
    }

    public static void setScene(Scene scene) {
        primaryStage.setScene(scene);
    }

    public static void setTitle(String title) {
        primaryStage.setTitle(title);
    }

    public static void setResizable(boolean bool) {
        primaryStage.setResizable(bool);
    }

    public static void hide() {
        primaryStage.hide();
    }

    public static double getHeight() {
        if(primaryStage == null)
            return 1000;
        return primaryStage.getHeight();
    }

    public static double getWidth() {
        if(primaryStage == null)
            return 1500;
        return primaryStage.getWidth();
    }

    public static void changeScene(Scene scene) {
        StageService.hide();
        StageService.setScene(scene);
        StageService.show();
    }

    public static void changeScene(String fxmlPath, String cssPath) throws IOException {
        Parent root = FXMLLoader.load(StageService.class.getResource(fxmlPath));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(cssPath);
        StageService.changeScene(scene);
    }
}
