package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.AdultDTO;
import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.model.entities.Adult;
import ChildCareTech.model.entities.Kid;
import ChildCareTech.utils.DTO.DTOEntityAssembler;

import java.util.HashSet;
import java.util.Set;

public class AdultDTOEntityAssembler implements AbstractDTOEntityAssembler<Adult, AdultDTO> {
    @Override
    public Adult assemble(AdultDTO dto) {
        Adult entity = getAdult(dto);
        if(entity == null) return null;


        Set<Kid> contacts = new HashSet<>();
        for(KidDTO k : dto.getContacts())
            contacts.add(KidDTOEntityAssembler.assembleAdultManySide(k));
        entity.setContacts(contacts);

        return entity;
    }

    public static Adult assembleKidManySide(AdultDTO dto) {
        return getAdult(dto);
    }

    private static Adult getAdult(AdultDTO dto) {
        if(dto == null)
            return null;

        return new Adult(
                dto.getId(),
                DTOEntityAssembler.getEntity(dto.getPerson())
        );
    }
}
