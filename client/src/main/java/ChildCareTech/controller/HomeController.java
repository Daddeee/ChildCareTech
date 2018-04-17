package ChildCareTech.controller;

import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.common.EventStatus;
import ChildCareTech.common.UserSession;
import ChildCareTech.network.RMI.RMIRemoteEventObserver;
import ChildCareTech.services.AccessorSceneManager;
import ChildCareTech.services.MainSceneManager;
import ChildCareTech.services.SessionService;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import sun.applet.Main;

import java.io.IOException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class HomeController {
    @FXML
    protected Label currentDate;
    @FXML
    protected TableView<EventDTO> eventsTable;
    @FXML
    protected Label alertLabel;

    private static WorkDayDTO selectedWorkDay;

    public HomeController() { }

    public void initialize() {
        if (selectedWorkDay != null) {
            refresh(selectedWorkDay);
        } else {
            try{
                refresh(SessionService.getSession().getCurrentWorkDay());
            } catch(Exception e){
                e.printStackTrace();
            }
        }

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

            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                            .then((ContextMenu) null)
                            .otherwise(contextMenu)
            );

            return row;

        });
    }

    public void refresh(WorkDayDTO workDayDTO){
        selectedWorkDay = workDayDTO;
        refreshDateLabel(workDayDTO.getDate());
        refreshTable(workDayDTO.getEvents());
    }

    private void refreshDateLabel(LocalDate date){
        currentDate.setText(date.toString());
    }

    private void refreshTable(Set<EventDTO> events){
        eventsTable.getItems().clear();
        eventsTable.getItems().addAll(events);
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
