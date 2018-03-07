package ChildCareTech;

import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "canteen")
public class Canteen {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @OneToMany(mappedBy="canteen")
    private Set<Meal> meals;

    public Canteen() {}
    public Canteen(Set<Meal> meals) { this.meals = meals; }

    public int getId() { return id; }

    private void setId(int id) { this.id = id; }

    public Set<Meal> getMeals() { return meals; }

    private void setMeals(Set<Meal> meals) { this.meals = meals; }

}
