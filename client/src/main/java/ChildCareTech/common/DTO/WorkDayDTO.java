package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

public interface WorkDayDTO extends Serializable {
    LocalDate getDate();
    Set<MealDTO> getMeals();
    Set<EventDTO> getEvents();
}