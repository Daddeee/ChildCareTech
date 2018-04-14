package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

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

    public int getWeekNumber() {
        return weekNumber;
    }

    public LocalTime getEntryTime() {
        return entryTime;
    }

    public LocalTime getExitTime() {
        return exitTime;
    }

    public Map<DayOfWeek, Boolean> getAreHolidays() {
        return areHolidays;
    }

    public void setMondayHoliday(boolean value){ areHolidays.put(DayOfWeek.MONDAY, value); }

    public void setTuesdayHoliday(boolean value){ areHolidays.put(DayOfWeek.TUESDAY, value); }

    public void setWednesdayHoliday(boolean value){
        areHolidays.put(DayOfWeek.WEDNESDAY, value);
    }

    public void setThursdayHoliday(boolean value){
        areHolidays.put(DayOfWeek.THURSDAY, value);
    }

    public void setFridayHoliday(boolean value){
        areHolidays.put(DayOfWeek.FRIDAY, value);
    }

    public void setSaturdayHoliday(boolean value){
        areHolidays.put(DayOfWeek.SATURDAY, value);
    }

    public void setSundayHoliday(boolean value){
        areHolidays.put(DayOfWeek.SUNDAY, value);
    }
}
