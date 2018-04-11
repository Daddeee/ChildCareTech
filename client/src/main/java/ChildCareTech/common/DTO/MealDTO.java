package ChildCareTech.common.DTO;

import java.io.Serializable;

public class MealDTO implements Serializable {
    private int id;
    private CanteenDTO canteen;
    private WorkDayDTO workDay;
    private int mealNum;

    public MealDTO(int id, CanteenDTO canteen, WorkDayDTO workDay, int mealNum) {
        this.id = id;
        this.canteen = canteen;
        this.workDay = workDay;
        this.mealNum = mealNum;
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

    public void setWorkDay(WorkDayDTO workDay) {
        this.workDay = workDay;
    }

    public int getMealNum() {
        return mealNum;
    }

    public void setMealNum(int mealNum) {
        this.mealNum = mealNum;
    }
}