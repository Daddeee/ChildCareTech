package ChildCareTech.network;

import ChildCareTech.common.RemoteEventObserver;
import ChildCareTech.common.RemoteUpdatable;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an object whose state can be observed remotely.
 * A RemoteEventObservable can have 0 or more RemoteEventObservers and each time an update is issued
 * it can notify it to all of his observers.
 *
 * This observer is exposed as a Singleton class.
 */
public class RemoteEventObservable extends Thread {
    private static RemoteEventObservable ourInstance = new RemoteEventObservable();

    private List<RemoteEventObserver> observers;

    /**
     * @return the unique instance.
     */
    public static RemoteEventObservable getInstance() {
        return ourInstance;
    }

    private RemoteEventObservable() {
        observers = new ArrayList<>();
    }

    /**
     * Add the given observer to the observers list.
     * @param observer the observer to add.
     */
    public void addObserver(RemoteEventObserver observer){
        if(!observers.contains(observer))
            observers.add(observer);
    }

    /**
     * Remove the given observer from the observers list.
     * @param observer the observer to remove.
     */
    public void removeObserver(RemoteEventObserver observer){
        observers.remove(observer);
    }

    /**
     * Notify all the observers of an update event, calling their {@link RemoteEventObserver#update(RemoteUpdatable) update} method.
     * @param updatable the type of update to notify.
     */
    public void notifyObservers(RemoteUpdatable updatable) throws RemoteException{
        for(RemoteEventObserver o : observers)
            o.update(updatable);
    }
}
