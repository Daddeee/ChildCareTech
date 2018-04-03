package ChildCareTech.model.canteen;

import ChildCareTech.model.iEntity;
import ChildCareTech.model.meal.Meal;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "canteens")
public class Canteen implements iEntity<Canteen, Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "canteen")
    private Set<Meal> meals;

    public Canteen() {
    }

    public Canteen(String name) {
        this.name = name;
    }

    public Canteen(String name, Set<Meal> meals) {
        this.name = name;
        this.meals = meals;
    }

    @Override
    public Integer getPrimaryKey() {
        return id;
    }

    @Override
    public void setPrimaryKey(Canteen a) {
        setId(a.getPrimaryKey());
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public Set<Meal> getMeals() {
        return meals == null ? Collections.EMPTY_SET : meals;
    }

    private void setMeals(Set<Meal> meals) {
        this.meals = meals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Canteen)) return false;
        return this.name.equals(((Canteen) o).name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
