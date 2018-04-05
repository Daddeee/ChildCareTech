package ChildCareTech.model.menu;

import ChildCareTech.utils.AbstractGenericDAO;
import org.hibernate.Hibernate;

public class MenuDAO extends AbstractGenericDAO<Menu, Integer> {
    public MenuDAO() {
        super(Menu.class);
    }

    @Override
    public void initializeLazyRelations(Menu obj) {
        initializeDishRelation(obj);
    }

    public void initializeDishRelation(Menu obj) {
        Hibernate.initialize(obj.getDishes());
    }
}