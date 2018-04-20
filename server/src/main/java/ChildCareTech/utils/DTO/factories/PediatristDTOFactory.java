package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.PediatristDTO;
import ChildCareTech.model.entities.Kid;
import ChildCareTech.model.entities.Pediatrist;
import ChildCareTech.utils.DTO.DTOFactory;
import org.hibernate.Hibernate;

import java.util.HashSet;
import java.util.Set;

public class PediatristDTOFactory implements AbstractDTOFactory<Pediatrist, PediatristDTO> {
    @Override
    public PediatristDTO getDTO(Pediatrist entity) {
        PediatristDTO dto = getPediatristDTO(entity);
        if (dto == null) return null;

        loadContactRelationship(entity, dto);

        loadKidRelationship(entity, dto);

        return dto;
    }

    private static void loadContactRelationship(Pediatrist entity, PediatristDTO dto) {
        Set<KidDTO> contacts = new HashSet<>();

        if(Hibernate.isInitialized(entity.getContacts()))
            for (Kid k : entity.getContacts())
                contacts.add(KidDTOFactory.getAdultContactsManySide(k));

        dto.setContacts(contacts);
    }

    public static PediatristDTO getKidOneSide(Pediatrist entity){
        return getPediatristDTO(entity);
    }

    private static PediatristDTO getPediatristDTO(Pediatrist entity) {
        if (entity == null)
            return null;

        return new PediatristDTO(
                entity.getId(),
                DTOFactory.getDTO(entity.getPerson()),
                null,
                null
        );
    }

    private static void loadKidRelationship(Pediatrist entity, PediatristDTO dto) {
        Set<KidDTO> kids = new HashSet<>();

        if(Hibernate.isInitialized(entity.getKids()))
            for (Kid k : entity.getKids())
                kids.add(KidDTOFactory.getPediatristManySide(k, dto));

        dto.setKids(kids);
    }
}

