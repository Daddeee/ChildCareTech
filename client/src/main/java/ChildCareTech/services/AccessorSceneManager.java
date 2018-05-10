package ChildCareTech.services;

import ChildCareTech.common.DTO.*;
import ChildCareTech.controller.*;
import ChildCareTech.services.ObservableDTOs.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AccessorSceneManager {

    private static FXMLLoader addKidLoader;
    private static FXMLLoader addAdultLoader;
    private static FXMLLoader addPediatristLoader;
    private static FXMLLoader addStaffLoader;
    private static FXMLLoader addSupplierLoader;
    private static FXMLLoader selectAdultKindLoader;
    private static FXMLLoader registerUserLoader;
    private static FXMLLoader addTripLoader;
    private static FXMLLoader showKidLoader;
    private static FXMLLoader showTripLoader;
    private static FXMLLoader updateTripLoader;
    private static FXMLLoader addBusLoader;
    private static FXMLLoader showBusLoader;
    private static FXMLLoader updateBusLoader;
    private static FXMLLoader codeInputLoader;
    private static FXMLLoader eventReportLoader;
    private static FXMLLoader tripPartecipationanagementLoader;
    private static FXMLLoader editKidLoader;
    private static FXMLLoader tripRoutesLoader;
    private static FXMLLoader addFoodLoader;
    private static FXMLLoader updateFoodLoader;
    private static FXMLLoader allergiesLoader;
    private static FXMLLoader addCanteenLoader;
    private static FXMLLoader addDishLoader;
    private static FXMLLoader updateDishLoader;

    private static Scene addKidScene;
    private static Scene addAdultScene;
    private static Scene addPediatristScene;
    private static Scene addStaffScene;
    private static Scene addSupplierScene;
    private static Scene selectAdultKindScene;
    private static Scene registerUserScene;
    private static Scene addTripScene;
    private static Scene showKidScene;
    private static Scene showTripScene;
    private static Scene updateTripScene;
    private static Scene addBusScene;
    private static Scene showBusScene;
    private static Scene updateBusScene;
    private static Scene codeInputScene;
    private static Scene eventReportScene;
    private static Scene tripPartecipationanagementScene;
    private static Scene editKidScene;
    private static Scene tripRoutesScene;
    private static Scene addFoodScene;
    private static Scene updateFoodScene;
    private static Scene allergiesScene;
    private static Scene addCanteenScene;
    private static Scene addDishScene;
    private static Scene updateDishScene;

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
    public static ShowKidController getShowKidController() { return showKidLoader.getController(); }

    public static void loadTripRoutes(TripDTO tripDTO) throws IOException {
        tripRoutesLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/tripRoutesWindow.fxml"));
        tripRoutesScene = sceneInit(tripRoutesLoader, "/style/tripRoutesWindow.css");

        TripRoutesController controller = tripRoutesLoader.getController();
        controller.initData(tripDTO);

        try{
            AccessorStageService.changeScene(tripRoutesScene);
        } catch (NoSuchFieldException ex){
            AccessorSceneManager.stageError(ex);
        }
    }

    public static void loadCodeInput(EventDTO eventDTO) throws IOException {
        codeInputLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/codeInputWindow.fxml"));
        codeInputScene = sceneInit(codeInputLoader, "/style/codeInputWindow.css");

        CodeInputWindowController controller = codeInputLoader.getController();
        controller.initData(eventDTO);

        try {
            AccessorStageService.changeScene(codeInputScene);
        } catch (NoSuchFieldException ex) {
            AccessorSceneManager.stageError(ex);
        }
    }

    public static void loadTripPartecipationManagement(TripDTO tripDTO) throws IOException{
        tripPartecipationanagementLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/tripPartecipationWindow.fxml"));
        tripPartecipationanagementScene = sceneInit(tripPartecipationanagementLoader, "/style/tripPartecipationWindow.css");

        TripPartecipationController controller = tripPartecipationanagementLoader.getController();
        controller.initData(tripDTO);

        try{
            AccessorStageService.changeScene(tripPartecipationanagementScene);
        } catch (NoSuchFieldException ex) {
            AccessorSceneManager.stageError(ex);
        }
    }

    public static void loadEventReport(EventDTO eventDTO) throws IOException {
        eventReportLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/eventReportWindow.fxml"));
        eventReportScene = sceneInit(eventReportLoader, "/style/eventReportWindow.css");

        EventReportController controller = eventReportLoader.getController();
        controller.initData(eventDTO);

        try {
            AccessorStageService.changeScene(eventReportScene);
        } catch (NoSuchFieldException ex) {
            AccessorSceneManager.stageError(ex);
        }
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
        addAdultScene = sceneInit(addAdultLoader, "/style/addAdultWindow.css");
        try {
            AccessorStageService.changeScene(addAdultScene);
        } catch (NoSuchFieldException ex) {
            AccessorSceneManager.stageError(ex);
        }
    }

    public static void loadEditKid(ObservableKid observableKid) throws IOException {
        EditKidController editKidController;
        editKidLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/editKidWindow.fxml"));
        editKidScene = sceneInit(editKidLoader, "/style/editKidWindow.css");
        try {
            AccessorStageService.changeScene(editKidScene);
            editKidController = editKidLoader.getController();
            editKidController.initData(observableKid);
        } catch(NoSuchFieldException ex) {
            AccessorSceneManager.stageError(ex);
        }
    }

    public static void loadAddPediatrist() throws IOException {
        addPediatristLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/addPediatristWindow.fxml"));
        addPediatristScene = sceneInit(addPediatristLoader, "/style/addPediatristWindow.css");
        try {
            AccessorStageService.changeScene(addPediatristScene);
        } catch (NoSuchFieldException ex) {
            AccessorSceneManager.stageError(ex);
        }
    }

    public static void loadAddSupplier() throws IOException {
        addSupplierLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/addSupplierWindow.fxml"));
        addSupplierScene = sceneInit(addSupplierLoader, "/style/addSupplierWindow.css");
        try {
            AccessorStageService.changeScene(addSupplierScene);
        } catch (NoSuchFieldException ex) {
            AccessorSceneManager.stageError(ex);
        }
    }

    public static void loadShowKid(ObservableKid observableKid) throws  IOException {
        showKidLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/showKidWindow.fxml"));
        showKidScene = sceneInit(showKidLoader, "/style/showKidWindow.css");
        try {
            AccessorStageService.changeScene(showKidScene);
            getShowKidController().initData(observableKid);
        } catch (NoSuchFieldException ex) {
            AccessorSceneManager.stageError(ex);
        }
    }

    public static void loadShowAdult(ObservableAdult observableAdult) throws IOException {
        System.out.println("show adult");
    }

    public static void loadShowPediatrist(ObservablePediatrist observablePediatrist) throws IOException {
        System.out.println("show pediatrist");
    }

    public static void loadShowStaffMember(ObservableStaff observableStaff) throws IOException {
        System.out.println("show staff member");
    }

    public static void loadShowSupplier(ObservableSupplier observableSupplier) throws IOException {
        System.out.println("show supplier");
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

    public static void loadAddFood() throws IOException {
        addFoodLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/addFoodWindow.fxml"));
        addFoodScene = sceneInit(addFoodLoader, "/style/addFoodWindow.css");

        try{
            AccessorStageService.changeScene(addFoodScene);
        } catch (NoSuchFieldException ex) {
            AccessorSceneManager.stageError(ex);
        }
    }

    public static void loadAddDish() throws IOException {
        addDishLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/addDishWindow.fxml"));
        addDishScene = sceneInit(addDishLoader, "/style/addDishWindow.css");

        try{
            AccessorStageService.changeScene(addDishScene);
        } catch (NoSuchFieldException ex) {
            AccessorSceneManager.stageError(ex);
        }
    }

    public static void loadUpdateDish(DishDTO dishDTO) throws IOException {
        updateDishLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/updateDishWindow.fxml"));
        updateDishScene = sceneInit(updateDishLoader, "/style/updateDishWindow.css");

        UpdateDishController controller = updateDishLoader.getController();
        controller.initData(dishDTO);

        try{
            AccessorStageService.changeScene(updateDishScene);
        } catch (NoSuchFieldException ex) {
            AccessorSceneManager.stageError(ex);
        }
    }

    public static void loadUpdateFood(FoodDTO foodDTO) throws IOException {
        updateFoodLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/updateFoodWindow.fxml"));
        updateFoodScene = sceneInit(updateFoodLoader, "/style/updateFoodWindow.css");

        UpdateFoodController controller = updateFoodLoader.getController();
        controller.initData(foodDTO);

        try{
            AccessorStageService.changeScene(updateFoodScene);
        } catch(NoSuchFieldException ex){
            AccessorSceneManager.stageError(ex);
        }
    }

    public static void loadAllergies(PersonDTO personDTO) throws IOException {
        allergiesLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/allergiesWindow.fxml"));
        allergiesScene = sceneInit(allergiesLoader, "/style/allergiesWindow.css");

        AllergiesController controller = allergiesLoader.getController();
        controller.initData(personDTO);

        try{
            AccessorStageService.changeScene(allergiesScene);
        } catch(NoSuchFieldException ex){
            AccessorSceneManager.stageError(ex);
        }
    }

    public static void loadAddBus() throws IOException {
        addBusLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/addBusWindow.fxml"));
        addBusScene = sceneInit(addBusLoader, "/style/addBusWindow.css");

        try{
            AccessorStageService.changeScene(addBusScene);
        } catch (NoSuchFieldException ex) {
            AccessorSceneManager.stageError(ex);
        }
    }


    public static void loadShowBus(BusDTO row) throws IOException {
        showBusLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/showBusWindow.fxml"));
        showBusScene = sceneInit(showBusLoader, "/style/showBusWindow.css");

        ShowBusController controller = showBusLoader.getController();
        controller.initData(row);

        try {
            AccessorStageService.changeScene(showBusScene);
        } catch (NoSuchFieldException ex) {
            AccessorSceneManager.stageError(ex);
        }
    }

    public static void loadUpdateBus(BusDTO row) throws IOException {
        updateBusLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/updateBusWindow.fxml"));
        updateBusScene = sceneInit(updateBusLoader, "/style/updateBusWindow.css");

        UpdateBusController controller = updateBusLoader.getController();
        controller.initData(row);

        try{
            AccessorStageService.changeScene(updateBusScene);
        } catch (NoSuchFieldException ex) {
            AccessorSceneManager.stageError(ex);
        }
    }

    public static void loadAddCanteen() throws IOException {
        addCanteenLoader = new FXMLLoader(AccessorSceneManager.class.getResource("/view/addCanteenWindow.fxml"));
        addCanteenScene = sceneInit(addCanteenLoader, "/style/addCanteenWindow.css");

        try{
            AccessorStageService.changeScene(addCanteenScene);
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
