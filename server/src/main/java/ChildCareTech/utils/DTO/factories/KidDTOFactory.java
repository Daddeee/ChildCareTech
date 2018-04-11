package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.AdultDTO;
import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.PediatristDTO;
import ChildCareTech.model.entities.Adult;
import ChildCareTech.model.entities.Kid;
import ChildCareTech.utils.DTO.DTOFactory;

import java.util.HashSet;
import java.util.Set;

public class KidDTOFactory implements AbstractDTOFactory<Kid, KidDTO> {
    @Override
    public KidDTO getDTO(Kid entity) {
        if (entity == null) return null;
        KidDTO dto = getKidDTO(entity, PediatristDTOFactory.getKidOneSide(entity.getPediatrist()));


        Set<AdultDTO> contacts = new HashSet<>();
        for (Adult a : entity.getContacts())
            contacts.add(AdultDTOFactory.getKidContactsManySide(a));
        dto.setContacts(contacts);

        return dto;
    }

    public static KidDTO getAdultContactsManySide(Kid entity){
        if(entity == null) return null;
        return getKidDTO(entity, PediatristDTOFactory.getKidOneSide(entity.getPediatrist()));
    }

    public static KidDTO getPediatristManySide(Kid entity, PediatristDTO pediatristDTO){
        return getKidDTO(entity, pediatristDTO);
    }

    private static KidDTO getKidDTO(Kid entity, PediatristDTO pediatristDTO) {
        if (entity == null)
            return null;

        return new KidDTO(
                entity.getId(),
                DTOFactory.getDTO(entity.getPerson()),
                AdultDTOFactory.getKidContactsManySide(entity.getFirstTutor()),
                AdultDTOFactory.getKidContactsManySide(entity.getSecondTutor()),
                pediatristDTO,
                null
        );
    }
}

