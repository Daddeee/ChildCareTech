package ChildCareTech.model.DAO;

import ChildCareTech.model.entities.Menu;
import ChildCareTech.model.AbstractGenericDAO;
import org.hibernate.Hibernate;

public class MenuDAO extends AbstractGenericDAO<Menu, Integer> {
    public MenuDAO() {
        super(Menu.class);
    }

    @Override
    public void initializeLazyRelations(Menu obj) {
        initializeDishRelation(obj);
    }

    private void initializeDishRelation(Menu obj) {
        Hibernate.initialize(obj.getDishes());
    }
}