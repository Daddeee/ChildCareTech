package ChildCareTech.services;

import ChildCareTech.common.DTO.*;
import ChildCareTech.controller.*;
import ChildCareTech.services.ObservableDTOs.*;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

/**
 * This class provides a window management service for pop-up or value input oriented windows.
 */
public class AccessorWindowService {

    protected TableWindowControllerInterface tableWindowControllerInterface;
    protected Stage stage;
    protected FXMLLoader loader;

    /**
     * This constructor creates a new stage,  initialize its modality to APPLICATION_MODAL and locks its dimensions.
     * @param tableWindowControllerInterface Should be the controller of a window that need to show pop-ups or value
     *                                       window with the instance of this class.
     */
    public AccessorWindowService(TableWindowControllerInterface tableWindowControllerInterface) {
        this.tableWindowControllerInterface = tableWindowControllerInterface;
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
    }

    /**
     * Sets the modality of the window to non blocking for the others application's windows.
     */
    public void setNonLockingModality() {
        stage.initModality(Modality.NONE);
    }
    /**
     *Calls the refreshTable method of the TableWindowControllerInterface controller passed as parameter in the constructor.
     */
    public void refreshTable() {
        tableWindowControllerInterface.refreshTable();
    }

    /**
     * Closes the accessor window service instance's stage.
     */
    public void close() {
        stage.close();
    }
    public void show() {
        stage.show();
    }
    public void hide() {
        stage.hide();
    }
    public void setTitle(String string) { stage.setTitle(string); }
    public void setResizable(boolean bool) { stage.setResizable(bool); }
    public double getHeight() {
        return stage.getHeight();
    }
    public double getWidth() {
        return stage.getWidth();
    }

    /**
     * Sets a method that will be called by the closing of the stage.
     * @param eventEventHandler the eventHandler that contains the method.
     */
    public void setOnCloseAction(EventHandler<WindowEvent> eventEventHandler) {
        stage.setOnCloseRequest(eventEventHandler);
    }
    public Stage getStage() {
        return this.stage;
    }

    public void loadRegisterUserWindow() throws IOException{
        loadWindow(ResourcesPaths.getRegisterUserFXMLPath(), ResourcesPaths.getRegisterUserCSSPath());
        ((AccessorWindowController)loader.getController()).setAccessorWindowService(this);
    }
    public void loadAddKidWindow() throws IOException{
        loadWindow(ResourcesPaths.getAddKidFXMLPath(), ResourcesPaths.getAddKidCSSPath());
    }
    public void loadEditKidWindow(ObservableKid observableKid) throws IOException{
        loadWindow(ResourcesPaths.getEditKidFXMLPath(), ResourcesPaths.getEditKidCSSPath());
        ((EditKidController) loader.getController()).initData(observableKid);
    }
    public void loadEditKidContactsWindow(ObservableKid observableKid) throws IOException{
        loadWindow(ResourcesPaths.getEditKidContactsFXMLPath(), ResourcesPaths.getEditKidContactsCSSPath());
        ((EditKidContactsController)loader.getController()).initContacts(observableKid.getDTO());
    }
    public void loadEditKidAllergiesWindow(ObservableKid observableKid) throws IOException{
        loadWindow(ResourcesPaths.getEditKidAllergiesFXMLPath(), ResourcesPaths.getEditKidAllergiesCSSPath());
        ((EditKidAllergiesController)loader.getController()).initAllergies(observableKid.getDTO());
    }
    public void loadShowKidWindow(ObservableKid observableKid) throws IOException{
        loadWindow(ResourcesPaths.getShowKidFXMLPath(), ResourcesPaths.getShowKidCSSPath());
        ((ShowKidController)loader.getController()).initData(observableKid);
    }
    public void loadAddAdultWindow() throws IOException {
        loadWindow(ResourcesPaths.getAddAdultFXMLPath(), ResourcesPaths.getAddAdultCSSPath());
    }
    public void loadEditAdultWindow(ObservablePersonInterface observablePersonInterface) throws IOException {
        loadWindow(ResourcesPaths.getEditAdultFXMLPath(), ResourcesPaths.getEditAdultCSSPath());
        ((EditAdultController) loader.getController()).initData(observablePersonInterface);
    }
    public void loadShowAdultWindow(ObservableAdult observableAdult) throws IOException{
        loadWindow(ResourcesPaths.getShowAdultFXMLPath(), ResourcesPaths.getShowAdultCSSPath());
        ((ShowAdultController)loader.getController()).initData(observableAdult);
    }
    public void loadShowPediatristWindow(ObservablePediatrist observablePediatrist) throws IOException{
        loadWindow(ResourcesPaths.getShowPediatristFXMLPath(), ResourcesPaths.getShowPediatristCSSPath());
        ((ShowPediatristController)loader.getController()).initData(observablePediatrist);
    }
    public void loadShowStaffMember(ObservableStaff observableStaff) throws IOException{
        loadWindow(ResourcesPaths.getShowStaffMemberFXMLPath(), ResourcesPaths.getShowStaffMemberCSSPath());
        ((ShowStaffMemberController)loader.getController()).initData(observableStaff);
    }
    public void loadShowSupplierWindow(ObservableSupplier observableSupplier) throws IOException{
        loadWindow(ResourcesPaths.getShowSupplierFXMLPath(), ResourcesPaths.getShowSupplierCSSPath());
        ((ShowSupplierController)loader.getController()).initData(observableSupplier);
    }
    public void loadBusListWindow() throws IOException{
        loadWindow(ResourcesPaths.getBusListFXMLPath(), ResourcesPaths.getBusListCSSPath());
        ActiveControllersList.addBusController(loader.getController());
    }
    public void loadCanteenListWindow() throws IOException{
        loadWindow(ResourcesPaths.getCanteenesListFXMLPath(), ResourcesPaths.getCanteenesListCSSPath());
        ActiveControllersList.addCanteenControllerList(loader.getController());
    }
    public void loadAddMealsWindow(String canteenName) throws IOException{
        loadWindow(ResourcesPaths.getAddMealFXMLPath(), ResourcesPaths.getAddMealCSSPath());
        ((NewAddMealsController)loader.getController()).setCanteenName(canteenName);
    }
    public void loadFoodsListWindow() throws IOException{
        loadWindow(ResourcesPaths.getFoodsListFXMLPath(), ResourcesPaths.getFoodsListCSSPath());
        ActiveControllersList.addFoodController(loader.getController());
    }
    public void loadUserAccountWindow(String userName) throws IOException{
        loadWindow(ResourcesPaths.getUserAccountFXMLPath(), ResourcesPaths.getUserAccountCSSPath());
        ((AccessorWindowController)loader.getController()).setAccessorWindowService(this);
        ((UserAccountController)loader.getController()).setUserName(userName);
    }
    public void loadAddTripWindow() throws IOException {
        loadWindow(ResourcesPaths.getAddTripFXMLPath(), ResourcesPaths.getAddTripCSSPath());
    }
    public void loadShowTripWindow(TripDTO tripDTO) throws IOException{
        loadWindow(ResourcesPaths.getShowTripFXMLPath(), ResourcesPaths.getShowTripCSSPath());
        ((ShowTripController)loader.getController()).initData(tripDTO);
    }
    public void loadUpdateTripWindow(TripDTO tripDTO) throws IOException{
        loadWindow(ResourcesPaths.getUpdateTripFXMLPath(), ResourcesPaths.getUpdateTripCSSPath());
        ((UpdateTripController)loader.getController()).initData(tripDTO);
    }
    public void loadTripPartecipationWindow(TripDTO tripDTO) throws IOException{
        loadWindow(ResourcesPaths.getTripPartecipationFXMLPath(), ResourcesPaths.getTripPartecipationCSSPath());
        ((TripPartecipationController)loader.getController()).initData(tripDTO);
    }
    public void loadTripRoutesWindow(TripDTO tripDTO) throws IOException{
        loadWindow(ResourcesPaths.getTripRoutesFXMLPath(), ResourcesPaths.getTripRoutesCSSPath());
        ((TripRoutesController)loader.getController()).initData(tripDTO);
    }
    public void loadEventReportWindow(EventDTO eventDTO) throws IOException{
        loadWindow(ResourcesPaths.getEventReportFXMLPath(), ResourcesPaths.getEventReportCSSPath());
        ((EventReportController)loader.getController()).initData(eventDTO);
        ActiveControllersList.addEventReportController(loader.getController());
    }
    public void loadCodeInputWindow(EventDTO eventDTO) throws IOException{
        loadWindow(ResourcesPaths.getCodeInputFXMLPath(), ResourcesPaths.getCodeInputCSSPath());
        ((CodeInputWindowController)loader.getController()).initData(eventDTO);
        ActiveControllersList.addCodeInputWindowController(loader.getController());
    }
    public void loadKitchenWindow() throws IOException{
        loadWindow(ResourcesPaths.getKitchenFXMLPath(), ResourcesPaths.getKitchenCSSPath());
        ActiveControllersList.addDishController(loader.getController());
    }
    public void loadShowDishWindow(DishDTO dishDTO) throws IOException{
        loadWindow(ResourcesPaths.getShowDishFXMLPath(), ResourcesPaths.getShowDishCSSPath());
        ((ShowDishController)loader.getController()).initData(dishDTO);
    }
    public void loadUpdateDishWindow(DishDTO dishDTO) throws IOException{
        loadWindow(ResourcesPaths.getUpdateDishFXMLPath(), ResourcesPaths.getUpdateDishCSSPath());
        ((UpdateDishController)loader.getController()).initData(dishDTO);
    }
    public void loadAddDishWindow() throws IOException{
        loadWindow(ResourcesPaths.getAddDishFXMLPath(), ResourcesPaths.getAddDishCSSPath());
    }
    public void loadAddMenuWindow(MealDTO mealDTO) throws IOException{
        loadWindow(ResourcesPaths.getAddMenuFXMLPath(), ResourcesPaths.getAddMenuCSSPath());
        ((AddMenuController)loader.getController()).initData(mealDTO);
    }
    public void loadTripPresenceRegistrationWindow(TripDTO tripDTO, RouteDTO routeDTO) throws IOException{
        loadWindow(ResourcesPaths.getTripPresenceRegistrationFXMLPath(), ResourcesPaths.getTripPresenceRegistrationCSSPath());
        ((TripPresenceRegistrationController)loader.getController()).initData(tripDTO, routeDTO);
        ActiveControllersList.addPresenceRegistrationControllersList(loader.getController());
    }

    /**
     * Loads the scene from relatives fxml's and css' file paths, stores it in the stage and shows the stage.
     * @param fxmlPath is the fxml's file path.
     * @param cssPath is the css' file path.
     * @throws IOException in case of incorrect fxml's or css' file path.
     */
    protected void loadWindow(String fxmlPath, String cssPath) throws IOException{
        loader = new FXMLLoader(AccessorWindowService.class.getResource(fxmlPath));
        try {
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(cssPath);
            stage.setScene(scene);
            ((AccessorWindowController) loader.getController()).setAccessorWindowService(this);
        } catch(ClassCastException ex) {
            System.err.println("fatal, loading a not accessor window as accessor");
            ex.printStackTrace();
        } catch(LoadException ex) {
            System.err.println("error loading fxml file");
            ex.printStackTrace();
        }
        stage.show();
    }

}
