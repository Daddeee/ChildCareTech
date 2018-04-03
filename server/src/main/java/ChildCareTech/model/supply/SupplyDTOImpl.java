package ChildCareTech.model.supply;

import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.common.DTO.SupplierDTO;
import ChildCareTech.common.DTO.SupplyDTO;
import ChildCareTech.model.food.FoodDTOImpl;
import ChildCareTech.model.supplier.SupplierDTOImpl;

import java.time.LocalDate;

public class SupplyDTOImpl implements SupplyDTO {
    private SupplierDTO supplier;
    private FoodDTO food;
    private int quantity;
    private LocalDate date;

    public SupplyDTOImpl(Supply supply){
        supplier = new SupplierDTOImpl(supply.getSupplier());
        food = new FoodDTOImpl(supply.getFood());
        quantity = supply.getQuantity();
        date = supply.getDate();
    }

    @Override
    public SupplierDTO getSupplier() {
        return supplier;
    }

    @Override
    public FoodDTO getFood() {
        return food;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public LocalDate getDate() {
        return date;
    }
}