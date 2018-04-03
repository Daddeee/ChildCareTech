package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.rmi.Remote;
import java.util.Set;

public interface MenuDTO extends Serializable, Remote {
    MealDTO getMeal();
    int getNumMenu();
    Set<DishDTO> getDishes();
    DrinkDTO getDrink();
}