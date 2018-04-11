package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.AdultDTO;
import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.model.entities.Adult;
import ChildCareTech.model.entities.Kid;
import ChildCareTech.utils.DTO.DTOFactory;

import java.util.HashSet;
import java.util.Set;

public class AdultDTOFactory implements AbstractDTOFactory<Adult, AdultDTO> {
    @Override
    public AdultDTO getDTO(Adult entity) {
        AdultDTO dto = getAdultDTO(entity);
        if(dto == null) return null;

        Set<KidDTO> contacts = new HashSet<>();
        for (Kid k : entity.getContacts())
            contacts.add(KidDTOFactory.getAdultContactsManySide(k));
        dto.setContacts(contacts);

        return dto;
    }

    public static AdultDTO getKidContactsManySide(Adult entity){
        return getAdultDTO(entity);
    }

    private static AdultDTO getAdultDTO(Adult entity) {
        if (entity == null)
            return null;

        return new AdultDTO(
                entity.getId(),
                DTOFactory.getDTO(entity.getPerson()),
                null
        );
    }
}

