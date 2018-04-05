package ChildCareTech.model.food;

import ChildCareTech.utils.AbstractGenericDAO;
import org.hibernate.Hibernate;

public class FoodDAO extends AbstractGenericDAO<Food, Integer> {
    public FoodDAO() {
        super(Food.class);
    }

    @Override
    public void initializeLazyRelations(Food obj) {
        initializeDishRelation(obj);
        initializeSupplyRelation(obj);
    }

    public void initializeDishRelation(Food obj) {
        Hibernate.initialize(obj.getDishes());
    }

    public void initializeSupplyRelation(Food obj){
        Hibernate.initialize(obj.getSupplies());
    }
}