package ChildCareTech.model.DAO;

import ChildCareTech.model.entities.Meal;
import ChildCareTech.utils.AbstractGenericDAO;
import org.hibernate.Hibernate;

public class MealDAO extends AbstractGenericDAO<Meal, Integer> {
    public MealDAO() {
        super(Meal.class);
    }

    @Override
    public void initializeLazyRelations(Meal obj) {
        initializeMenuRelation(obj);
    }

    private void initializeMenuRelation(Meal obj) {
        Hibernate.initialize(obj.getMenu());
    }
}