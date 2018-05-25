package ChildCareTech.controller;

import ChildCareTech.Client;
import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.common.EventStatus;
import ChildCareTech.common.EventType;
import ChildCareTech.services.AccessorWindowService;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    private WorkDayDTO selectedWorkDay;
    private Set<EventDTO> events = new HashSet<>();
    private AccessorWindowService accessorWindowService;
    private AccessorWindowService codeInputService;
    private AccessorWindowService alertWindowService;
    private boolean accessorLogActive = false;
    private EventDTO selectedEventLog;

    @FXML
    public void initialize() {
        initMenu();
        accessorWindowService = new AccessorWindowService(this);
        alertWindowService = new AccessorWindowService(this);
        accessorWindowService.setOnCloseAction(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) { accessorLogActive = false; }
        });
        codeInputService = new AccessorWindowService(this);
        try {
            workDayDatePicker.setValue(Client.getSessionService().getSession().getCurrentWorkDay().getDate());
            updateTable(null);
        } catch(RemoteException ex) {
            //gestione
            ex.printStackTrace();
        }
    }

    @FXML
    public void logButtonAction(ActionEvent event) {
        selectedEventLog = eventsTable.getSelectionModel().getSelectedItem();
        if(selectedEventLog == null) return;
        try {
            accessorWindowService.loadEventReportWindow(selectedEventLog);
            accessorLogActive = true;
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
            } catch (IOException ex) {
                System.err.println("Can't load showTrip window");
                ex.printStackTrace();
            }
        }
    }

    public void remoteUpdate() {
        try{
            selectedWorkDay = Client.getSessionService().getSession().getCurrentWorkDay();
            events = selectedWorkDay.getEvents();
            events.removeIf(eventDTO -> eventDTO.getEventType().equals(EventType.TRIP));
            workDayDatePicker.setValue(selectedWorkDay.getDate());
            refreshTable();
        } catch(RemoteException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void updateTable(ActionEvent event){
        try{
            selectedWorkDay = Client.getSessionService().getSession().getWorkDay(workDayDatePicker.getValue());
            events = selectedWorkDay.getEvents();
            events.removeIf(eventDTO -> eventDTO.getEventType().equals(EventType.TRIP));
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
                codeAcquisitionButton.setDisable(false);
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
        if(accessorLogActive) {
            List<EventDTO> eventsList = new ArrayList<>();
            eventsList.addAll(events);
            if(eventsList.contains(selectedEventLog)) {
                try {
                    accessorWindowService.loadEventReportWindow(eventsList.get(eventsList.indexOf(selectedEventLog)));
                } catch(IOException ex) {
                    //gestione
                }
            }
            else {
                accessorWindowService.close();
                accessorLogActive = false;
            }
        }
    }
    public void notifyUpdate() {

    }

}
