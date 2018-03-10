package ChildCareTech;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "menus")
public class Menu implements DAOEntity<Integer>{
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "disc")
    @OneToMany(mappedBy = "menu", targetEntity = Dish.class)
    private Set<Dish> dishes;


    @OneToOne(targetEntity = Drink.class)
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

    private void setDisces(Set<Dish> dishes) { this.dishes = dishes; }

    public Set<Dish> getDisces() { return new HashSet<>(dishes); }

    private void setDrink(Drink drink) { this.drink = drink; }

    public Drink getDrink() { return drink; }

}
