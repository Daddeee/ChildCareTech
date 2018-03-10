package ChildCareTech;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@javax.persistence.Entity
@Table(name = "foods",
        uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Food implements iEntity<Integer> {

    @Id
    private int id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(targetEntity = Dish.class)
    @JoinTable(name="ingredients")
    private Set<Food> dishes;

    public Food() {}

    public Food(String name) { this.name = name; }

    private void setName(String name) { this.name = name; }

    public String getName() { return name; }

    public Set<Food> getDishes() { return new HashSet<>(dishes); }

    private void setDishes(Set<Food> dishes) { this.dishes = dishes; }

    @Override
    public Integer getPrimaryKey() { return id; }

}
