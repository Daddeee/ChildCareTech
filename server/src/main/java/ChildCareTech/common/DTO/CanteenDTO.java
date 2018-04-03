package ChildCareTech.common.DTO;


import java.io.Serializable;
import java.rmi.Remote;
import java.util.Set;

public interface CanteenDTO extends Serializable, Remote {
    String getName();

    Set<MealDTO> getMeals();
}