package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.AdultDTO;
import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.model.adult.Adult;
import ChildCareTech.model.kid.Kid;
import ChildCareTech.utils.DTO.DTOEntityAssembler;

import java.util.HashSet;
import java.util.Set;

public class AdultDTOEntityAssembler implements AbstractDTOEntityAssembler<Adult, AdultDTO> {
    @Override
    public Adult assemble(AdultDTO dto) {
        if(dto == null)
            return null;

        Adult entity = new Adult(
                DTOEntityAssembler.getEntity(dto.getPerson())
        );

        Set<Kid> contacts = new HashSet<>();
        for(KidDTO k : dto.getContacts())
            contacts.add(KidDTOEntityAssembler.assembleAdultManySide(k));
        entity.setContacts(contacts);

        return entity;
    }
}
