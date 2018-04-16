package ChildCareTech.utils;

import ChildCareTech.common.DTO.DayGenerationSettingsDTO;
import ChildCareTech.common.EventStatus;
import ChildCareTech.model.DAO.WorkDayDAO;
import ChildCareTech.model.entities.Event;
import ChildCareTech.model.entities.WorkDay;
import ChildCareTech.utils.exceptions.ValidationFailedException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WorkDaysGenerationUtil {
    private DayGenerationSettingsDTO settings;
    private WorkDayDAO workDayDAO;

    public WorkDaysGenerationUtil(DayGenerationSettingsDTO settings){
        this.settings = settings;
        this.workDayDAO = new WorkDayDAO();
    }

    public void generateDays(){
        LocalDate start = LocalDate.now().with(DayOfWeek.MONDAY);
        LocalDate end = start.plusDays(settings.getWeekNumber() * 7);
        WorkDay temp;
        Set<Event> events;

        Map<DayOfWeek, Boolean> holidayMap = settings.getAreHolidays();

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        workDayDAO.setSession(session);
        try {
            tx = session.beginTransaction();
            for (; !start.isAfter(end); start = start.plusDays(1)) {
                temp = new WorkDay(
                        start,
                        settings.getEntryTime(),
                        settings.getExitTime(),
                        holidayMap.get(start.getDayOfWeek())
                );

                events = new HashSet<>();
                if(!holidayMap.get(start.getDayOfWeek())){
                    events.add(new Event(
                                    "Ingresso",
                                    temp,
                                    settings.getEntryTime(),
                                    settings.getEntryTime().plusMinutes(10),
                                    EventStatus.WAIT
                            )
                    );

                    events.add(new Event(
                                    "Uscita",
                                    temp,
                                    settings.getExitTime(),
                                    settings.getExitTime().plusMinutes(10),
                                    EventStatus.WAIT
                            )
                    );
                }

                temp.setEvents(events);
                workDayDAO.create(temp);
            }
            tx.commit();
        } catch (Exception ex){
            if(tx!=null) tx.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }

    }
}
