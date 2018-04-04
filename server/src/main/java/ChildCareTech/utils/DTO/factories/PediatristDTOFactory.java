package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.PediatristDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.model.kid.Kid;
import ChildCareTech.model.pediatrist.Pediatrist;
import ChildCareTech.utils.DTO.DTOFactory;

import java.util.HashSet;
import java.util.Set;

public class PediatristDTOFactory implements AbstractDTOFactory<Pediatrist, PediatristDTO> {
    @Override
    public PediatristDTO getDTO(Pediatrist entity) {
        if (entity == null)
            return null;

        PersonDTO person = DTOFactory.getDTO(entity.getPerson());
        Set<KidDTO> contacts = new HashSet<>();
        Set<KidDTO> kids = new HashSet<>();

        for (Kid k : entity.getContacts())
            contacts.add(DTOFactory.getDTO(k));

        for (Kid k : entity.getKids())
            kids.add(DTOFactory.getDTO(k));

        return new PediatristDTO(person, contacts, kids);
    }
}

