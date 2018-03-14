package ChildCareTech.model;

import com.sun.xml.internal.ws.api.pipe.FiberContextSwitchInterceptor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@javax.persistence.Entity
@Table(name = "menus",
        uniqueConstraints = @UniqueConstraint(columnNames = {"meal_id", "numMenu"})
)
public class Menu implements iEntity<Menu, Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    @JoinColumn(nullable = false)
    private Meal meal;

    @Column(nullable = false)
    private int numMenu;

    @OneToMany(mappedBy = "menu", targetEntity = Dish.class)
    private Set<Dish> dishes;

    @OneToOne
    private Drink drink;

    public Menu() { }
    public Menu(Meal meal, int numMenu){
        this.meal = meal;
        this.numMenu = numMenu;
    }
    public Menu(Meal meal, int numMenu, Set<Dish> dishes, Drink drink){
        this.meal = meal;
        this.numMenu = numMenu;
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

    public Meal getMeal() {
        return meal;
    }

    private void setMeal(Meal meal) {
        this.meal = meal;
    }

    public int getNumMenu() {
        return numMenu;
    }

    private void setNumMenu(int numMenu){
        this.numMenu = numMenu;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Menu)) return false;
        return this.meal.equals(((Menu) o).meal) &&
                this.numMenu == ((Menu) o).numMenu;
    }

    @Override
    public int hashCode() {
        return (Integer.toString(numMenu) + meal.hashCode()).hashCode();
    }
}