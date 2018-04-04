package ChildCareTech.services;

import java.io.IOException;

public class MainSceneManager {
    private MainSceneManager() {
    }

    public static void loadLogin() throws IOException {
        try {
            MainStageService.changeScene("/view/loginWindow.fxml", "/style/loginWindow.css");
        } catch (NoSuchFieldException ex) {
            ex.printStackTrace();
        }
    }

    public static void loadHome() throws IOException {
        try {
            MainStageService.changeScene("/view/homeWindow.fxml", "/style/homeWindow.css");
        } catch (NoSuchFieldException ex) {
            ex.printStackTrace();
        }

    }

    public static void loadAnagraphics() throws IOException {
        try {
            MainStageService.changeScene("/view/kidAnagraphicsWindow.fxml", "/style/anagraphicsWindow.css");
        } catch (NoSuchFieldException ex) {
            ex.printStackTrace();
        }
    }

    public static void loadTrips() throws IOException {
        try {
            MainStageService.changeScene("/view/tripsWindow.fxml", "/style/tripsWindow.css");
        } catch (NoSuchFieldException ex) {
            ex.printStackTrace();
        }
    }
}
