package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.time.LocalDate;

public class SupplyDTO implements Serializable {
    private SupplierDTO supplier;
    private FoodDTO food;
    private int quantity;
    private LocalDate date;

    public SupplyDTO(SupplierDTO supplier, FoodDTO food, int quantity, LocalDate date) {
        this.supplier = supplier;
        this.food = food;
        this.quantity = quantity;
        this.date = date;
    }

    public SupplierDTO getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierDTO supplier) {
        this.supplier = supplier;
    }

    public FoodDTO getFood() {
        return food;
    }

    public void setFood(FoodDTO food) {
        this.food = food;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}