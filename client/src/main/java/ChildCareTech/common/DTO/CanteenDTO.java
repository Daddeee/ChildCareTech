package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

/**
 * This class provides a Data Transfer Object encapsulation for Canteen entity.
 */
public class CanteenDTO implements Serializable {
    private int id;
    private String name;
    private Set<MealDTO> meals;

    public CanteenDTO(int id, String name, Set<MealDTO> meals) {
        this.id = id;
        this.name = name;
        this.meals = meals == null ? Collections.EMPTY_SET : meals;
    }

    public int getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(!(o instanceof CanteenDTO)) return false;
        return this.name.equals(((CanteenDTO) o).name);
    }
}