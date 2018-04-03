package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.time.LocalDate;

public interface MealDTO extends Serializable {
    CanteenDTO getCanteen();
    int getMealNum();
    WorkDayDTO getWorkDay();
}