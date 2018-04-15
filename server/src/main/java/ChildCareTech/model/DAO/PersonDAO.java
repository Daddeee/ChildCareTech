package ChildCareTech.model.DAO;

import ChildCareTech.model.entities.Person;
import ChildCareTech.utils.AbstractGenericDAO;
import org.hibernate.Hibernate;

public class PersonDAO extends AbstractGenericDAO<Person, String> {
    public PersonDAO() {
        super(Person.class);
    }

    @Override
    public void initializeLazyRelations(Person obj) {
        initializeEventRelation(obj);
        initializeTripPartecipationRelation(obj);
    }

    public void initializeEventRelation(Person obj){
        Hibernate.initialize(obj.getCheckpoints());
    }

    public void initializeTripPartecipationRelation(Person obj){
        Hibernate.initialize(obj.getTripPartecipations());
    }
}
