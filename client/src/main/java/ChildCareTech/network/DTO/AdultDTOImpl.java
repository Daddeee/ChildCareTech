package ChildCareTech.network.DTO;

import ChildCareTech.common.DTO.AdultDTO;
import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.PersonDTO;

import java.util.Set;

public class AdultDTOImpl implements AdultDTO {
    private PersonDTO person;
    private Set<KidDTO> contacts;

    public AdultDTOImpl(PersonDTO person, Set<KidDTO> contacts) {
        this.contacts = contacts;
        this.person = person;
    }

    @Override
    public PersonDTO getPerson() {
        return person;
    }

    @Override
    public Set<KidDTO> getContacts() {
        return contacts;
    }
}
