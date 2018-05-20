package ChildCareTech.utils;

import ChildCareTech.common.RemoteEventObserver;
import ChildCareTech.common.RemoteUpdatable;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class RemoteEventObservable extends Thread {
    private static RemoteEventObservable ourInstance = new RemoteEventObservable();

    private List<RemoteEventObserver> observers;

    public static RemoteEventObservable getInstance() {
        return ourInstance;
    }

    private RemoteEventObservable() {
        observers = new ArrayList<>();
    }

    public void addObserver(RemoteEventObserver observer){
        if(!observers.contains(observer))
            observers.add(observer);
    }

    public void removeObserver(RemoteEventObserver observer){
        observers.remove(observer);
    }

    public void notifyObservers(RemoteUpdatable updatable) throws RemoteException{
        for(RemoteEventObserver o : observers)
            o.update(updatable);
    }
}
