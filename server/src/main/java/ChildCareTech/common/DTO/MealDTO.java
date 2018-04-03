package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.rmi.Remote;

public interface MealDTO extends Serializable, Remote {
    CanteenDTO getCanteen();

    int getMealNum();

    WorkDayDTO getWorkDay();
}