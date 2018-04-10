package ChildCareTech.model.DAO;

import ChildCareTech.model.entities.Meal;
import ChildCareTech.utils.AbstractGenericDAO;

public class MealDAO extends AbstractGenericDAO<Meal, Integer> {
    public MealDAO() {
        super(Meal.class);
    }

    @Override
    public void initializeLazyRelations(Meal obj) {

    }
}