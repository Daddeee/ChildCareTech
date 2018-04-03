package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.time.LocalDate;

public interface MealDTO extends Serializable {
    public LocalDate getDate();
    public CanteenDTO getCanteen();
    int getMealNum();
    WorkDayDTO getWorkDay();
}