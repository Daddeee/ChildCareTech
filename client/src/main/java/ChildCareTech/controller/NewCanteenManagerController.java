package ChildCareTech.controller;

import ChildCareTech.Client;
import ChildCareTech.common.DTO.CanteenDTO;
import ChildCareTech.common.DTO.MealDTO;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.services.AccessorWindowService;
import ChildCareTech.services.AlertWindowService;
import ChildCareTech.utils.TempMealData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.NoSuchElementException;

public class NewCanteenManagerController implements TableWindowControllerInterface {

    @FXML
    private Button menuManagerButton;
    @FXML
    private Button canteenManagerButton;
    @FXML
    private Button foodManagerButton;
    @FXML
    private Button validateMenuButton;
    @FXML
    protected ComboBox<String> selectCanteen;
    @FXML
    protected TableView<TempMealData> mealsDataTable;

    private ObservableList<TempMealData> items = FXCollections.observableArrayList();
    private CanteenDTO canteenDTO;
    private AccessorWindowService accessorWindowService;
    private AccessorWindowService canteenListWindow;
    private AccessorWindowService kitchenWindow;
    private AlertWindowService alertWindowService;

    @FXML
    public void initialize() {
        refreshTable();
        initMenu();
        mealsDataTable.setItems(items);
        mealsDataTable.getSortOrder().add(mealsDataTable.getColumns().get(0));
        accessorWindowService = new AccessorWindowService(this);
        accessorWindowService.setOnCloseAction(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                refreshTable();
            }
        });
        canteenListWindow = new AccessorWindowService(this);
        kitchenWindow = new AccessorWindowService(this);
        alertWindowService = new AlertWindowService();
    }
    @FXML
    protected void validateMenuAction(ActionEvent event){
        TempMealData selected = mealsDataTable.getSelectionModel().getSelectedItem();
        if(selected == null || selected.getMealDTO() == null) return;
        if(selected.getMealDTO().getMenu() == null) {
            alertWindowService.loadWindow("Il pasto selezionato non ha menù registrati.");
            return;
        }
        try{
            Client.getSessionService().getSession().validateMenu(selected.getMealDTO().getMenu());
            alertWindowService.loadWindow("OK!");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (UpdateFailedException e){
            alertWindowService.loadWindow(e.getMessage());
        }
    }
    @FXML
    public void menuManagerButtonAction(ActionEvent event) {
        TempMealData selected = mealsDataTable.getSelectionModel().getSelectedItem();
        if(selected == null || selected.getMealDTO() == null) return;
        try {
            accessorWindowService.loadAddMenuWindow(selected.getMealDTO());
        } catch(IOException ex) {
            System.err.println("can't load menu manager window");
            ex.printStackTrace();
        }
    }
    @FXML
    public void canteenManagerButtonAction(ActionEvent event) {
        try {
            canteenListWindow.loadCanteenListWindow();
        } catch(IOException ex) {
            System.err.println("can't load canteenList window");
            ex.printStackTrace();
        }
    }
    @FXML
    public void foodManagerButtonAction(ActionEvent event) {
        try {
            kitchenWindow.loadKitchenWindow();
        } catch(IOException ex) {
            System.err.println("can't load kitchen window");
            ex.printStackTrace();
        }
    }
    @FXML
    public void selectCanteenAction(ActionEvent event) {
        changeSelectedCanteen();
    }

    public void refreshMealsTable() {
        items.clear();
        if(canteenDTO != null) {
            for (MealDTO m : canteenDTO.getMeals())
                items.add(new TempMealData(m));
        }
        else
            items.clear();
    }

    public void initMenu() {
        menuManagerButton.setDisable(true);
        validateMenuButton.setDisable(true);

        mealsDataTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                menuManagerButton.setDisable(false);
                validateMenuButton.setDisable(false);
            }
            else {
                menuManagerButton.setDisable(true);
                validateMenuButton.setDisable(true);
            }
        });

        if(selectCanteen.getItems().size() > 0){
            selectCanteen.setValue(selectCanteen.getItems().get(0));
            changeSelectedCanteen();
        }
    }
    public void refreshTable() {
        List<String> names;
        String prevName = null;
        try{
            names = Client.getSessionService().getSession().getAllCanteenNames();
            if(names.contains(selectCanteen.getSelectionModel().getSelectedItem()))
                prevName = selectCanteen.getSelectionModel().getSelectedItem();
            selectCanteen.getItems().clear();
            selectCanteen.getItems().addAll(names);
            if(prevName != null) {
                selectCanteen.setValue(prevName);
            } else {
                if(selectCanteen.getItems().size() > 0)
                    selectCanteen.setValue(selectCanteen.getItems().get(0));
            }

        } catch (RemoteException e){
            e.printStackTrace();
        }
    }

    protected void changeSelectedCanteen(){
        try{
            if(selectCanteen.getValue() != null)
                canteenDTO = Client.getSessionService().getSession().getCanteenByName(selectCanteen.getValue());
        } catch (RemoteException ex){
            ex.printStackTrace();
        } catch(NoSuchElementException ex) {
            alertWindowService.loadWindow(ex.getMessage());
            ex.printStackTrace();
        }

        refreshMealsTable();
    }
    public void notifyUpdate() { }
}
