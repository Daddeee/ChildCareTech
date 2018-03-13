package ChildCareTech.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@javax.persistence.Entity
@Table(name = "menus")
public class Menu implements iEntity<Menu, Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(mappedBy = "menu", targetEntity = Dish.class)
    private Set<Dish> dishes;

    @OneToOne
    private Drink drink;

    public Menu() { }
    public Menu(Set<Dish> dishes, Drink drink) {
        this.dishes = dishes;
        this.drink = drink;
    }

    @Override
    public Integer getPrimaryKey() {
        return id;
    }
    @Override
    public void setPrimaryKey(Menu a) { this.id = a.getPrimaryKey(); }

    private void setDisces(Set<Dish> dishes) { this.dishes = dishes; }

    public Set<Dish> getDisces() { return new HashSet<>(dishes); }

    private void setDrink(Drink drink) { this.drink = drink; }

    public Drink getDrink() { return drink; }

}
