package ChildCareTech;

import javax.persistence.*;
import java.util.Set;
import java.util.HashSet;

@javax.persistence.Entity
@Table(name = "drinks",
    uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Drink implements iEntity<Drink, Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @OneToOne
    private Menu menu;

    @ManyToMany(targetEntity = Food.class)
    @JoinTable(name="drink_ingredients")
    private Set<Food> foods;

    public Drink() { }
    public Drink(String name, Menu menu){
        this.name = name;
        this.menu = menu;
    }
    public Drink(String name, Menu menu, Set<Food> foods) {
        this.name = name;
        this.menu = menu;
        this.foods = foods;
    }

    @Override
    public Integer getPrimaryKey() {
        return id;
    }
    @Override
    public void setPrimaryKey(Drink a) { this.id = a.getPrimaryKey(); }

    private void setName(String name) { this.name = name; }

    public String getName() { return name; }

    public Menu getMenu() { return menu; }

    private void setMenu(Menu menu) { this.menu = menu; }

    private void setFoods(Set<Food> foods) { this.foods = foods; }

    public Set<Food> getFoods() {return new HashSet<>(foods); }

}
