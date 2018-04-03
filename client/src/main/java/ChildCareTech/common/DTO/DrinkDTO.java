package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.util.Set;

public interface DrinkDTO extends Serializable {
    String getName();
    MenuDTO getMenu();
    Set<FoodDTO> getFoods();
}