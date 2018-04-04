package ChildCareTech.common.DTO;

import ChildCareTech.model.dish.Dish;
import ChildCareTech.model.food.Food;
import ChildCareTech.model.supply.Supply;
import ChildCareTech.utils.DTO.DTOFactory;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class FoodDTO implements Serializable {
    private String name;
    private boolean isDrink;
    private int residualQuantity;
    private Set<DishDTO> dishes;
    private Set<SupplyDTO> supplies;

    public FoodDTO(String name, boolean isDrink, int residualQuantity, Set<DishDTO> dishes, Set<SupplyDTO> supplies){
        this.name = name;
        this.isDrink = isDrink;
        this.residualQuantity = residualQuantity;
        this.dishes = dishes;
        this.supplies = supplies;
    }

    public String getName() {
        return name;
    }

    public boolean isDrink() {
        return isDrink;
    }

    public int getResidualQuantity() {
        return residualQuantity;
    }

    public Set<DishDTO> getDishes() {
        return dishes;
    }

    public Set<SupplyDTO> getSupplies() {
        return supplies;
    }
}