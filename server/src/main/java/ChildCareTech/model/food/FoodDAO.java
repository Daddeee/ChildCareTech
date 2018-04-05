package ChildCareTech.model.food;

import ChildCareTech.utils.AbstractGenericDAO;

public class FoodDAO extends AbstractGenericDAO<Food, Integer> {
    public FoodDAO() {
        super(Food.class);
    }
}