package ChildCareTech.services;

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

    private MainSceneManager() {
    }

    public static void loadLogin() throws IOException {
        loginLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/loginWindow.fxml"));
        loginScene = sceneInit(loginLoader, "style/loginWindow.css");
        try {
            MainStageService.changeScene("/view/loginWindow.fxml", "/style/loginWindow.css");
        } catch (NoSuchFieldException ex) {
            ex.printStackTrace();
        }
    }

    public static void loadHome() throws IOException {
        homeLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/homeWindow.fxml"));
        homeScene = sceneInit(homeLoader, "/style/homeWindow.css");
        try {
            MainStageService.changeScene("/view/homeWindow.fxml", "/style/homeWindow.css");
        } catch (NoSuchFieldException ex) {
            ex.printStackTrace();
        }

    }

    public static void loadAnagraphics() throws IOException {
        kidAnagraphicsLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/kidAnagraphicsWindow.fxml"));
        kidAnagraphicsScene = sceneInit(kidAnagraphicsLoader, "/style/anagraphicsWindow.css");
        try {
            MainStageService.changeScene("/view/kidAnagraphicsWindow.fxml", "/style/anagraphicsWindow.css");
        } catch (NoSuchFieldException ex) {
            ex.printStackTrace();
        }
    }

    public static void loadTrips() throws IOException {
        tripListLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/tripsWindow.fxml"));
        tripListScene = sceneInit(tripListLoader, "/style/tripsWindow.css");
        try {
            MainStageService.changeScene("/view/tripsWindow.fxml", "/style/tripsWindow.css");
        } catch (NoSuchFieldException ex) {
            ex.printStackTrace();
        }
    }
}
