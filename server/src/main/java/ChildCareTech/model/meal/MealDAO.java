package ChildCareTech.model.meal;

import ChildCareTech.utils.GenericDAO;

public class MealDAO extends GenericDAO<Meal, Integer> {
    public MealDAO() {
        super(Meal.class);
    }
}