package ChildCareTech.model.entities;

import ChildCareTech.model.iEntity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "supplies",
        uniqueConstraints = @UniqueConstraint(columnNames = {"supplier_id", "food_id", "date"})
)
public class Supply implements iEntity<Supply, Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Food food;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private LocalDate date;

    public Supply() {
    }

    public Supply(Supplier supplier, Food food, int quantity, LocalDate date) {
        this.supplier = supplier;
        this.food = food;
        this.quantity = quantity;
        this.date = date;
    }

    public Supply(int id, Supplier supplier, Food food, int quantity, LocalDate date) {
        this.id = id;
        this.supplier = supplier;
        this.food = food;
        this.quantity = quantity;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    private void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Food getFood() {
        return food;
    }

    private void setFood(Food food) {
        this.food = food;
    }

    public int getQuantity() {
        return quantity;
    }

    private void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    private void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public Integer getPrimaryKey() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Supply)) return false;
        return this.food.equals(((Supply) o).food) &&
                this.supplier.equals(((Supply) o).supplier) &&
                this.date.equals(((Supply) o).date);
    }

    @Override
    public int hashCode() {
        return (date.toString() + supplier.hashCode() + food.hashCode()).hashCode();
    }

}

