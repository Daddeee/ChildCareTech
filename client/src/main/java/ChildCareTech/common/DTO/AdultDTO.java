package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

public class AdultDTO implements Serializable {
    private int id;
    private PersonDTO person;
    private Set<KidDTO> contacts;

    public AdultDTO(int id, PersonDTO person, Set<KidDTO> contacts) {
        this.id = id;
        this.person = person;
        this.contacts = contacts == null ? Collections.EMPTY_SET : contacts;
    }

    public int getId() {
        return id;
    }

    public PersonDTO getPerson() {
        return person;
    }

    public Set<KidDTO> getContacts() {
        return contacts;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    public void setContacts(Set<KidDTO> contacts) {
        this.contacts = contacts;
    }
}