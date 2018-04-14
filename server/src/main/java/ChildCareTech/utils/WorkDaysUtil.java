package ChildCareTech.utils;

import ChildCareTech.model.DAO.WorkDayDAO;
import ChildCareTech.model.entities.WorkDay;
import ChildCareTech.utils.exceptions.ValidationFailedException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.time.LocalTime;

public class WorkDaysUtil {
    private static LocalDate firstDate;
    private static LocalDate lastDate;
    private static WorkDayDAO workDayDAO;

    static {
        firstDate = LocalDate.parse(Settings.getProperty("firstDate"));
        lastDate = LocalDate.parse(Settings.getProperty("lastDate"));

        if(firstDate.isAfter(lastDate))
            throw new RuntimeException("Invalid dates configuration: lastDate cannot preceed firstDate");
    }

    public static LocalDate getFirstDate(){
        return firstDate;
    }

    public static LocalDate getLastDate(){
        return lastDate;
    }

    public static void initDays(){
        LocalDate minPersistentDate = null;
        LocalDate maxPersistentDate = null;
        workDayDAO = new WorkDayDAO();

        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        workDayDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            minPersistentDate = workDayDAO.getMinPersistentDate();
            maxPersistentDate = workDayDAO.getMaxPersistentDate();

            if(minPersistentDate == null || maxPersistentDate == null){
                persistRange(firstDate, lastDate);
            } else {
                if(minPersistentDate.isAfter(firstDate))
                    persistRange(firstDate, minPersistentDate.minusDays(1));

                if(maxPersistentDate.isBefore(lastDate))
                    persistRange(maxPersistentDate.plusDays(1), lastDate);
            }

            tx.commit();
        } catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }

    }

    private static void persistRange(LocalDate start, LocalDate end) throws ValidationFailedException{
        for(; !start.isAfter(end); start = start.plusDays(1))
            workDayDAO.create(new WorkDay(start, LocalTime.MIN, LocalTime.MAX, false));
    }
}
