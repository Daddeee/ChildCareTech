package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.AdultDTO;
import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.model.adult.Adult;
import ChildCareTech.model.kid.Kid;
import ChildCareTech.utils.DTO.DTOEntityAssembler;

import java.util.HashSet;
import java.util.Set;

public class KidDTOEntityAssembler implements AbstractDTOEntityAssembler<Kid, KidDTO> {
    @Override
    public Kid assemble(KidDTO dto) {
        Kid entity = getKid(dto);
        if (entity == null) return null;

        Set<Adult> contacts = new HashSet<>();
        for(AdultDTO a : dto.getContacts())
            contacts.add(DTOEntityAssembler.getEntity(a));
        entity.setContacts(contacts);

        return entity;
    }

    public static Kid assembleAdultManySide(KidDTO dto){
        return getKid(dto);
    }

    private static Kid getKid(KidDTO dto) {
        if(dto == null)
            return null;

        Kid entity = new Kid(
                DTOEntityAssembler.getEntity(dto.getPerson()),
                DTOEntityAssembler.getEntity(dto.getFirstTutor()),
                DTOEntityAssembler.getEntity(dto.getSecondTutor()),
                DTOEntityAssembler.getEntity(dto.getPediatrist())
        );
        return entity;
    }
}
