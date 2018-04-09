package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

public class DrinkDTO implements Serializable {
    private String name;
    private MenuDTO menu;
    private Set<FoodDTO> foods;

    public DrinkDTO(String name, MenuDTO menu, Set<FoodDTO> foods) {
        this.name = name;
        this.menu = menu;
        this.foods = foods == null ? Collections.EMPTY_SET : foods;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MenuDTO getMenu() {
        return menu;
    }

    public void setMenu(MenuDTO menu) {
        this.menu = menu;
    }

    public Set<FoodDTO> getFoods() {
        return foods;
    }

    public void setFoods(Set<FoodDTO> foods) {
        this.foods = foods;
    }
}