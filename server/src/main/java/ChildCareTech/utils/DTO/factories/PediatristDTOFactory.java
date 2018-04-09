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

        PediatristDTO dto = new PediatristDTO(
                DTOFactory.getDTO(entity.getPerson()),
                null,
                null
        );

        Set<KidDTO> contacts = new HashSet<>();
        for (Kid k : entity.getContacts())
            contacts.add(KidDTOFactory.getAdultContactsManySide(k));
        dto.setContacts(contacts);

        Set<KidDTO> kids = new HashSet<>();
        for (Kid k : entity.getKids())
            kids.add(KidDTOFactory.getPediatristManySide(k, dto));
        dto.setKids(kids);

        return dto;
    }

    public static PediatristDTO getKidOneSide(Pediatrist entity){
        if (entity == null)
            return null;

        return new PediatristDTO(
                DTOFactory.getDTO(entity.getPerson()),
                null,
                null
        );
    }
}

