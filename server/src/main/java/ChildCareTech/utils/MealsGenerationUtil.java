package ChildCareTech.utils;

import ChildCareTech.common.EventStatus;
import ChildCareTech.common.EventType;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.model.DAO.EventDAO;
import ChildCareTech.model.DAO.MealDAO;
import ChildCareTech.model.DAO.MenuDAO;
import ChildCareTech.model.DAO.WorkDayDAO;
import ChildCareTech.model.entities.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

public class MealsGenerationUtil {
    public static void generateMeals(Canteen canteen, List<LocalTime> entryTimes, List<LocalTime> exitTimes) throws AddFailedException {
        Session session;
        Transaction tx = null;
        MealDAO mealDAO = new MealDAO();
        EventDAO eventDAO = new EventDAO();
        WorkDayDAO workDayDAO = new WorkDayDAO();
        MenuDAO menuDAO = new MenuDAO();

        validateTimes(entryTimes, exitTimes);

        session = HibernateSessionFactoryUtil.getInstance().openSession();
        mealDAO.setSession(session);
        eventDAO.setSession(session);
        workDayDAO.setSession(session);
        menuDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            List<WorkDay> allWorkDays = workDayDAO.readAll();
            allWorkDays.removeIf(WorkDay::isHoliday);

            for(WorkDay w : allWorkDays){
                EventStatus status = EventStatus.WAIT;
                if(w.getDate().isBefore(CurrentWorkDayService.getCurrent().getDate()))
                    status = EventStatus.CLOSED;

                for(int i = 0; i < entryTimes.size(); i++){
                    String entryEventName = canteen.getName() + " " + w.getDate().toString() + " - Entrata " + (i+1);
                    String exitEventName = canteen.getName() + " " + w.getDate().toString() + " - Uscita " + (i+1);

                    Event entryEvent = new Event(entryEventName, w, entryTimes.get(i), entryTimes.get(i).plusMinutes(10), EventType.MEAL, status);
                    Event exitEvent = new Event(exitEventName, w, exitTimes.get(i), exitTimes.get(i).plusMinutes(10), EventType.MEAL, status);
                    Meal meal = new Meal(canteen, i + 1, w, entryEvent, exitEvent, status, null);

                    eventDAO.create(entryEvent);
                    eventDAO.create(exitEvent);
                    mealDAO.create(meal);

                    Menu menu = new Menu(meal, 0);
                    menuDAO.create(menu);

                    meal.setMenu(menu);
                    mealDAO.update(meal);
                }
            }

            tx.commit();
        } catch(Exception e) {
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            throw new AddFailedException("Generazione dei pasti fallita");
        }

    }

    private static void validateTimes(List<LocalTime> entryTimes, List<LocalTime> exitTimes) throws AddFailedException {
        if(entryTimes.size() != exitTimes.size()) throw new AddFailedException("Errore nell'aggiunta dei pasti");
        for(int i = 0; i < entryTimes.size() - 1; i++){
            LocalTime entryTime = entryTimes.get(i);
            LocalTime exitTime = exitTimes.get(i);


            if(!entryTime.plusMinutes(10).isBefore(exitTime))
                throw new AddFailedException("Devono esserci almeno 10 minuti tra gli orari di entrata e uscita");

            for(int j = i + 1; j < entryTimes.size(); j++){
                if(entryTime.isBefore(exitTimes.get(j)) && exitTime.isAfter(exitTimes.get(j)))
                    throw new AddFailedException("Gli orari dei pasti si sovrappongono");
            }
        }
    }
}
