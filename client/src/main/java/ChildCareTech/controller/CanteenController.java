package ChildCareTech.controller;

import ChildCareTech.common.DTO.CanteenDTO;
import ChildCareTech.common.DTO.MealDTO;
import ChildCareTech.services.AccessorSceneManager;
import ChildCareTech.services.MainSceneManager;
import ChildCareTech.services.SessionService;
import ChildCareTech.utils.TempMealData;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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

        mealsDataTable.setRowFactory(tempMealDataTableView -> {
            final TableRow<TempMealData> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();

            //TODO menu...

            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                            .then((ContextMenu) null)
                            .otherwise(contextMenu)
            );

            return row;
        });
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
               m.getStatus().toString(),
               m
            ));
        }

        mealsDataTable.getItems().clear();
        mealsDataTable.getItems().addAll(items);
    }
}
