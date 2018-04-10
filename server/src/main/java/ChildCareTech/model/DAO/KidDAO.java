package ChildCareTech.model.DAO;

import ChildCareTech.model.entities.Kid;
import ChildCareTech.utils.AbstractGenericDAO;
import org.hibernate.Hibernate;

public class KidDAO extends AbstractGenericDAO<Kid, Integer> {
    public KidDAO() {
        super(Kid.class);
    }

    @Override
    public void initializeLazyRelations(Kid obj) {
        initializeContactsRelation(obj);
    }

    public void initializeContactsRelation(Kid obj) {
        Hibernate.initialize(obj.getContacts());
    }
}