package ChildCareTech.model.food;

import ChildCareTech.utils.GenericDAO;

public class FoodDAO extends GenericDAO<Food, Integer> {
    public FoodDAO() {
        super(Food.class);
    }
}