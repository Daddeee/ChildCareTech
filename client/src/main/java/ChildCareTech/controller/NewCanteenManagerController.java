package ChildCareTech.controller;

import ChildCareTech.Client;
import ChildCareTech.common.DTO.CanteenDTO;
import ChildCareTech.common.DTO.MealDTO;
import ChildCareTech.services.AccessorWindowService;
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
    protected ComboBox<String> selectCanteen;
    @FXML
    protected TableView<TempMealData> mealsDataTable;

    private ObservableList<TempMealData> items = FXCollections.observableArrayList();
    private CanteenDTO canteenDTO;
    private AccessorWindowService accessorWindowService;

    @FXML
    public void initialize() {
        refreshTable();
        initMenu();
        mealsDataTable.setItems(items);
        accessorWindowService = new AccessorWindowService(this);
        accessorWindowService.setOnCloseAction(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                refreshTable();
            }
        });
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
            accessorWindowService.loadCanteenListWindow();
        } catch(IOException ex) {
            System.err.println("can't load canteenList window");
            ex.printStackTrace();
        }
    }
    @FXML
    public void foodManagerButtonAction(ActionEvent event) {
        try {
            accessorWindowService.loadKitchenWindow();
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

        mealsDataTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                menuManagerButton.setDisable(false);
            }
            else {
                menuManagerButton.setDisable(true);
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
        } catch (RemoteException | NoSuchElementException ex){
            ex.printStackTrace();
        }
        refreshMealsTable();
    }
}
