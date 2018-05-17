package ChildCareTech.controller;

import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.EventStatus;
import ChildCareTech.services.AccessorWindowService;
import ChildCareTech.services.SessionService;
import ChildCareTech.utils.RestrictedDatePicker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.rmi.RemoteException;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class NewWorkDayController implements TableWindowControllerInterface {
    @FXML
    protected TableView<EventDTO> eventsTable;
    @FXML
    protected TableColumn<EventDTO, String> nameColumn;
    @FXML
    protected TableColumn<EventDTO, LocalTime> beginColumn;
    @FXML
    protected TableColumn<EventDTO, LocalTime> endColumn;
    @FXML
    protected TableColumn<EventDTO, EventStatus> statusColumn;
    @FXML
    protected RestrictedDatePicker workDayDatePicker;
    @FXML
    protected Button codeAcquisitionButton;
    @FXML
    protected Button logButton;

    Set<EventDTO> events = new HashSet<>();
    AccessorWindowService accessorWindowService;
    AccessorWindowService codeInputService;
    boolean codeInputActive = false;

    @FXML
    public void initialize() {
        initMenu();
        accessorWindowService = new AccessorWindowService(this);
        codeInputService = new AccessorWindowService(this);
        codeInputService.setNonLockingModality();
        codeInputService.setOnCloseAction(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                codeInputActive = false;
            }
        });
    }

    @FXML
    public void logButtonAction(ActionEvent event) {
        EventDTO selected = eventsTable.getSelectionModel().getSelectedItem();
        if(selected == null) return;
        try {
            accessorWindowService.loadEventReportWindow(selected);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    @FXML
    public void codeAcquisitionButtonAction(ActionEvent event) {
        EventDTO selected = eventsTable.getSelectionModel().getSelectedItem();
        if(selected == null) return;
        if(selected.getEventStatus().equals(EventStatus.CLOSED))
            System.out.println("This event is now closed.");  //gestione errore
        else if(selected.getEventStatus().equals(EventStatus.WAIT))
            System.out.println("This event has not yet been opened.");
        else {
            try {
                codeInputService.loadCodeInputWindow(selected);
                codeInputActive = true;
            } catch (IOException ex) {
                System.err.println("Can't load showTrip window");
                ex.printStackTrace();
            }
        }
    }

    @FXML
    public void updateTable(ActionEvent event){
        try{
            events = SessionService.getSession().getWorkDay(workDayDatePicker.getValue()).getEvents();
            refreshTable();
        } catch(RemoteException e){
            e.printStackTrace();
        }
    }

    public void initMenu() {
        logButton.setDisable(true);
        codeAcquisitionButton.setDisable(true);

        eventsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                logButton.setDisable(false);
                if(!codeInputActive)
                    codeAcquisitionButton.setDisable(false);
                else
                    codeAcquisitionButton.setDisable(true);
            }
            else {
                logButton.setDisable(true);
                codeAcquisitionButton.setDisable(true);
            }
        });
    }

    public void refreshTable() {
        eventsTable.getItems().clear();
        eventsTable.getItems().addAll(events);
    }

}
