package ChildCareTech.utils;

import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.EventStatus;
import ChildCareTech.common.RemoteEventObserver;
import ChildCareTech.model.entities.Event;
import ChildCareTech.utils.DTO.DTOFactory;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class RemoteEventObservable {
    private static RemoteEventObservable ourInstance = new RemoteEventObservable();

    private List<Event> plannedEvents;
    private List<RemoteEventObserver> observers;

    public static RemoteEventObservable getInstance() {
        return ourInstance;
    }

    private RemoteEventObservable() {
        observers = new ArrayList<>();
        plannedEvents = new ArrayList<>();
    }

    public List<Event> getPlannedEvents() {
        return plannedEvents;
    }

    public void setPlannedEvents(List<Event> plannedEvents) throws RemoteException{
        this.plannedEvents = plannedEvents;
        notifyObservers();
    }

    public void changeEventStatus(Event event, EventStatus eventStatus) throws RemoteException{
        if(plannedEvents.contains(event)) {
            event.setEventStatus(eventStatus);
            notifyObservers();
        }
    }

    public void addObserver(RemoteEventObserver observer){
        if(!observers.contains(observer))
            observers.add(observer);
    }

    public void removeObserver(RemoteEventObserver observer){
        observers.remove(observer);
    }

    private void notifyObservers() throws RemoteException{
        List<EventDTO> eventDTOS = new ArrayList<>();
        for(Event e : plannedEvents)
            eventDTOS.add(DTOFactory.getDTO(e));

        for(RemoteEventObserver o : observers)
            o.update(eventDTOS);
    }
}
