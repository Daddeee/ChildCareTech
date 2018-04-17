package ChildCareTech.controller;

import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.services.MainSceneManager;
import ChildCareTech.services.SessionService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Set;

public class WorkDayController {
    @FXML
    protected TableView<EventDTO> eventsTable;
    @FXML
    protected DatePicker workDayDatePicker;

    @FXML
    public void updateTable(ActionEvent event){
        WorkDayDTO w;
        try{
            w = SessionService.getSession().getWorkDay(workDayDatePicker.getValue());
            refreshTable(w.getEvents());
        } catch(RemoteException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void backButtonAction(ActionEvent event) {
        try {
            MainSceneManager.loadHome();
        } catch (IOException ex) {
            System.err.println("Can't load addKid window");
            ex.printStackTrace();
        }
    }

    private void refreshTable(Set<EventDTO> events){
        eventsTable.getItems().clear();
        eventsTable.getItems().addAll(events);
    }
}
