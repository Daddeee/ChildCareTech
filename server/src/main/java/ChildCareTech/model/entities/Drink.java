package ChildCareTech.model.entities;

import ChildCareTech.model.iEntity;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@javax.persistence.Entity
@Table(name = "drinks",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name", "menu_id"}))
public class Drink implements iEntity<Drink, Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;

    @OneToOne
    private Menu menu;

    @ManyToMany(targetEntity = Food.class)
    @JoinTable(name = "drink_ingredients")
    private Set<Food> foods;

    public Drink() {
    }

    public Drink(String name, Menu menu) {
        this.name = name;
        this.menu = menu;
    }

    public Drink(String name, Menu menu, Set<Food> foods) {
        this.name = name;
        this.menu = menu;
        this.foods = foods;
    }

    public Drink(int id, String name, Menu menu, Set<Food> foods) {
        this.id = id;
        this.name = name;
        this.menu = menu;
        this.foods = foods;
    }

    @Override
    public Integer getPrimaryKey() {
        return id;
    }

    @Override
    public void setPrimaryKey(Drink a) {
        this.id = a.getPrimaryKey();
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setFoods(Set<Food> foods) {
        this.foods = foods;
    }

    public Set<Food> getFoods() {
        return foods == null ? Collections.EMPTY_SET : foods;
    }

    @Override
    public int hashCode() {
        return (name + menu.hashCode()).hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Drink)) return false;
        return this.name.equals(((Drink) o).name) &&
                this.menu.equals(((Drink) o).menu);
    }

}
