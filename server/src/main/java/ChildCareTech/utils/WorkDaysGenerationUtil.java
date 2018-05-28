package ChildCareTech.utils;

import ChildCareTech.common.DTO.DayGenerationSettingsDTO;
import ChildCareTech.common.EventStatus;
import ChildCareTech.common.EventType;
import ChildCareTech.model.DAO.WorkDayDAO;
import ChildCareTech.model.entities.Event;
import ChildCareTech.model.entities.WorkDay;
import ChildCareTech.utils.exceptions.ValidationFailedException;
import javafx.beans.binding.ObjectExpression;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class hold the logic needed to generate WorkDays on the first server startup.
 */
public class WorkDaysGenerationUtil {
    private DayGenerationSettingsDTO settings;
    private WorkDayDAO workDayDAO;
    private static Object lock = new Object();

    public WorkDaysGenerationUtil(DayGenerationSettingsDTO settings){
        this.settings = settings;
        this.workDayDAO = new WorkDayDAO();
    }

    /**
     * All threads that need to access WorkDays possibly before they are generated will wait on this lock until
     * notifications when the generation process will be finished.
     *
     * @return an Object instance representing the lock.
     */
    public static Object getLock(){
        return lock;
    }

    /**
     * Generate WorkDays based on the given {@link #settings}.
     * <p>
     * Starting from this week's monday, a number of weeks {@link DayGenerationSettingsDTO#getWeekNumber()}  specified in the settings} is generated.
     * For each generated day, an entry event and an exit event are created, both starting at times specified in settings and ending 10 minutes after.
     * After the generation is completed, all threads waiting on the {@link #lock} are notified.
     */
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
                                    EventType.DAILY,
                                    EventStatus.WAIT
                            )
                    );

                    events.add(new Event(
                                    "Uscita",
                                    temp,
                                    settings.getExitTime(),
                                    settings.getExitTime().plusMinutes(10),
                                    EventType.DAILY,
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

        synchronized(lock) {
            lock.notifyAll();
        }
    }
}
