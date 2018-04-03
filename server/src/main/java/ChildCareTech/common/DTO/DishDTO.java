package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.rmi.Remote;
import java.util.Set;

public interface DishDTO extends Serializable, Remote {
    String getName();
    MenuDTO getMenu();
    Set<FoodDTO> getFoods();
}