package ChildCareTech.utils;

import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.common.DTO.MealDTO;
import ChildCareTech.common.EventStatus;
/**
 * This class is used for simplify the {@link MealDTO MealDTO} instance's display in tables.
 */
public class TempMealData {
    private String day;
    private String entryTime;
    private String exitTime;
    private String status;
    private MealDTO mealDTO;
    private String menuList;

    public TempMealData(String day, String entryTime, String exitTime, String status,MealDTO mealDTO) {
        this.day = day;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.status = status;
        this.mealDTO = mealDTO;
        this.menuList = buildMenuList();
    }

    public TempMealData(MealDTO mealDTO) {
        this.day = mealDTO.getWorkDay().getDate().toString();
        this.entryTime = mealDTO.getEntryEvent().getBeginTime().toString();
        this.exitTime = mealDTO.getExitEvent().getEndTime().toString();
        this.status = mealDTO.getStatus().toString();
        this.mealDTO = mealDTO;
        this.menuList = buildMenuList();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MealDTO getMealDTO() {
        return mealDTO;
    }

    public String getMenuList() {
        return menuList;
    }

    public void setMenuList(String menuList) {
        this.menuList = menuList;
    }

    private String buildMenuList(){
        if(mealDTO.getMenu() == null || mealDTO.getMenu().getDishes().size() <= 0) {
            return  "";
        } else {
            StringBuilder sb = new StringBuilder();
            for(DishDTO d : mealDTO.getMenu().getDishes()) {
                sb.append(d.getName());
                sb.append(", ");
            }

            sb.deleteCharAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);

            return sb.toString();
        }
    }
}
