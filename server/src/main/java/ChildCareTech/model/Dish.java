package ChildCareTech.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Set;
import java.util.HashSet;

@javax.persistence.Entity
@Table(name = "dishes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name", "menu_id"})
)
public class Dish implements iEntity<Dish, Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(targetEntity = Menu.class)
    @JoinColumn(nullable = false)
    private Menu menu;

    @ManyToMany(targetEntity = Food.class)
    @JoinTable(name="dish_ingredients")
    private Set<Food> foods;

    public Dish() { }
    public Dish(String name, Menu menu) {
        this.name = name;
        this.menu = menu;
    }

    public Dish(String name, Menu menu, Set<Food> foods) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return (name + menu.getPrimaryKey()).hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Dish)) return false;
        return this.name.equals(((Dish) o).name) &&
                this.menu.equals(((Dish) o).menu);
    }

}
