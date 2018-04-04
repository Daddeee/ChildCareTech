package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.AdultDTO;
import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.model.adult.Adult;
import ChildCareTech.model.kid.Kid;
import ChildCareTech.utils.DTO.DTOFactory;

import java.util.HashSet;
import java.util.Set;

public class AdultDTOFactory implements AbstractDTOFactory<Adult, AdultDTO> {
    @Override
    public AdultDTO getDTO(Adult entity) {
        if(entity == null)
            return null;

        PersonDTO person = DTOFactory.getDTO(entity.getPerson());

        Set<KidDTO> contacts = new HashSet<>();
        for(Kid k : entity.getContacts())
            contacts.add(DTOFactory.getDTO(k));

        return new AdultDTO(person, contacts);
    }
}

