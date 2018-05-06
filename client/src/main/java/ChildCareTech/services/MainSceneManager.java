package ChildCareTech.services;

import ChildCareTech.controller.AdultAnagraphicsController;
import ChildCareTech.controller.HomeController;
import ChildCareTech.controller.KidAnagraphicsController;
import ChildCareTech.controller.TripsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainSceneManager {
    private static FXMLLoader loginLoader;
    private static FXMLLoader homeLoader;
    private static FXMLLoader kidAnagraphicsLoader;
    private static FXMLLoader tripListLoader;
    private static FXMLLoader adultAnagraphicsLoader;
    private static FXMLLoader busLoader;
    private static FXMLLoader dayGenerationLoader;
    private static FXMLLoader workDayLoader;
    private static FXMLLoader foodLoader;
    private static FXMLLoader canteenLoader;
    
    private static Scene loginScene;
    private static Scene homeScene;
    private static Scene kidAnagraphicsScene;
    private static Scene tripListScene;
    private static Scene adultAnagraphicsScene;
    private static Scene busScene;
    private static Scene dayGenerationScene;
    private static Scene workDayScene;
    private static Scene foodScene;
    private static Scene canteenScene;

    private MainSceneManager() {
    }

    public static AdultAnagraphicsController getAdultAnagController() {
        return  adultAnagraphicsLoader.getController();
    }
    public static HomeController getHomeController() {
        return homeLoader.getController();
    }
    public static KidAnagraphicsController getKidAnagController() {
        return kidAnagraphicsLoader.getController();
    }
    public static TripsController getTripsController() {
        return tripListLoader.getController();
    }

    public static void loadLogin() throws IOException {
        loginLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/loginWindow.fxml"));
        loginScene = sceneInit(loginLoader, "style/loginWindow.css");
        try {
            MainStageService.changeScene(loginScene);
        } catch (NoSuchFieldException ex) {
            ex.printStackTrace();
        }
    }

    public static void loadHome() throws IOException {
        homeLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/homeWindow.fxml"));
        homeScene = sceneInit(homeLoader, "/style/homeWindow.css");
        try {
            MainStageService.changeScene(homeScene);
        } catch (NoSuchFieldException ex) {
            ex.printStackTrace();
        }
    }

    public static void loadWorkDay() throws IOException {
        workDayLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/workDayWindow.fxml"));
        workDayScene = sceneInit(workDayLoader, "/style/workDayWindow.css");
        try{
            MainStageService.changeScene(workDayScene);
        } catch(NoSuchFieldException ex){
            ex.printStackTrace();
        }
    }

    public static void loadDayGeneration() throws IOException {
        dayGenerationLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/dayGenerationWindow.fxml"));
        dayGenerationScene = sceneInit(dayGenerationLoader, "/style/dayGenerationWindow.css");
        try {
            MainStageService.changeScene(dayGenerationScene);
        } catch (NoSuchFieldException ex) {
            ex.printStackTrace();
        }
    }

    public static void loadKidAnagraphics() throws IOException {
        kidAnagraphicsLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/kidAnagraphicsWindow.fxml"));
        kidAnagraphicsScene = sceneInit(kidAnagraphicsLoader, "/style/anagraphicsWindow.css");
        try {
            MainStageService.changeScene(kidAnagraphicsScene);
        } catch (NoSuchFieldException ex) {
            ex.printStackTrace();
        }
    }

    public static void loadAdultAnagraphics() throws IOException {
        adultAnagraphicsLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/adultAnagraphicsWindow.fxml"));
        adultAnagraphicsScene = sceneInit(adultAnagraphicsLoader, "/style/adultAnagraphicsWindow.css");
        try {
            MainStageService.changeScene(adultAnagraphicsScene);
        } catch (NoSuchFieldException ex) {
            ex.printStackTrace();
        }
    }

    public static void loadTrips() throws IOException {
        tripListLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/tripsWindow.fxml"));
        tripListScene = sceneInit(tripListLoader, "/style/tripsWindow.css");
        try {
            MainStageService.changeScene(tripListScene);
        } catch (NoSuchFieldException ex) {
            ex.printStackTrace();
        }
    }

    public static void loadBuses() throws IOException {
        busLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/busWindow.fxml"));
        busScene = sceneInit(busLoader, "/style/busWindow.css");
        try{
            MainStageService.changeScene(busScene);
        } catch(NoSuchFieldException ex){
            ex.printStackTrace();
        }
    }

    public static void loadFoods() throws IOException {
        foodLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/foodWindow.fxml"));
        foodScene = sceneInit(foodLoader, "/style/foodWindow.css");
        try{
            MainStageService.changeScene(foodScene);
        } catch(NoSuchFieldException ex){
            ex.printStackTrace();
        }
    }

    public static void loadCanteen() throws IOException {
        canteenLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/canteenWindow.fxml"));
        canteenScene = sceneInit(canteenLoader, "/style/canteenWindow.css");
        try{
            MainStageService.changeScene(canteenScene);
        } catch(NoSuchFieldException ex){
            ex.printStackTrace();
        }
    }

    private static Scene sceneInit(FXMLLoader loader, String cssPath) {
        Scene scene;
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
        Label label = new Label("fxml/css not loaded");
        pane.getChildren().add(label);
        return new Scene(pane, 200, 200);
    }
}
