package ChildCareTech.common.DTO;

import java.io.Serializable;

public class MealDTO implements Serializable {
    private int id;
    private CanteenDTO canteen;
    private WorkDayDTO workDay;
    private int mealNum;
    private EventDTO entryEvent;
    private EventDTO exitEvent;

    public MealDTO(int id, CanteenDTO canteen, WorkDayDTO workDay, int mealNum, EventDTO entryEvent, EventDTO exitEvent) {
        this.id = id;
        this.canteen = canteen;
        this.workDay = workDay;
        this.mealNum = mealNum;
        this.entryEvent = entryEvent;
        this.exitEvent = exitEvent;
    }

    public int getId() {
        return id;
    }

    public CanteenDTO getCanteen() {
        return canteen;
    }

    public void setCanteen(CanteenDTO canteen) {
        this.canteen = canteen;
    }

    public WorkDayDTO getWorkDay() {
        return workDay;
    }

    public EventDTO getEntryEvent() {
        return entryEvent;
    }

    public EventDTO getExitEvent() {
        return exitEvent;
    }

    public void setWorkDay(WorkDayDTO workDay) {
        this.workDay = workDay;
    }

    public int getMealNum() {
        return mealNum;
    }

    public void setMealNum(int mealNum) {
        this.mealNum = mealNum;
    }

    public void setEntryEvent(EventDTO entryEvent) {
        this.entryEvent = entryEvent;
    }

    public void setExitEvent(EventDTO exitEvent) {
        this.exitEvent = exitEvent;
    }
}