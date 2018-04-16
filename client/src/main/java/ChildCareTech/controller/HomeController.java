package ChildCareTech.controller;

import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.common.UserSession;
import ChildCareTech.network.RMI.RMIRemoteEventObserver;
import ChildCareTech.services.MainSceneManager;
import ChildCareTech.services.SessionService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
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

    private WorkDayDTO selectedWorkDay;

    public HomeController() { }

    public void initialize(){
        try{
            refresh(SessionService.getSession().getCurrentWorkDay());
        } catch(Exception e){
            e.printStackTrace();
        }
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
