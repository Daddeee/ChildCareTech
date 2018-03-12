package ChildCareTech.entities;

import javax.persistence.*;
import java.util.Set;
import java.util.HashSet;

@javax.persistence.Entity
@Table(name = "disces")
public class Dish implements iEntity<Dish, Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(targetEntity = Menu.class)
    @JoinColumn(nullable = false)
    private Menu menu;

    @ManyToMany(targetEntity = Food.class)
    @JoinTable(name="dish_ingredients")
    private Set<Food> foods;

    public Dish() { }
    public Dish(Menu menu) { this.menu = menu; }
    public Dish(Menu menu, Set<Food> foods) {
        this.menu = menu;
        this.foods = foods;
    }

    @Override
    public Integer getPrimaryKey() {
        return id;
    }
    @Override
    public void setPrimaryKey(Dish a) { this.id = a.getPrimaryKey(); }

    private void setMenu(Menu menu) { this.menu = menu; }

    public Menu getMenu() { return menu; }

    public Set<Food> getFoods() { return new HashSet<>(foods); }

    public void setFoods(Set<Food> foods) { this.foods = foods; }

}
