package ChildCareTech.utils;

import ChildCareTech.common.DTO.MealDTO;

public class TempMealData {
    private String day;
    private String entryTime;
    private String exitTime;
    private MealDTO mealDTO;

    public TempMealData(String day, String entryTime, String exitTime, MealDTO mealDTO) {
        this.day = day;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.mealDTO = mealDTO;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getExitTime() {
        return exitTime;
    }

    public void setExitTime(String exitTime) {
        this.exitTime = exitTime;
    }

    public MealDTO getMealDTO() {
        return mealDTO;
    }
}
