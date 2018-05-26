package ChildCareTech.controller;

import ChildCareTech.Client;
import ChildCareTech.common.DTO.CanteenDTO;
import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.services.AccessorWindowService;
import ChildCareTech.services.AlertWindowService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class NewAddMealsController implements AccessorWindowController {
    @FXML
    private TableView<MealData> mealsTable;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button saveButton;
    @FXML
    private ComboBox<String> inComboBoxH;
    @FXML
    private ComboBox<String> outComboBoxH;
    @FXML
    private ComboBox<String> inComboBoxM;
    @FXML
    private ComboBox<String> outComboBoxM;

    private ObservableList<String> minutes = FXCollections.observableArrayList();
    private ObservableList<String> hour = FXCollections.observableArrayList();
    private ObservableList<MealData> meals = FXCollections.observableArrayList();
    private AccessorWindowService accessorWindowService;
    private AlertWindowService alertWindowService;
    private String canteenName;

    @FXML
    private void initialize() {
        initComboBoxes();
        initMenu();
        mealsTable.setItems(meals);
        alertWindowService = new AlertWindowService();
    }
    @FXML
    private void addButtonAction(ActionEvent event) {
        LocalTime inizio;
        LocalTime fine;
        MealData mealData;
        if(inComboBoxH.getSelectionModel().getSelectedItem() != null &&
                inComboBoxM.getSelectionModel().getSelectedItem() != null &&
                outComboBoxH.getSelectionModel().getSelectedItem() != null &&
                outComboBoxM.getSelectionModel().getSelectedItem() != null) {
            inizio = LocalTime.of(Integer.parseInt(inComboBoxH.getSelectionModel().getSelectedItem()), Integer.parseInt(inComboBoxM.getSelectionModel().getSelectedItem()));
            fine = LocalTime.of(Integer.parseInt(outComboBoxH.getSelectionModel().getSelectedItem()), Integer.parseInt(outComboBoxM.getSelectionModel().getSelectedItem()));
            if(inizio.isAfter(fine)){
                return;
                //gestione errore
            }
            saveButton.setDisable(false);
            mealData = new MealData(inizio, fine);
            meals.add(mealData);
            inComboBoxH.setValue(null);
            inComboBoxM.setValue(null);
            outComboBoxH.setValue(null);
            outComboBoxM.setValue(null);
        }
    }
    @FXML
    private void deleteButtonAction(ActionEvent event) {
        meals.remove(mealsTable.getSelectionModel().getSelectedItem());
        if(meals.isEmpty())
            saveButton.setDisable(true);
    }
    @FXML
    private void saveButtonAction(ActionEvent event) {
        CanteenDTO canteenDTO = new CanteenDTO(0, canteenName, null);
        List<LocalTime> entryTimeList = new ArrayList<>();
        List<LocalTime> exitTimeList = new ArrayList<>();

        for(MealData m : meals){
            entryTimeList.add(m.entryTimeParsed);
            exitTimeList.add(m.exitTimeParsed);
        }

        try{
            Client.getSessionService().getSession().saveCanteen(canteenDTO, entryTimeList, exitTimeList);
        } catch(RemoteException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return;
        } catch(AddFailedException ex){
            alertWindowService.loadWindow(ex.getMessage());
            ex.printStackTrace();
            return;
        }

        accessorWindowService.close();
        accessorWindowService.refreshTable();


        try {
            Client.getSessionService().getSession().triggerDailyScheduling();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setAccessorWindowService(AccessorWindowService accessorWindowService) {
        this.accessorWindowService = accessorWindowService;
    }

    public void initMenu() {
        deleteButton.setDisable(true);
        saveButton.setDisable(true);

        mealsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                deleteButton.setDisable(false);
            }
            else {
                deleteButton.setDisable(true);
            }
        });
    }

    public void setCanteenName(String canteenName) {
        this.canteenName = canteenName;
    }

    public void initComboBoxes() {
        WorkDayDTO workDayDTO;
        LocalTime inizio;
        LocalTime fine;
        String number;
        minutes.clear();
        hour.clear();
        try {
            workDayDTO = Client.getSessionService().getSession().getWorkDay(LocalDate.now());
        } catch(RemoteException ex) {
            System.err.println("error reading today workday");
            ex.printStackTrace();
            return;
        }
        inizio = workDayDTO.getEntryTime();
        fine = workDayDTO.getExitTime();
        for(Integer i = 0; i < 60; i++) {
            if(i<10)
                number = "0" + i.toString();
            else
                number = i.toString();
            minutes.add(number);
        }
        if(inizio.getHour() > fine.getHour()) {
            LocalTime temp = inizio;
            inizio = fine;
            fine = temp;
        }
        for(Integer i = inizio.getHour(); i<fine.getHour(); i++) {
            if(i<10)
                number = "0" + i.toString();
            else
                number = i.toString();
            hour.add(number);
        }

        inComboBoxH.setItems(hour);
        outComboBoxH.setItems(hour);
        inComboBoxM.setItems(minutes);
        outComboBoxM.setItems(minutes);
    }

    public static class MealData {
        private String entryTime;
        private String exitTime;

        public LocalTime entryTimeParsed;
        public LocalTime exitTimeParsed;

        public MealData() {}

        public MealData(LocalTime entryTime, LocalTime exitTime){
            this.entryTimeParsed = entryTime;
            this.exitTimeParsed = exitTime;
            this.entryTime = entryTime.toString();
            this.exitTime = exitTime.toString();
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
