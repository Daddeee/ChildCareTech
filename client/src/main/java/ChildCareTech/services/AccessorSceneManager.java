package ChildCareTech.services;

import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.controller.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AccessorSceneManager {

    private static FXMLLoader addKidLoader;
    private static FXMLLoader addAdultLoader;
    private static FXMLLoader selectAdultKindLoader;
    private static FXMLLoader registerUserLoader;
    private static FXMLLoader addTripLoader;
    private static FXMLLoader showTripLoader;
    private static FXMLLoader updateTripLoader;

    private static Scene addKidScene;
    private static Scene addAdultScene;
    private static Scene selectAdultKindScene;
    private static Scene registerUserScene;
    private static Scene addTripScene;
    private static Scene showTripScene;
    private static Scene updateTripScene;


    private AccessorSceneManager() {
    }

    public static AddKidController getAddKidController() {
        return addKidLoader.getController();
    }
    public static AddAdultController getAddAdultController() {
        return addAdultLoader.getController();
    }
    public static SelectAdultKindController getSelectAdultController() {
        return selectAdultKindLoader.getController();
    }
    public static AddTripController getAddTripController() {
        return addTripLoader.getController();
    }
    public static ShowTripController getShowTripControllet() {
        return showTripLoader.getController();
    }
    public static UpdateTripController getUpdateTripController() {
        return updateTripLoader.getController();
    }

    public static void loadAddKid() throws IOException {
        addKidLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/addKidWindow.fxml"));
        addKidScene = sceneInit(addKidLoader, "/style/addKidWindow.css");
        try {
            AccessorStageService.changeScene(addKidScene);
        } catch (NoSuchFieldException ex) {
            AccessorSceneManager.stageError(ex);
        }
    }

    public static void loadAddAdult() throws IOException {
        addAdultLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/addAdultWindow.fxml"));
        addAdultScene = sceneInit(addAdultLoader, "/style/addAdultWindow.fxml");
        try {
            AccessorStageService.changeScene(addAdultScene);
        } catch (NoSuchFieldException ex) {
            AccessorSceneManager.stageError(ex);
        }
    }

    public static void loadSelectAdultKind() throws  IOException {
        selectAdultKindLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/selectAdultKindWindow.fxml"));
        selectAdultKindScene = sceneInit(selectAdultKindLoader, "/style/selectAdultKindWindow.css");
        try {
            AccessorStageService.changeScene(selectAdultKindScene);
        } catch (NoSuchFieldException ex) {
            AccessorSceneManager.stageError(ex);
        }
    }

    public static void loadRegisterUser() throws IOException {
        registerUserLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/registerUserWindow.fxml"));
        registerUserScene = sceneInit(registerUserLoader, "style/registerUserWindow.css");
        try {
            AccessorStageService.changeScene(registerUserScene);
        } catch (NoSuchFieldException ex) {
            AccessorSceneManager.stageError(ex);
        }
    }

    public static void loadAddTrip() throws IOException {
        addTripLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/addTripWindow.fxml"));
        addTripScene = sceneInit(addTripLoader, "/style/addTripWindow.css");
        try {
            AccessorStageService.changeScene(addTripScene);
        } catch (NoSuchFieldException ex) {
            AccessorSceneManager.stageError(ex);
        }
    }

    public static void loadShowTrip(TripDTO row) throws IOException {
        showTripLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/showTripWindow.fxml"));
        showTripScene = sceneInit(showTripLoader, "/style/showTripWindow.css");

        ShowTripController controller = showTripLoader.getController();
        controller.initData(row);

        try {
            AccessorStageService.changeScene(showTripScene);
        } catch (NoSuchFieldException ex) {
            AccessorSceneManager.stageError(ex);
        }
    }

    public static void loadUpdateTrip(TripDTO row) throws IOException {
        updateTripLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/updateTripWindow.fxml"));
        updateTripScene = sceneInit(updateTripLoader, "/style/updateTripWindow.css");

        UpdateTripController controller = updateTripLoader.getController();
        controller.initData(row);

        try{
            AccessorStageService.changeScene(updateTripScene);
        } catch (NoSuchFieldException ex) {
            AccessorSceneManager.stageError(ex);
        }
    }

    private static void stageError(NoSuchFieldException ex) {
        System.err.println("accessory stage not initialized");
        ex.printStackTrace();
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
