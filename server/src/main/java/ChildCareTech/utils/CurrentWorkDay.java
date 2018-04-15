package ChildCareTech.utils;

import ChildCareTech.model.DAO.WorkDayDAO;
import ChildCareTech.model.entities.WorkDay;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class CurrentWorkDay {
    private final static WorkDayDAO workDayDAO = new WorkDayDAO();

    public static WorkDay getCurrent() {
        return  retrieveCurrentWorkDay();
    }

    private static WorkDay retrieveCurrentWorkDay(){
        Transaction tx = null;
        List<WorkDay> result = Collections.emptyList();
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        workDayDAO.setSession(session);

        try{
            tx = session.beginTransaction();

            result = workDayDAO.read("date", LocalDate.now().toString());

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
