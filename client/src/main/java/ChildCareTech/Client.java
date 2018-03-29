package ChildCareTech;

import ChildCareTech.services.AccessorStageService;
import ChildCareTech.services.MainSceneManager;
import ChildCareTech.services.MainStageService;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Client extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        MainStageService.setStage(primaryStage);

        try {
            MainStageService.setTitle("ChildCareTech");
            MainStageService.setResizable(false);
            AccessorStageService.init();

            MainSceneManager.loadLogin();
        } catch(IOException ex) {
            System.err.println("Can't render login window");
            ex.printStackTrace();
        } catch (NoSuchFieldException ex) {
            System.err.println("Main stage not initialized");
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
