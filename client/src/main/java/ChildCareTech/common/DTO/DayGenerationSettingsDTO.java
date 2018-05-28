package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * This class provides a Data Transfer Object encapsulation of the workday generation request utility.
 */
public class DayGenerationSettingsDTO implements Serializable {
    private int weekNumber;
    private LocalTime entryTime;
    private LocalTime exitTime;

    private Map<DayOfWeek, Boolean> areHolidays;

    public DayGenerationSettingsDTO(int weekNumber, LocalTime entryTime, LocalTime exitTime){
        this.weekNumber = weekNumber;
        this.entryTime = entryTime;
        this.exitTime = exitTime;

        this.areHolidays = new HashMap<>();
    }

    /**
     * @return the number of weeks to be generated.
     */
    public int getWeekNumber() {
        return weekNumber;
    }

    /**
     * @return the entry time (same for each working day).
     */
    public LocalTime getEntryTime() {
        return entryTime;
    }

    /**
     * @return the exit time (same for each working day).
     */
    public LocalTime getExitTime() {
        return exitTime;
    }

    /**
     * @return a boolean map where an entry's value is true if the corresponding day of week is holiday.
     */
    public Map<DayOfWeek, Boolean> getAreHolidays() {
        return areHolidays;
    }

    /**
     * @param value is monday an holiday?
     */
    public void setMondayHoliday(boolean value){ areHolidays.put(DayOfWeek.MONDAY, value); }

    /**
     * @param value is tuesday an holiday?
     */
    public void setTuesdayHoliday(boolean value){ areHolidays.put(DayOfWeek.TUESDAY, value); }

    /**
     * @param value is wednesday an holiday?
     */
    public void setWednesdayHoliday(boolean value){
        areHolidays.put(DayOfWeek.WEDNESDAY, value);
    }

    /**
     * @param value is thursday an holiday?
     */
    public void setThursdayHoliday(boolean value){
        areHolidays.put(DayOfWeek.THURSDAY, value);
    }

    /**
     * @param value is friday an holiday?
     */
    public void setFridayHoliday(boolean value){
        areHolidays.put(DayOfWeek.FRIDAY, value);
    }

    /**
     * @param value is saturday an holiday?
     */
    public void setSaturdayHoliday(boolean value){
        areHolidays.put(DayOfWeek.SATURDAY, value);
    }

    /**
     * @param value is sunday an holiday?
     */
    public void setSundayHoliday(boolean value){
        areHolidays.put(DayOfWeek.SUNDAY, value);
    }
}
