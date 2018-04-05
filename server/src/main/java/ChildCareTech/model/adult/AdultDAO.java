package ChildCareTech.model.adult;

import ChildCareTech.utils.AbstractGenericDAO;
import org.hibernate.Hibernate;

public class AdultDAO extends AbstractGenericDAO<Adult, Integer> {
    public AdultDAO() {
        super(Adult.class);
    }

    @Override
    public void initializeLazyRelations(Adult obj) {
        initializeContactsRelation(obj);
    }

    public void initializeContactsRelation(Adult obj){
        Hibernate.initialize(obj.getContacts());
    }
}