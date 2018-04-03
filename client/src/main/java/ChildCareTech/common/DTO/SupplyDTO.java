package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.time.LocalDate;

public interface SupplyDTO extends Serializable {
    SupplierDTO getSupplier();
    FoodDTO getFood();
    int getQuantity();
    LocalDate getDate();
}