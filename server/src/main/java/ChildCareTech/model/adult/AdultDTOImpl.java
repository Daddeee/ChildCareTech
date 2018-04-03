package ChildCareTech.model.adult;

import ChildCareTech.common.DTO.AdultDTO;
import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.model.kid.Kid;
import ChildCareTech.model.kid.KidDTOImpl;
import ChildCareTech.model.person.PersonDTOImpl;

import java.util.HashSet;
import java.util.Set;

public class AdultDTOImpl implements AdultDTO {
    private PersonDTO person;
    private Set<KidDTO> contacts;

    public AdultDTOImpl(Adult adult){
        person = new PersonDTOImpl(adult.getPerson());
        contacts = new HashSet<>();
        for(Kid k : adult.getContacts())
            contacts.add(new KidDTOImpl(k));
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