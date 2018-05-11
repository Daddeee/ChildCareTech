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
        List<String> names;

        mealsDataTable.setRowFactory(tempMealDataTableView -> {
            final TableRow<TempMealData> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();


            final MenuItem addMenuItem = new MenuItem("Aggiungi Menu");
            addMenuItem.setOnAction(event -> {
                try{
                    AccessorSceneManager.loadAddMenu(row.getItem().getMealDTO());
                } catch (IOException e){
                    e.printStackTrace();
                }
            });

            contextMenu.getItems().add(addMenuItem);

            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                            .then((ContextMenu) null)
                            .otherwise(contextMenu)
            );

            return row;
        });

        try{
            names = SessionService.getSession().getAllCanteenNames();

            selectCanteen.getItems().clear();
            selectCanteen.getItems().addAll(names);
            if(names.size() > 0){
                selectCanteen.getSelectionModel().select(0);
                changeSelectedCanteen(null);
            }
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
               m.getStatus().toString(),
               m
            ));
        }

        mealsDataTable.getItems().clear();
        mealsDataTable.getItems().addAll(items);
    }
}
