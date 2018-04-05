package ChildCareTech.model.meal;

import ChildCareTech.utils.AbstractGenericDAO;

public class MealDAO extends AbstractGenericDAO<Meal, Integer> {
    public MealDAO() {
        super(Meal.class);
    }
}