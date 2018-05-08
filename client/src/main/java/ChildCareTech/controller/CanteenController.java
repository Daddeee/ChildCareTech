package ChildCareTech.controller;

import ChildCareTech.common.DTO.CanteenDTO;
import ChildCareTech.common.DTO.MealDTO;
import ChildCareTech.services.AccessorSceneManager;
import ChildCareTech.services.MainSceneManager;
import ChildCareTech.services.SessionService;
import ChildCareTech.utils.TempMealData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class CanteenController {
    @FXML
    protected ComboBox<String> selectCanteen;
    @FXML
    protected TableView<TempMealData> mealsDataTable;

    private CanteenDTO canteenDTO;

    public void initialize() {
        try{
            selectCanteen.getItems().clear();
            selectCanteen.getItems().addAll(SessionService.getSession().getAllCanteenNames());
        } catch (RemoteException e){
            e.printStackTrace();
        }
    }

    @FXML
    protected void changeSelectedCanteen(ActionEvent event){
        try{
            canteenDTO = SessionService.getSession().getCanteenByName(selectCanteen.getValue());
        } catch (RemoteException | NoSuchElementException ex){
            ex.printStackTrace();
        }

        refresh();
    }

    @FXML
    protected void addButtonAction(ActionEvent event) {
        try{
            AccessorSceneManager.loadAddCanteen();
        } catch (IOException ex){
            System.err.println("Can't load addKid window");
            ex.printStackTrace();
        }
    }

    @FXML
    protected void backButtonAction(ActionEvent event) {
        try {
            MainSceneManager.loadHome();
        } catch (IOException ex) {
            System.err.println("Can't load addKid window");
            ex.printStackTrace();
        }
    }

    private void refresh(){
        List<TempMealData> items = new ArrayList<>();

        for(MealDTO m : canteenDTO.getMeals()){
            items.add(new TempMealData(
               m.getWorkDay().getDate().toString(),
               m.getEntryEvent().getBeginTime().toString(),
               m.getExitEvent().getEndTime().toString(),
               m
            ));
        }

        mealsDataTable.getItems().clear();
        mealsDataTable.getItems().addAll(items);
    }
}
