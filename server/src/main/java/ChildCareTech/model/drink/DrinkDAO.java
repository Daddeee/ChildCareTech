package ChildCareTech.model.drink;

import ChildCareTech.utils.AbstractGenericDAO;
import org.hibernate.Hibernate;

public class DrinkDAO extends AbstractGenericDAO<Drink, Integer> {
    public DrinkDAO() {
        super(Drink.class);
    }

    @Override
    public void initializeLazyRelations(Drink obj) {
        initializeFoodRelation(obj);
    }

    public void initializeFoodRelation(Drink obj) {
        Hibernate.initialize(obj.getFoods());
    }
}