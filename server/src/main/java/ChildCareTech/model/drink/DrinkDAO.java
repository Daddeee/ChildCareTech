package ChildCareTech.model.drink;

import ChildCareTech.utils.AbstractGenericDAO;

public class DrinkDAO extends AbstractGenericDAO<Drink, Integer> {
    public DrinkDAO() {
        super(Drink.class);
    }
}