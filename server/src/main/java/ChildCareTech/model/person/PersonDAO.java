package ChildCareTech.model.person;

import ChildCareTech.utils.AbstractGenericDAO;

public class PersonDAO extends AbstractGenericDAO<Person, String> {
    public PersonDAO() {
        super(Person.class);
    }
}
