package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

public class FoodDTO implements Serializable {
    private String name;
    private boolean isDrink;
    private int residualQuantity;
    private Set<DishDTO> dishes;
    private Set<SupplyDTO> supplies;

    public FoodDTO(String name, boolean isDrink, int residualQuantity, Set<SupplyDTO> supplies) {
        this.name = name;
        this.isDrink = isDrink;
        this.residualQuantity = residualQuantity;
        this.supplies = supplies == null ? Collections.EMPTY_SET : supplies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDrink() {
        return isDrink;
    }

    public void setDrink(boolean drink) {
        isDrink = drink;
    }

    public int getResidualQuantity() {
        return residualQuantity;
    }

    public void setResidualQuantity(int residualQuantity) {
        this.residualQuantity = residualQuantity;
    }

    public Set<SupplyDTO> getSupplies() {
        return supplies;
    }

    public void setSupplies(Set<SupplyDTO> supplies) {
        this.supplies = supplies;
    }
}