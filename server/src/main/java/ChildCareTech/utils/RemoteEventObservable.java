package ChildCareTech.utils;

import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.common.EventStatus;
import ChildCareTech.common.RemoteEventObserver;
import ChildCareTech.model.DAO.EventDAO;
import ChildCareTech.model.DAO.TripDAO;
import ChildCareTech.model.DAO.WorkDayDAO;
import ChildCareTech.model.entities.Event;
import ChildCareTech.model.entities.Meal;
import ChildCareTech.model.entities.Trip;
import ChildCareTech.model.entities.WorkDay;
import ChildCareTech.utils.DTO.DTOFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class RemoteEventObservable extends Thread {
    private static RemoteEventObservable ourInstance = new RemoteEventObservable();

    private static WorkDay today = CurrentWorkDayService.getCurrent();
    private static List<Trip> todayTrips = new ArrayList<>();
    private List<RemoteEventObserver> observers;
    private EventDAO eventDAO;

    public static RemoteEventObservable getInstance() {
        return ourInstance;
    }

    private RemoteEventObservable() {
        observers = new ArrayList<>();
        eventDAO = new EventDAO();
    }

    public WorkDay getToday() {
        if (today == null) {
            Object lock = WorkDaysGenerationUtil.getLock();
            synchronized (lock) {
                try {
                    while (CurrentWorkDayService.getCurrent() == null)
                        lock.wait();

                    if(CurrentWorkDayService.getCurrent().getEvents() != null && CurrentWorkDayService.getCurrent().getEvents().size() <= 0) Thread.sleep(200);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            today = CurrentWorkDayService.getCurrent();
        }

        return today;
    }

    public List<Event> getPlannedEvents() {
        return today == null ? Collections.emptyList() : new ArrayList<>(today.getEvents());
    }

    public void setDay(WorkDay day) throws RemoteException{
        today = day;
        updateTripStatus();
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

            updateMealStatus();
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

        List<TripDTO> todayTripsDTO = new ArrayList<>();
        for(Trip t : todayTrips)
            todayTripsDTO.add(DTOFactory.getDTO(t));

        for(RemoteEventObserver o : observers)
            o.update(workDayDTO, todayTripsDTO);
    }

    private void updateMealStatus() {
        WorkDayDAO workDayDAO = new WorkDayDAO();

        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        workDayDAO.setSession(session);

        try{
            tx = session.beginTransaction();

            WorkDay w = workDayDAO.read(today);
            for(Meal m : w.getMeals())
                m.setStatus(getStatusFromEvents(m.getEntryEvent(), m.getExitEvent()));

            tx.commit();
        } catch (Exception e) {
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }
    }

    private EventStatus getStatusFromEvents(Event entry, Event exit) {
        if(entry.getEventStatus().equals(EventStatus.WAIT))
            return EventStatus.WAIT;
        if(entry.getEventStatus().equals(EventStatus.CLOSED))
            if(exit.getEventStatus().equals(EventStatus.CLOSED))
                return EventStatus.CLOSED;
            else
                return EventStatus.OPEN;

        return EventStatus.OPEN;
    }

    private void updateTripStatus() {
        TripDAO tripDAO = new TripDAO();

        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        tripDAO.setSession(session);

        try{
            tx = session.beginTransaction();

            List<Trip> tripStartingToday = tripDAO.read("depDate", today.getDate().toString());

            for(Trip t : tripStartingToday) {
                t.setStatus(EventStatus.OPEN);
                tripDAO.update(t);
            }

            // There can be duplicate trips because of the "TriggerDailyScheduling"
            tripStartingToday.removeIf(trip -> todayTrips.contains(trip));

            todayTrips.addAll(tripStartingToday);

            List<Trip> tripEndedYesterday = tripDAO.read("arrDate", today.getDate().minusDays(1).toString());
            for(Trip t : tripEndedYesterday) {
                t.setStatus(EventStatus.CLOSED);
                tripDAO.update(t);
            }

            todayTrips.removeAll(tripEndedYesterday);

            tx.commit();
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
