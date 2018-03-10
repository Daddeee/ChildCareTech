package ChildCareTech;

import javax.persistence.*;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "drinks",
    uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Drink implements DAOEntity<Integer>{

    @Id
    private int id;

    private String name;

    @OneToMany
    @JoinColumn(nullable = false)
    private Set<Food> foods;

    public Drink() { }

    public Drink(String name, Set<Food> foods) {
        this.foods = foods;
        this.name = name;
    }

    @Override
    public Integer getPrimaryKey() {
        return id;
    }

    private void setName(String name) { this.name = name; }

    public String getName() { return name; }

    private void setFoods(Set<Food> foods) { this.foods = foods; }

    public Set<Food> getFoods() {return new HashSet<>(foods); }

}
