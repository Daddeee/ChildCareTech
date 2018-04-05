package ChildCareTech.model.dish;

import ChildCareTech.utils.AbstractGenericDAO;
import org.hibernate.Hibernate;

public class DishDAO extends AbstractGenericDAO<Dish, Integer> {
    public DishDAO() {
        super(Dish.class);
    }

    @Override
    public void initializeLazyRelations(Dish obj) {
        initializeFoodRelation(obj);
    }

    public void initializeFoodRelation(Dish obj) {
        Hibernate.initialize(obj.getFoods());
    }
}