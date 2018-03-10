package ChildCareTech;

import javax.persistence.*;
import java.util.Set;
import java.util.HashSet;

@Table(name = "supplies")
public class Supply implements DAOEntity<Integer>{
    @Id
    private int id;

    @ManyToOne(targetEntity = Supplier.class)
    @JoinColumn(nullable = false)
    private Supplier supplier;

    @ManyToMany(targetEntity = Food.class)
    @JoinColumn(nullable = false)
    private Set<Food> foods;

    public Supplier getSupplier() { return supplier; }

    private void setSupplier(Supplier supplier) { this.supplier = supplier; }

    public Set<Food> getFoods() { return new HashSet<>(foods); }

    private void setFoods(Set<Food> foods) { this.foods = foods; }

    @Override
    public Integer getPrimaryKey() { return id; }
}

