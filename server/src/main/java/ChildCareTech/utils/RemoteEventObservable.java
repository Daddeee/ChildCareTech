package ChildCareTech.utils;

import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.RemoteEventObserver;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class RemoteEventObservable {
    private static RemoteEventObservable ourInstance = new RemoteEventObservable();

    private EventDTO nextEvent;
    private List<RemoteEventObserver> observers;

    public static RemoteEventObservable getInstance() {
        return ourInstance;
    }

    private RemoteEventObservable() {
        observers = new ArrayList<>();
    }

    public void setNextEvent(EventDTO nextEvent) throws RemoteException{
        this.nextEvent = nextEvent;
        notifyObservers();
    }

    public void addObserver(RemoteEventObserver observer){
        if(!observers.contains(observer))
            observers.add(observer);
    }

    public void removeObserver(RemoteEventObserver observer){
        observers.remove(observer);
    }

    private void notifyObservers() throws RemoteException{
        for(RemoteEventObserver o : observers)
            o.update(nextEvent);
    }
}
