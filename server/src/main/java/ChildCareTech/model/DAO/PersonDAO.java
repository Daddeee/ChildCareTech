package ChildCareTech.model.DAO;

import ChildCareTech.model.entities.Person;
import ChildCareTech.utils.AbstractGenericDAO;
import org.hibernate.Hibernate;

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

    public void initializeEventRelation(Person obj){
        Hibernate.initialize(obj.getCheckpoints());
    }

    public void initializeTripPartecipationRelation(Person obj){
        Hibernate.initialize(obj.getTripPartecipations());
    }
}
