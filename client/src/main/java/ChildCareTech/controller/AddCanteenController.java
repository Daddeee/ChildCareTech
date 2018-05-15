package ChildCareTech.controller;

import ChildCareTech.common.DTO.CanteenDTO;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.services.AccessorStageService;
import ChildCareTech.services.MainSceneManager;
import ChildCareTech.services.SessionService;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.rmi.RemoteException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class AddCanteenController {
    @FXML
    protected TableView<MealData> mealsDataTable;
    @FXML
    protected TextField nameField;
    @FXML
    protected TextField entryTimeField;
    @FXML
    protected TextField exitTimeField;

    private ArrayList<MealData> mealsData = new ArrayList<>();

    public void initialize(){
        mealsDataTable.setRowFactory(mealDataTableView -> {
            TableRow<MealData> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();

            final MenuItem deleteMealData = new MenuItem("Elimina");
            deleteMealData.setOnAction(event -> {
                contextMenu.hide();
                mealsData.remove(row.getItem());
                refreshTable();
            });
            contextMenu.getItems().add(deleteMealData);

            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                            .then((ContextMenu) null)
                            .otherwise(contextMenu)
            );

            return row;
        });
    }

    @FXML
    protected void saveButtonAction(ActionEvent event){
        CanteenDTO canteenDTO = new CanteenDTO(0, nameField.getText(), null);
        List<LocalTime> entryTimeList = new ArrayList<>();
        List<LocalTime> exitTimeList = new ArrayList<>();

        for(MealData m : mealsData){
            entryTimeList.add(m.entryTimeParsed);
            exitTimeList.add(m.exitTimeParsed);
        }

        entryTimeList.sort(LocalTime::compareTo);
        exitTimeList.sort(LocalTime::compareTo);

        try{
            SessionService.getSession().saveCanteen(canteenDTO, entryTimeList, exitTimeList);
        } catch(RemoteException | AddFailedException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return;
        }

        try {
            AccessorStageService.close();
            MainSceneManager.loadCanteen();
            SessionService.getSession().triggerDailyScheduling();
        } catch (NoSuchFieldException ex) {
            System.err.println("Stage not initialized");
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    protected void addMealButtonAction(ActionEvent event){
        try{
            MealData mealData = new MealData(entryTimeField.getText(), exitTimeField.getText());
            mealData.entryTimeParsed = LocalTime.parse(entryTimeField.getCharacters());
            mealData.exitTimeParsed = LocalTime.parse(exitTimeField.getCharacters());

            mealsData.add(mealData);
            refreshTable();
        } catch (DateTimeParseException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void refreshTable() {
        mealsDataTable.getItems().clear();
        mealsDataTable.getItems().addAll(mealsData);
    }

    public static class MealData {
        private String entryTime;
        private String exitTime;

        public LocalTime entryTimeParsed;
        public LocalTime exitTimeParsed;

        public MealData() {}
        public MealData(String entryTime, String exitTime){
            this.entryTime = entryTime;
            this.exitTime = exitTime;
        }

        public String getEntryTime() {
            return entryTime;
        }

        public void setEntryTime(String entryTime) {
            this.entryTime = entryTime;
        }

        public String getExitTime() {
            return exitTime;
        }

        public void setExitTime(String exitTime) {
            this.exitTime = exitTime;
        }
    }
}
