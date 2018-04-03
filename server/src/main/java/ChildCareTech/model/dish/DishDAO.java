package ChildCareTech.model.dish;

import ChildCareTech.utils.GenericDAO;

public class DishDAO extends GenericDAO<Dish, Integer> {
    public DishDAO() {
        super(Dish.class);
    }
}