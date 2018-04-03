package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.util.Set;

public interface MenuDTO extends Serializable {
    MealDTO getMeal();
    int getNumMenu();
    Set<DishDTO> getDishes();
    DrinkDTO getDrink();
}