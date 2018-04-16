package ChildCareTech.utils;

import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.common.EventStatus;
import ChildCareTech.common.RemoteEventObserver;
import ChildCareTech.model.DAO.EventDAO;
import ChildCareTech.model.entities.Event;
import ChildCareTech.model.entities.WorkDay;
import ChildCareTech.utils.DTO.DTOFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RemoteEventObservable {
    private static RemoteEventObservable ourInstance = new RemoteEventObservable();

    private WorkDay today;
    private List<RemoteEventObserver> observers;
    private EventDAO eventDAO;

    public static RemoteEventObservable getInstance() {
        return ourInstance;
    }

    private RemoteEventObservable() {
        observers = new ArrayList<>();
        eventDAO = new EventDAO();
    }

    public List<Event> getPlannedEvents() {
        return today == null ? Collections.emptyList() : new ArrayList<>(today.getEvents());
    }

    public void setDay(WorkDay day) throws RemoteException{
        this.today = day;
        notifyObservers();
    }

    public void changeEventStatus(Event event, EventStatus eventStatus) throws RemoteException{
        Transaction tx = null;
        Session session;

        if(today.getEvents().contains(event)) {
            session = HibernateSessionFactoryUtil.getInstance().openSession();
            eventDAO.setSession(session);
            try{
                tx = session.beginTransaction();

                event.setEventStatus(eventStatus);
                eventDAO.update(event);

                tx.commit();
            } catch (Exception e){
                if(tx!=null) tx.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
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
        WorkDayDTO workDayDTO = DTOFactory.getDTO(today);
        for(RemoteEventObserver o : observers)
            o.update(workDayDTO);
    }
}
