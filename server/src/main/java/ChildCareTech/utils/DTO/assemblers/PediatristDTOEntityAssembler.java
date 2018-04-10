package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.PediatristDTO;
import ChildCareTech.model.entities.Kid;
import ChildCareTech.model.entities.Pediatrist;
import ChildCareTech.utils.DTO.DTOEntityAssembler;

import java.util.HashSet;
import java.util.Set;

public class PediatristDTOEntityAssembler implements AbstractDTOEntityAssembler<Pediatrist, PediatristDTO> {
    @Override
    public Pediatrist assemble(PediatristDTO dto) {
        if(dto == null)
            return null;

        Pediatrist entity = new Pediatrist(
                DTOEntityAssembler.getEntity(dto.getPerson())
        );

        Set<Kid> contacts = new HashSet<>();
        for(KidDTO k : dto.getContacts())
            contacts.add(KidDTOEntityAssembler.assembleAdultManySide(k));
        entity.setContacts(contacts);

        Set<Kid> kids = new HashSet<>();
        for(KidDTO e : dto.getKids())
            kids.add(KidDTOEntityAssembler.assemblePediatristManySide(e, entity));
        entity.setKids(kids);

        return entity;
    }

    public static Pediatrist assembleKidOneSide(PediatristDTO dto){
        if(dto == null)
            return null;

        Pediatrist entity = new Pediatrist(
                DTOEntityAssembler.getEntity(dto.getPerson())
        );

        Set<Kid> contacts = new HashSet<>();
        for(KidDTO e : dto.getContacts())
            contacts.add(DTOEntityAssembler.getEntity(e));
        entity.setContacts(contacts);

        return entity;
    }
}
