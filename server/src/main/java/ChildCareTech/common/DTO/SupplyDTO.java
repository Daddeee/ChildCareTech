package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.rmi.Remote;
import java.time.LocalDate;

public interface SupplyDTO extends Serializable, Remote {
    SupplierDTO getSupplier();

    FoodDTO getFood();

    int getQuantity();

    LocalDate getDate();
}