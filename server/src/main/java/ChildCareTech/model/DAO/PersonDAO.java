package ChildCareTech.model.DAO;

import ChildCareTech.model.entities.Person;
import ChildCareTech.model.AbstractGenericDAO;
import org.hibernate.Hibernate;

/**
 * A Data Access Object that operates with Person entities.
 */
public class PersonDAO extends AbstractGenericDAO<Person, Integer> {
    public PersonDAO() {
        super(Person.class);
    }

    @Override
    public void initializeLazyRelations(Person obj) {
        initializeEventRelation(obj);
        initializeTripPartecipationRelation(obj);
        initializellergiesRelation(obj);
    }

    public void initializellergiesRelation(Person obj) { Hibernate.initialize(obj.getAllergies()); }

    private void initializeEventRelation(Person obj){
        Hibernate.initialize(obj.getCheckpoints());
    }

    private void initializeTripPartecipationRelation(Person obj){
        Hibernate.initialize(obj.getTripPartecipations());
    }
}
