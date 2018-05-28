package ChildCareTech.utils;

import ChildCareTech.common.EventStatus;
import ChildCareTech.model.DAO.EventDAO;
import ChildCareTech.model.DAO.TripDAO;
import ChildCareTech.model.DAO.WorkDayDAO;
import ChildCareTech.model.entities.Event;
import ChildCareTech.model.entities.Meal;
import ChildCareTech.model.entities.Trip;
import ChildCareTech.model.entities.WorkDay;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

/**
 * This class holds the current server WorkDay.
 */
public class CurrentWorkDayService {
    private final static WorkDayDAO workDayDAO = new WorkDayDAO();
    private final static EventDAO eventDAO = new EventDAO();
    private static LocalDate today = LocalDate.now();
    private static WorkDay current = null;

    /**
     * Return the current WorkDay. If no workday is present or the present WorkDay is old, it is retrieved from the
     * database according to the current real world date and then it is returned.
     * <p>
     * An update on the trip status must be perfomed every time the current workday changes. This is fired "lazily" after
     * retrieving the current workday from the database.
     *
     * @return the current WorkDay.
     */
    public static WorkDay getCurrent() {
        if(LocalDate.now().isAfter(today) || current == null){
            today = LocalDate.now();
            current = retrieveCurrentWorkDay();
            updateTripStatus();
        }
        return  current;
    }

    /**
     * Set the current workday to the given value. An update on the trip status is triggered after setting the new current
     * WorkDay.
     *
     * @param newCurrent the new current WorkDay.
     */
    public static void setCurrent(WorkDay newCurrent){
        current = newCurrent;
        today = newCurrent.getDate();
        updateTripStatus();
    }

    /**
     * Set the event status to the given value. Every time this happens the status of meals is updated if needed.
     *
     * @param event the given event.
     * @param eventStatus the new event status.
     */
    public static void changeEventStatus(Event event, EventStatus eventStatus) {
        Transaction tx = null;
        Session session;

        if(current.getEvents().contains(event)) {
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
        }


    }

    private static void updateMealStatus() {
        WorkDayDAO workDayDAO = new WorkDayDAO();

        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        workDayDAO.setSession(session);

        try{
            tx = session.beginTransaction();

            WorkDay w = workDayDAO.read(current);
            for(Meal m : w.getMeals())
                m.setStatus(getStatusFromEvents(m.getEntryEvent(), m.getExitEvent()));

            tx.commit();
        } catch (Exception e) {
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }
    }

    private static EventStatus getStatusFromEvents(Event entry, Event exit) {
        if(entry.getEventStatus().equals(EventStatus.WAIT))
            return EventStatus.WAIT;
        if(entry.getEventStatus().equals(EventStatus.CLOSED))
            if(exit.getEventStatus().equals(EventStatus.CLOSED))
                return EventStatus.CLOSED;
            else
                return EventStatus.OPEN;

        return EventStatus.OPEN;
    }

    private static void updateTripStatus() {
        TripDAO tripDAO = new TripDAO();

        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        tripDAO.setSession(session);

        try{
            tx = session.beginTransaction();

            List<Trip> tripStartingToday = tripDAO.read("depDate", today);
            for(Trip t : tripStartingToday) {
                t.setStatus(EventStatus.OPEN);
                tripDAO.update(t);
            }

            List<Trip> tripEndedYesterday = tripDAO.read("arrDate", today.minusDays(1));
            for(Trip t : tripEndedYesterday) {
                t.setStatus(EventStatus.CLOSED);
                tripDAO.update(t);
            }

            // There can be duplicate trips because of the "TriggerDailyScheduling"
            // This was used in the main event board
            /*tripStartingToday.removeIf(trip -> todayTrips.contains(trip));
            todayTrips.addAll(tripStartingToday);
            todayTrips.removeAll(tripEndedYesterday);*/

            tx.commit();
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static WorkDay retrieveCurrentWorkDay(){
        Transaction tx = null;
        List<WorkDay> result = Collections.emptyList();
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        workDayDAO.setSession(session);

        try{
            tx = session.beginTransaction();

            result = workDayDAO.read("date", today);
            for(WorkDay w : result) {
                workDayDAO.initializeLazyRelations(w);
                for(Event e : w.getEvents())
                    eventDAO.initializeLazyRelations(e);
            }
            tx.commit();
        } catch(Exception e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result.isEmpty() ? null : result.get(0);
    }
}
