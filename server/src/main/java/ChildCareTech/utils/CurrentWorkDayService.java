package ChildCareTech.utils;

import ChildCareTech.model.DAO.EventDAO;
import ChildCareTech.model.DAO.WorkDayDAO;
import ChildCareTech.model.entities.Event;
import ChildCareTech.model.entities.WorkDay;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class CurrentWorkDayService {
    private final static WorkDayDAO workDayDAO = new WorkDayDAO();
    private final static EventDAO eventDAO = new EventDAO();
    private static LocalDate today = LocalDate.now();
    private static WorkDay current = null;

    public static WorkDay getCurrent() {
        if(LocalDate.now().isAfter(today) || current == null){
            today = LocalDate.now();
            current = retrieveCurrentWorkDay();
        }
        return  current;
    }

    private static WorkDay retrieveCurrentWorkDay(){
        Transaction tx = null;
        List<WorkDay> result = Collections.emptyList();
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        workDayDAO.setSession(session);

        try{
            tx = session.beginTransaction();

            result = workDayDAO.read("date", today.toString());
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
