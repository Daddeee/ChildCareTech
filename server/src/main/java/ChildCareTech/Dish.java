package ChildCareTech;

import javax.persistence.*;
import java.util.Set;
import java.util.HashSet;

@javax.persistence.Entity
@Table(name = "disces")
public class Dish implements iEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(targetEntity = Menu.class)
    @JoinColumn(nullable = false)
    private Menu menu;

    @ManyToMany(targetEntity = Food.class)
    @JoinTable(name="ingredients")
    private Set<Food> foods;

    public Dish() { }

    public Dish(Menu menu, Set<Food> foods) {
        this.menu = menu;
        this.foods = foods;
    }

    @Override
    public Integer getPrimaryKey() {
        return id;
    }

    private void setMenu(Menu menu) { this.menu = menu; }

    public Menu getMenu() { return menu; }

    private void setFoods(Set<Food> foods) { this.foods = foods; }

    public Set<Food> getFoods() { return new HashSet<>(foods); }

}
