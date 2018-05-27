package ChildCareTech.model.DAO;

import ChildCareTech.model.entities.Dish;
import ChildCareTech.model.AbstractGenericDAO;
import org.hibernate.Hibernate;

/**
 * A Data Access Object that operates with Dish entities.
 */
public class DishDAO extends AbstractGenericDAO<Dish, Integer> {
    public DishDAO() {
        super(Dish.class);
    }

    @Override
    public void initializeLazyRelations(Dish obj) {
        initializeFoodRelation(obj);
    }

    private void initializeFoodRelation(Dish obj) {
        Hibernate.initialize(obj.getFoods());
    }
}