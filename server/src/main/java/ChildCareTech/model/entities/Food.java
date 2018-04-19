package ChildCareTech.model.entities;


import ChildCareTech.model.iEntity;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@javax.persistence.Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Food implements iEntity<Food, Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;

    @ColumnDefault("0")
    private boolean isDrink;

    @Column
    @ColumnDefault("0")
    private int residualQuantity;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "food")
    private Set<Supply> supplies;

    public Food() {
    }

    public Food(String name, boolean isDrink) {
        this.name = name;
        this.isDrink = isDrink;
    }

    public Food(String name, boolean isDrink, int residualQuantity) {
        this.name = name;
        this.isDrink = isDrink;
        this.residualQuantity = residualQuantity;
    }

    public Food(String name, boolean isDrink, int residualQuantity, Set<Supply> supplies) {
        this.name = name;
        this.isDrink = isDrink;
        this.residualQuantity = residualQuantity;
        this.supplies = supplies;
    }

    public Food(int id, String name, boolean isDrink, int residualQuantity, Set<Supply> supplies) {
        this.name = name;
        this.isDrink = isDrink;
        this.residualQuantity = residualQuantity;
        this.supplies = supplies;
    }

    public int getId() {
        return id;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isDrink() {
        return isDrink;
    }

    private void setIsDrink(boolean isDrink) {
        this.isDrink = isDrink;
    }

    public int getResidualQuantity() {
        return residualQuantity;
    }

    private void setResidualQuantity(int residualQuantity) {
        this.residualQuantity = residualQuantity;
    }

    public Set<Supply> getSupplies() {
        return supplies == null ? Collections.EMPTY_SET : supplies;
    }

    public void setSupplies(Set<Supply> supplies) {
        this.supplies = supplies;
    }

    @Override
    public Integer getPrimaryKey() {
        return id;
    }
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Food)) return false;
        return this.name.equals(((Food) o).name);
    }
}
