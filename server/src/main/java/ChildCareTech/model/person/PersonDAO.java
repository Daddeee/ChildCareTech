package ChildCareTech.model.person;

import ChildCareTech.utils.GenericDAO;

public class PersonDAO extends GenericDAO<Person, String> {
    public PersonDAO() {
        super(Person.class);
    }
}
