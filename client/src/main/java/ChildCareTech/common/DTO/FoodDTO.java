package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

public class FoodDTO implements Serializable {
    private int id;
    private String name;
    private boolean isDrink;
    private int residualQuantity;
    private Set<SupplyDTO> supplies;
    private Set<PersonDTO> allergies;

    public FoodDTO(int id, String name, boolean isDrink, int residualQuantity, Set<SupplyDTO> supplies, Set<PersonDTO> allergies) {
        this.id = id;
        this.name = name;
        this.isDrink = isDrink;
        this.residualQuantity = residualQuantity;
        this.supplies = supplies == null ? Collections.EMPTY_SET : supplies;
        this.allergies = allergies == null ? Collections.EMPTY_SET : allergies;
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

    public Set<PersonDTO> getAllergies() {
        return allergies;
    }

    public void setAllergies(Set<PersonDTO> allergies) {
        this.allergies = allergies;
    }
}