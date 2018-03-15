package ChildCareTech.model;


import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@javax.persistence.Entity
@Table(name = "foods",
        uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Food implements iEntity<Food, Integer> {

    @Id
    private int id;

    @Column(nullable = false)
    private String name;

    @ColumnDefault("0")
    private boolean isDrink;

    @Column
    @ColumnDefault("0")
    private int residualQuantity;

    @ManyToMany(targetEntity = Dish.class)
    @JoinTable(name="ingredients")
    private Set<Dish> dishes;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "food")
    private Set<Supply> supplies;

    public Food() {}
    public Food(String name, boolean isDrink) { this.name = name; this.isDrink = isDrink; }
    public Food(String name, boolean isDrink, int residualQuantity){
        this.name = name;
        this.isDrink = isDrink;
        this.residualQuantity = residualQuantity;
    }
    public Food(String name, boolean isDrink, int residualQuantity, Set<Dish> dishes, Set<Supply> supplies) {
        this.name = name;
        this.isDrink = isDrink;
        this.residualQuantity = residualQuantity;
        this.dishes = dishes;
        this.supplies = supplies;
    }

    private void setName(String name) { this.name = name; }

    public String getName() { return name; }

    public int getResidualQuantity() {
        return residualQuantity;
    }

    private void setResidualQuantity(int residualQuantity) {
        this.residualQuantity = residualQuantity;
    }

    public Set<Supply> getSupplies() {
        return supplies;
    }

    private void setSupplies(Set<Supply> supplies) {
        this.supplies = supplies;
    }

    public Set<Dish> getDishes() { return new HashSet<>(dishes); }

    public void setDishes(Set<Dish> dishes) { this.dishes = dishes; }

    @Override
    public Integer getPrimaryKey() { return id; }
    @Override
    public void setPrimaryKey(Food a) { this.id = a.getPrimaryKey(); }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Food)) return false;
        return this.name.equals(((Food) o).name);
    }
}
