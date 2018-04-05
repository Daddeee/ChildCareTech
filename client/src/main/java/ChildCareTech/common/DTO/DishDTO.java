package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

public class DishDTO implements Serializable {
    private String name;
    private MenuDTO menu;
    private Set<FoodDTO> foods;

    public DishDTO(String name, MenuDTO menu, Set<FoodDTO> foods) {
        this.name = name;
        this.menu = menu;
        this.foods = foods == null ? Collections.EMPTY_SET : foods;
    }

    public String getName() {
        return name;
    }

    public MenuDTO getMenu() {
        return menu;
    }

    public Set<FoodDTO> getFoods() {
        return foods;
    }
}