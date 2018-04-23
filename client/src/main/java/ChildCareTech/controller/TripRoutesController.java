package ChildCareTech.controller;

import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.DTO.RouteDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.common.EventStatus;
import ChildCareTech.common.RemoteEventObserver;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.services.MainSceneManager;
import ChildCareTech.services.SessionService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;


public class TripRoutesController {
    @FXML
    protected TableView<RouteDTO> routesTable;
    @FXML
    protected Button startEvent;
    @FXML
    protected Button stopEvent;

    private ObservableList<RouteDTO> data = FXCollections.observableArrayList();
    private TripDTO tripDTO;
    private RouteDTO currentRoute;
    private boolean isStarting;
    private boolean isEventOpening;

    public void initData(TripDTO tripDTO) {
        this.tripDTO = tripDTO;

        data.addAll(tripDTO.getRoutes());
        FXCollections.sort(data, Comparator.comparing(RouteDTO::getRouteNumber));
        routesTable.setItems(data);

        for(RouteDTO r : data){
            if(!r.getStatus().equals(EventStatus.CLOSED)){
                currentRoute = r;
                break;
            }
        }

        isStarting = currentRoute.getStatus().equals(EventStatus.WAIT);

        if(isStarting)
            isEventOpening = currentRoute.getDepartureEvent() == null;
        else
            isEventOpening = currentRoute.getArrivalEvent() == null;

        if(isStarting){
            startEvent.setText("Apri partenza da " + currentRoute.getDepartureLocation());
            stopEvent.setText("Chiudi partenza da " + currentRoute.getDepartureLocation());
        } else {
            startEvent.setText("Apri arrivo a " + currentRoute.getArrivalLocation());
            stopEvent.setText("Chiudi arrivo a " + currentRoute.getArrivalLocation());
        }

        if(isEventOpening)
            stopEvent.setDisable(true);
        else
            startEvent.setDisable(true);
    }

    @FXML
    protected void startEventButtonAction(ActionEvent event) {
        EventDTO eventDTO;
        String eventName;

        if (isStarting)
            eventName = "Partenza da " + currentRoute.getDepartureLocation();
        else
            eventName = "Arrivo a " + currentRoute.getArrivalLocation();

        eventDTO = new EventDTO(
                0,
                eventName,
                HomeController.getSelectedWorkDay(),
                LocalTime.now(),
                null,
                EventStatus.OPEN,
                Collections.emptySet()
        );

        if(isStarting) {
            currentRoute.setDepartureEvent(eventDTO);
        } else {
            currentRoute.setArrivalEvent(eventDTO);
        }


        try{
            SessionService.getSession().updateRouteEvent(currentRoute);
        } catch(RemoteException | UpdateFailedException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
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
            SessionService.getSession().updateRouteEvent(currentRoute);
        } catch(RemoteException | UpdateFailedException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
