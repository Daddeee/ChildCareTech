package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.AdultDTO;
import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.model.entities.Adult;
import ChildCareTech.model.entities.Kid;
import ChildCareTech.model.entities.Pediatrist;
import ChildCareTech.utils.DTO.DTOEntityAssembler;

import java.util.HashSet;
import java.util.Set;

public class KidDTOEntityAssembler implements AbstractDTOEntityAssembler<Kid, KidDTO> {
    @Override
    public Kid assemble(KidDTO dto) {
        if(dto == null)
            return null;

        Kid entity = new Kid(
                DTOEntityAssembler.getEntity(dto.getPerson()),
                AdultDTOEntityAssembler.assembleKidManySide(dto.getFirstTutor()),
                AdultDTOEntityAssembler.assembleKidManySide(dto.getSecondTutor()),
                PediatristDTOEntityAssembler.assembleKidOneSide(dto.getPediatrist())
        );

        Set<Adult> contacts = new HashSet<>();
        for(AdultDTO a : dto.getContacts())
            contacts.add(AdultDTOEntityAssembler.assembleKidManySide(a));
        entity.setContacts(contacts);

        return entity;
    }

    public static Kid assembleAdultManySide(KidDTO dto){
        if(dto == null)
            return null;

        Kid entity = new Kid(
                DTOEntityAssembler.getEntity(dto.getPerson()),
                DTOEntityAssembler.getEntity(dto.getFirstTutor()),
                DTOEntityAssembler.getEntity(dto.getSecondTutor()),
                PediatristDTOEntityAssembler.assembleKidOneSide(dto.getPediatrist())
        );
        return entity;
    }

    public static Kid assemblePediatristManySide(KidDTO dto, Pediatrist pediatrist){
        if(dto == null)
            return null;

        Kid entity = new Kid(
                DTOEntityAssembler.getEntity(dto.getPerson()),
                DTOEntityAssembler.getEntity(dto.getFirstTutor()),
                DTOEntityAssembler.getEntity(dto.getSecondTutor()),
                pediatrist
        );
        return entity;
    }
}
