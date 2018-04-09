package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.AdultDTO;
import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.PediatristDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.model.adult.Adult;
import ChildCareTech.model.kid.Kid;
import ChildCareTech.utils.DTO.DTOFactory;

import java.util.HashSet;
import java.util.Set;

public class KidDTOFactory implements AbstractDTOFactory<Kid, KidDTO> {
    @Override
    public KidDTO getDTO(Kid entity) {
        if (entity == null)
            return null;

        KidDTO dto = new KidDTO(
                DTOFactory.getDTO(entity.getPerson()),
                AdultDTOFactory.getKidContactsManySide(entity.getFirstTutor()),
                AdultDTOFactory.getKidContactsManySide(entity.getSecondTutor()),
                DTOFactory.getDTO(entity.getPediatrist()),
                null
        );

        Set<AdultDTO> contacts = new HashSet<>();
        for (Adult a : entity.getContacts())
            contacts.add(AdultDTOFactory.getKidContactsManySide(a));
        dto.setContacts(contacts);

        return dto;
    }

    public static KidDTO getAdultContactsManySide(Kid entity){
        if (entity == null)
            return null;

        return new KidDTO(
                DTOFactory.getDTO(entity.getPerson()),
                AdultDTOFactory.getKidContactsManySide(entity.getFirstTutor()),
                AdultDTOFactory.getKidContactsManySide(entity.getSecondTutor()),
                DTOFactory.getDTO(entity.getPediatrist()),
                null
        );
    }
}

