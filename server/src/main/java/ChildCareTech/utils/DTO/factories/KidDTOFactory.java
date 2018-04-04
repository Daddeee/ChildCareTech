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

        PersonDTO person = DTOFactory.getDTO(entity.getPerson());
        AdultDTO firstTutor = DTOFactory.getDTO(entity.getFirstTutor());
        AdultDTO secondTutor = DTOFactory.getDTO(entity.getSecondTutor());
        PediatristDTO pediatrist = DTOFactory.getDTO(entity.getPediatrist());

        Set<AdultDTO> contacts = new HashSet<>();
        for (Adult a : entity.getContacts())
            contacts.add(DTOFactory.getDTO(a));

        return new KidDTO(person, firstTutor, secondTutor, pediatrist, contacts);
    }
}

