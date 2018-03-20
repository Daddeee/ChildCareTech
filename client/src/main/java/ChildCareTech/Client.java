package ChildCareTech;

import ChildCareTech.controller.Login;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Client extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Login logIn = new Login();
        logIn.render(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
