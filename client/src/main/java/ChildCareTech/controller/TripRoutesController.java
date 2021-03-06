package ChildCareTech.controller;

import ChildCareTech.Client;
import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.DTO.RouteDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.common.EventStatus;
import ChildCareTech.common.EventType;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.services.AccessorWindowService;
import ChildCareTech.services.AlertWindowService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;

import java.io.IOException;
import java.rmi.RemoteException;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;


public class TripRoutesController implements AccessorWindowController, TableWindowControllerInterface{
    @FXML
    protected TableView<RouteDTO> routesTable;
    @FXML
    protected Button startEvent;
    @FXML
    protected Button stopEvent;
    @FXML
    protected Label luogoPartenza;
    @FXML
    protected Label luogoArrivo;
    @FXML
    protected Button logButton;
    @FXML
    protected Button reportButton;

    private ObservableList<RouteDTO> data = FXCollections.observableArrayList();
    private TripDTO tripDTO;
    private RouteDTO currentRoute;
    private boolean isStarting;
    private boolean isEventOpening;
    private AccessorWindowService accessorWindowService;
    private AccessorWindowService logWindow;
    private AlertWindowService alertWindowService;

    @FXML
    public void initialize() {
        logWindow = new AccessorWindowService(this);
        alertWindowService = new AlertWindowService();
    }

    public void initData(TripDTO tripDTO) {
        this.tripDTO = tripDTO;
        refresh();
    }

    private void refresh() {
        data.clear();
        data.addAll(tripDTO.getRoutes());
        FXCollections.sort(data, Comparator.comparing(RouteDTO::getRouteNumber));
        routesTable.setItems(data);

        for (RouteDTO r : data) {
            if (!r.getStatus().equals(EventStatus.CLOSED)) {
                currentRoute = r;
                break;
            }
        }

        isStarting = currentRoute.getStatus().equals(EventStatus.WAIT);

        if (isStarting)
            isEventOpening = currentRoute.getDepartureEvent() == null;
        else
            isEventOpening = currentRoute.getArrivalEvent() == null;

        if (isStarting) {
            startEvent.setText("Apri partenza da " + currentRoute.getDepartureLocation());
            stopEvent.setText("Chiudi partenza da " + currentRoute.getDepartureLocation());
        } else {
            startEvent.setText("Apri arrivo a " + currentRoute.getArrivalLocation());
            stopEvent.setText("Chiudi arrivo a " + currentRoute.getArrivalLocation());
        }

        luogoPartenza.setText(currentRoute.getDepartureLocation());
        luogoArrivo.setText(currentRoute.getArrivalLocation());

        if (isEventOpening) {
            startEvent.setDisable(false);
            logButton.setDisable(true);
            stopEvent.setDisable(true);
        } else {
            startEvent.setDisable(true);
            logButton.setDisable(false);
            stopEvent.setDisable(false);
        }
    }

    @FXML
    protected void reportButtonAction(ActionEvent event) {
        EventDTO currentEvent = null;
        if(currentRoute.getDepartureEvent() != null && currentRoute.getDepartureEvent().getEventStatus().equals(EventStatus.OPEN)) {
            currentEvent = currentRoute.getDepartureEvent();
        }
        else if(currentRoute.getArrivalEvent() != null && currentRoute.getArrivalEvent().getEventStatus().equals(EventStatus.OPEN)) {
            currentEvent = currentRoute.getArrivalEvent();
        }
        if(currentEvent == null)
            return;
        try {
            logWindow.loadTripPresenceReportWindow(currentEvent);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    protected void logButtonAction(ActionEvent event) {
        try {
            logWindow.loadTripPresenceRegistrationWindow(tripDTO, currentRoute);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    protected void startEventButtonAction(ActionEvent event) {
        EventDTO eventDTO;
        String eventName;
        WorkDayDTO currentWorkDay;

        try {
            currentWorkDay = Client.getSessionService().getSession().getCurrentWorkDay();
        } catch(RemoteException ex) {
            return;
        }

        if (isStarting)
            eventName = "Partenza da " + currentRoute.getDepartureLocation();
        else
            eventName = "Arrivo a " + currentRoute.getArrivalLocation();

        eventDTO = new EventDTO(
                0,
                eventName,
                currentWorkDay,
                LocalTime.now(),
                null,
                EventType.TRIP,
                EventStatus.OPEN,
                Collections.emptySet()
        );

        if(isStarting) {
            currentRoute.setDepartureEvent(eventDTO);
        } else {
            currentRoute.setArrivalEvent(eventDTO);
        }


        try{
            Client.getSessionService().getSession().updateRouteEvent(currentRoute);
            tripDTO = Client.getSessionService().getSession().getTrip(tripDTO.getId());
        } catch(RemoteException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
        } catch(UpdateFailedException ex) {
            alertWindowService.loadWindow(ex.getMessage());
            ex.printStackTrace();
        }

        refresh();

    }

    @FXML
    protected void stopEventButtonAction(ActionEvent event){
        EventDTO eventDTO;

        if(isStarting) {
            eventDTO = currentRoute.getDepartureEvent();
            currentRoute.setStatus(EventStatus.OPEN);
        } else {
            eventDTO = currentRoute.getArrivalEvent();
            currentRoute.setStatus(EventStatus.CLOSED);
        }

        eventDTO.setEventStatus(EventStatus.CLOSED);
        eventDTO.setEndTime(LocalTime.now());

        try{
            Client.getSessionService().getSession().updateRouteEvent(currentRoute);
            tripDTO = Client.getSessionService().getSession().getTrip(tripDTO.getId());
        } catch(RemoteException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
        } catch(UpdateFailedException ex) {
            alertWindowService.loadWindow(ex.getMessage());
            ex.printStackTrace();
        }

        refresh();
    }
    public void setAccessorWindowService(AccessorWindowService accessorWindowService) {
        this.accessorWindowService = accessorWindowService;
    }
    public void refreshTable() {
        refresh();
    }
    public void notifyUpdate() { }
    private void initButtonToolTips() {
        logButton.setTooltip(new Tooltip("Mostra lo storico degli accessi"));
    }
}
