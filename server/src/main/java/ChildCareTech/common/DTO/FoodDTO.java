package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.rmi.Remote;
import java.util.Set;

public interface FoodDTO extends Serializable, Remote {
    String getName();
    int getResidualQuantity();
    Set<SupplyDTO> getSupplies();
    Set<DishDTO> getDishes();
    boolean isDrink();
}