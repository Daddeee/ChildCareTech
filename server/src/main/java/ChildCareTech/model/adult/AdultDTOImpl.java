package ChildCareTech.model.adult;

import ChildCareTech.common.DTO.AdultDTO;
import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.model.kid.Kid;
import ChildCareTech.model.kid.KidDTOImpl;
import ChildCareTech.model.person.PersonDTOImpl;
import ChildCareTech.utils.DTOFactory;

import java.util.HashSet;
import java.util.Set;

public class AdultDTOImpl implements AdultDTO {
    private PersonDTO person;
    private Set<KidDTO> contacts;

    public AdultDTOImpl(Adult adult){
        person = DTOFactory.getDTO(adult.getPerson());
        contacts = new HashSet<>();
        for(Kid k : adult.getContacts())
            contacts.add(DTOFactory.getDTO(k));
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