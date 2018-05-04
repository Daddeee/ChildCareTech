package ChildCareTech.controller;

import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.common.EventStatus;
import ChildCareTech.services.AccessorSceneManager;
import ChildCareTech.services.MainSceneManager;
import ChildCareTech.services.SessionService;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class HomeController {
    @FXML
    protected Label currentDate;
    @FXML
    protected TableView<EventDTO> eventsTable;
    @FXML
    protected TableView<TripDTO> openTripsTable;
    @FXML
    protected Label alertLabel;

    private static WorkDayDTO selectedWorkDay;
    private static List<TripDTO> todayOpenTrips;

    public HomeController() { }

    public static WorkDayDTO getSelectedWorkDay() {
        return selectedWorkDay;
    }

    @FXML
    public void initialize() {
        eventsTable.setRowFactory(eventDTOTableView -> {
            final TableRow<EventDTO> row = new TableRow<>();

            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem codeInput = new MenuItem("Acquisisci codici");
            codeInput.setOnAction(event ->  {
                contextMenu.hide();
                if(row.getItem().getEventStatus().equals(EventStatus.CLOSED))
                    alertLabel.setText("This event is now closed.");
                else if(row.getItem().getEventStatus().equals(EventStatus.WAIT))
                    alertLabel.setText("This event has not yet been opened.");
                else {
                    alertLabel.setText("");
                    try {
                        AccessorSceneManager.loadCodeInput(row.getItem());
                    } catch (IOException ex) {
                        System.err.println("Can't load showTrip window");
                        ex.printStackTrace();
                    }
                }
            });
            contextMenu.getItems().add(codeInput);

            final MenuItem eventReport = new MenuItem("Consulta report");
            eventReport.setOnAction(event -> {
                contextMenu.hide();
                alertLabel.setText("");
                try {
                    AccessorSceneManager.loadEventReport(row.getItem());
                } catch (IOException ex) {
                    System.err.println("Can't load eventReport window");
                    ex.printStackTrace();
                }
            });
            contextMenu.getItems().add(eventReport);

            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                            .then((ContextMenu) null)
                            .otherwise(contextMenu)
            );

            return row;

        });

        if (selectedWorkDay != null && todayOpenTrips != null) {
            refresh(selectedWorkDay, todayOpenTrips);
        } else {
            try{
                SessionService.getSession().triggerDailyScheduling();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void refresh(WorkDayDTO workDayDTO, List<TripDTO> tripDTOS){
        selectedWorkDay = workDayDTO;
        todayOpenTrips = tripDTOS;
        refreshDateLabel();
        refreshWorkDayTable();
        refreshOpenTripsTable();
    }

    private void refreshDateLabel(){
        currentDate.setText(selectedWorkDay.getDate().toString());
    }

    private void refreshWorkDayTable(){
        eventsTable.getItems().clear();
        eventsTable.getItems().addAll(selectedWorkDay.getEvents());
    }

    private void refreshOpenTripsTable(){
        openTripsTable.getItems().clear();
        openTripsTable.getItems().addAll(todayOpenTrips);
    }

    @FXML
    protected void kidAnagraphicsButtonAction(ActionEvent action) {
        try {
            MainSceneManager.loadKidAnagraphics();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    protected void adultAnagraphicsButtonAction(ActionEvent action) {
        try {
            MainSceneManager.loadAdultAnagraphics();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    protected void tripButtonAction(ActionEvent action){
        try {
            MainSceneManager.loadTrips();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    protected void busButtonAction(ActionEvent action){
        try {
            MainSceneManager.loadBuses();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    protected void foodButtonAction(ActionEvent action){
        try {
            MainSceneManager.loadFoods();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    protected void workDayButtonAction(ActionEvent ev){
        try {
            MainSceneManager.loadWorkDay();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    protected void logoutButtonAction(ActionEvent action) {
        SessionService.logoutAttempt();
        try {
            MainSceneManager.loadLogin();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
