package ChildCareTech;

import ChildCareTech.controller.Login;
import ChildCareTech.services.SceneManager;
import ChildCareTech.services.StageService;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Client extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        StageService.setStage(primaryStage);
        StageService.setTitle("ChildCareTech");
        StageService.setResizable(false);
        try {
            SceneManager.loadLogin();
        } catch(IOException ex) {
            System.err.println("Can't render login window");
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
