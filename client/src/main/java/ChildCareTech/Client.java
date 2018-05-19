package ChildCareTech;

import ChildCareTech.network.RegistrationService;
import ChildCareTech.network.SessionService;
import ChildCareTech.services.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Client extends Application {
    private static SessionService sessionService;
    private static RegistrationService registrationService;

    public static SessionService getSessionService() {
        return sessionService;
    }

    public static void setSessionService(SessionService s) {
        sessionService = s;
    }

    public static RegistrationService getRegistrationService() {
        return registrationService;
    }

    public static void setRegistrationService(RegistrationService r) {
        registrationService = r;
    }

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
        if(sessionService != null)
            sessionService.logoutAttempt();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
