package ChildCareTech.common.DTO;


import java.io.Serializable;
import java.util.Set;

public interface CanteenDTO extends Serializable {
    String getName();
    Set<MealDTO> getMeals();
}