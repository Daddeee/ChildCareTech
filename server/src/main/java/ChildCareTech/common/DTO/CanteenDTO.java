package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.util.Set;

public class CanteenDTO implements Serializable {
    private String name;
    private Set<MealDTO> meals;

    public CanteenDTO(String name, Set<MealDTO> meals) {
        this.name = name;
        this.meals = meals;
    }

    public String getName() {
        return name;
    }

    public Set<MealDTO> getMeals() {
        return meals;
    }
}