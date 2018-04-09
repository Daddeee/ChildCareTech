package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

public class CanteenDTO implements Serializable {
    private String name;
    private Set<MealDTO> meals;

    public CanteenDTO(String name, Set<MealDTO> meals) {
        this.name = name;
        this.meals = meals == null ? Collections.EMPTY_SET : meals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<MealDTO> getMeals() {
        return meals;
    }

    public void setMeals(Set<MealDTO> meals) {
        this.meals = meals;
    }
}