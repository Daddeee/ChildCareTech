package ChildCareTech;

import ChildCareTech.services.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Client extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        MainWindowService mainWindowService = new MainWindowService(primaryStage);
        mainWindowService.setTitle("ChildCareTech");
        //posso settare una onClose in modo che quando si chiude dalla X rossa si rilasciano i collegamenti rmi

        try {
            mainWindowService.loadLoginWindow();
        } catch (IOException ex) {
            System.err.println("Can't render login window");
            ex.printStackTrace();
        }
    }

    @Override
    public void stop() throws Exception {
        SessionService.logoutAttempt();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
