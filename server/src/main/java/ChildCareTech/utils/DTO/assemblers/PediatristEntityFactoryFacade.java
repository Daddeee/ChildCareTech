package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.PediatristDTO;
import ChildCareTech.model.entities.Kid;
import ChildCareTech.model.entities.Pediatrist;
import ChildCareTech.utils.DTO.EntityFactoryFacade;

import java.util.HashSet;
import java.util.Set;

public class PediatristEntityFactoryFacade implements AbstractEntityFactoryFacade<Pediatrist, PediatristDTO> {
    @Override
    public Pediatrist assemble(PediatristDTO dto) {
        Pediatrist entity = getPediatrist(dto);
        if (entity == null) return null;

        Set<Kid> contacts = new HashSet<>();
        for(KidDTO k : dto.getContacts())
            contacts.add(KidEntityFactoryFacade.assembleAdultManySide(k));
        entity.setContacts(contacts);

        Set<Kid> kids = new HashSet<>();
        for(KidDTO e : dto.getKids())
            kids.add(KidEntityFactoryFacade.assemblePediatristManySide(e, entity));
        entity.setKids(kids);

        return entity;
    }

    public static Pediatrist assembleKidOneSide(PediatristDTO dto){
        Pediatrist entity = getPediatrist(dto);
        if (entity == null) return null;

        Set<Kid> contacts = new HashSet<>();
        for(KidDTO e : dto.getContacts())
            contacts.add(EntityFactoryFacade.getEntity(e));
        entity.setContacts(contacts);

        return entity;
    }

    private static Pediatrist getPediatrist(PediatristDTO dto) {
        if(dto == null)
            return null;

        Pediatrist entity = new Pediatrist(
                dto.getId(),
                EntityFactoryFacade.getEntity(dto.getPerson()),
                null
        );
        return entity;
    }
}
