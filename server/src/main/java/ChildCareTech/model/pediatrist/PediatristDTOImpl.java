package ChildCareTech.model.pediatrist;

import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.PediatristDTO;
import ChildCareTech.model.adult.AdultDTOImpl;
import ChildCareTech.model.kid.Kid;
import ChildCareTech.utils.DTOFactory;

import java.util.HashSet;
import java.util.Set;

public class PediatristDTOImpl extends AdultDTOImpl implements PediatristDTO {
    private Set<KidDTO> kids;

    public PediatristDTOImpl(Pediatrist pediatrist) {
        super(pediatrist);
        kids = new HashSet<>();
        for (Kid k : pediatrist.getKids())
            kids.add(DTOFactory.getDTO(k));
    }

    @Override
    public Set<KidDTO> getKids() {
        return kids;
    }
}