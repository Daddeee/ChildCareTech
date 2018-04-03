package ChildCareTech.model.food;

import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.common.DTO.SupplyDTO;
import ChildCareTech.model.dish.Dish;
import ChildCareTech.model.dish.DishDTOImpl;
import ChildCareTech.model.supply.Supply;
import ChildCareTech.model.supply.SupplyDTOImpl;

import java.util.HashSet;
import java.util.Set;

public class FoodDTOImpl implements FoodDTO {
    private String name;
    private boolean isDrink;
    private int residualQuantity;
    private Set<DishDTO> dishes;
    private Set<SupplyDTO> supplies;

    public FoodDTOImpl(Food food) {
        name = food.getName();
        isDrink = food.isDrink();
        residualQuantity = food.getResidualQuantity();

        dishes = new HashSet<>();
        for(Dish d : food.getDishes())
            dishes.add(new DishDTOImpl(d));

        supplies = new HashSet<>();
        for(Supply s : food.getSupplies())
            supplies.add(new SupplyDTOImpl(s));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isDrink() {
        return isDrink;
    }

    @Override
    public int getResidualQuantity() {
        return residualQuantity;
    }

    @Override
    public Set<DishDTO> getDishes() {
        return dishes;
    }

    @Override
    public Set<SupplyDTO> getSupplies() {
        return supplies;
    }
}