package ChildCareTech;

import javax.persistence.*;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "supplies")
public class Supply implements Entity<Integer>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(targetEntity = Supplier.class)
    private Supplier supplier;

    @ManyToOne(targetEntity = Food.class)
    private Food food;

    public Supplier getSupplier() { return supplier; }

    private void setSupplier(Supplier supplier) { this.supplier = supplier; }

    public Food getFoods() { return food; }

    private void setFoods(Food food) { this.food = food; }

    @Override
    public Integer getPrimaryKey() { return id; }
}

