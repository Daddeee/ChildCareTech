package ChildCareTech;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "canteens")
public class Canteen implements iEntity<Integer> {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @OneToMany(mappedBy="canteen")
    private Set<Meal> meals;

    public Canteen() {}
    public Canteen(Set<Meal> meals) { this.meals = meals; }

    @Override
    public Integer getPrimaryKey() {
        return id;
    }

    public int getId() { return id; }

    private void setId(int id) { this.id = id; }

    public Set<Meal> getMeals() { return new HashSet<>(meals); }

    private void setMeals(Set<Meal> meals) { this.meals = meals; }

}
