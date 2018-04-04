package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.util.Set;

public class AdultDTO implements Serializable {
    private PersonDTO person;
    private Set<KidDTO> contacts;

    public AdultDTO(PersonDTO person, Set<KidDTO> contacts) {
        this.person = person;
        this.contacts = contacts;
    }

    public PersonDTO getPerson() {
        return person;
    }

    public Set<KidDTO> getContacts() {
        return contacts;
    }
}