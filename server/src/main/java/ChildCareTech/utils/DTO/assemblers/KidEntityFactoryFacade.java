package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.AdultDTO;
import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.model.entities.Adult;
import ChildCareTech.model.entities.Kid;
import ChildCareTech.model.entities.Pediatrist;
import ChildCareTech.utils.DTO.EntityFactoryFacade;

import java.util.HashSet;
import java.util.Set;

public class KidEntityFactoryFacade implements AbstractEntityFactoryFacade<Kid, KidDTO> {
    @Override
    public Kid assemble(KidDTO dto) {
        if(dto == null) return null;
        Kid entity = getKid(dto, PediatristEntityFactoryFacade.assembleKidOneSide(dto.getPediatrist()));

        Set<Adult> contacts = new HashSet<>();
        for(AdultDTO a : dto.getContacts())
            contacts.add(AdultEntityFactoryFacade.assembleKidManySide(a));
        entity.setContacts(contacts);

        return entity;
    }

    public static Kid assembleAdultManySide(KidDTO dto){
        if(dto == null) return null;
        return getKid(dto, PediatristEntityFactoryFacade.assembleKidOneSide(dto.getPediatrist()));
    }

    public static Kid assemblePediatristManySide(KidDTO dto, Pediatrist pediatrist){
        return getKid(dto, pediatrist);
    }

    private static Kid getKid(KidDTO dto, Pediatrist pediatrist) {
        if(dto == null)
            return null;

        return new Kid(
                dto.getId(),
                EntityFactoryFacade.getEntity(dto.getPerson()),
                EntityFactoryFacade.getEntity(dto.getFirstTutor()),
                EntityFactoryFacade.getEntity(dto.getSecondTutor()),
                pediatrist
        );
    }
}
