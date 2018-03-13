package ChildCareTech.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "canteens")
public class Canteen implements iEntity<Canteen, Integer> {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    private String name;

    @OneToMany(mappedBy="canteen")
    private Set<Meal> meals;

    public Canteen() {}
    public Canteen(String name) { this.name = name; }
    public Canteen(String name, Set<Meal> meals) { this.name = name; this.meals = meals; }

    @Override
    public Integer getPrimaryKey() {
        return id;
    }
    @Override
    public void setPrimaryKey(Canteen a) { setId(a.getPrimaryKey()); }

    public int getId() { return id; }

    private void setId(int id) { this.id = id; }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public Set<Meal> getMeals() { return new HashSet<>(meals); }

    private void setMeals(Set<Meal> meals) { this.meals = meals; }

}
