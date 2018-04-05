package ChildCareTech.model.dish;

import ChildCareTech.utils.AbstractGenericDAO;

public class DishDAO extends AbstractGenericDAO<Dish, Integer> {
    public DishDAO() {
        super(Dish.class);
    }
}