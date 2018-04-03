package ChildCareTech.model.drink;

import ChildCareTech.utils.GenericDAO;

public class DrinkDAO extends GenericDAO<Drink, Integer> {
    public DrinkDAO() {
        super(Drink.class);
    }
}