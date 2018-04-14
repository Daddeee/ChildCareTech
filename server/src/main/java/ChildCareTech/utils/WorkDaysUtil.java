package ChildCareTech.utils;

import ChildCareTech.common.DTO.DayGenerationSettingsDTO;
import ChildCareTech.model.DAO.WorkDayDAO;
import ChildCareTech.model.entities.WorkDay;
import ChildCareTech.utils.exceptions.ValidationFailedException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;

public class WorkDaysUtil {
    private DayGenerationSettingsDTO settings;
    private WorkDayDAO workDayDAO;

    public WorkDaysUtil(DayGenerationSettingsDTO settings){
        this.settings = settings;
        this.workDayDAO = new WorkDayDAO();
    }

    public void generateDays(){
        LocalDate start = LocalDate.now().with(DayOfWeek.MONDAY);
        LocalDate end = start.plusDays(settings.getWeekNumber() * 7);

        Map<DayOfWeek, Boolean> holidayMap = settings.getAreHolidays();

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        workDayDAO.setSession(session);
        try {
            tx = session.beginTransaction();
            for (; !start.isAfter(end); start = start.plusDays(1))
                workDayDAO.create(new WorkDay(
                        start,
                        settings.getEntryTime(),
                        settings.getExitTime(),
                        holidayMap.get(start.getDayOfWeek())
                ));
            tx.commit();
        } catch (Exception ex){
            if(tx!=null) tx.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }

    }
}
