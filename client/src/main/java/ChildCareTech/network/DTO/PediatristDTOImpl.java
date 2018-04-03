package ChildCareTech.network.DTO;

import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.PediatristDTO;
import ChildCareTech.common.DTO.PersonDTO;

import java.util.Set;

public class PediatristDTOImpl extends AdultDTOImpl implements PediatristDTO {
    private Set<KidDTO> kids;

    public PediatristDTOImpl(PersonDTO person, Set<KidDTO> contacts, Set<KidDTO> kids) {
        super(person, contacts);
        this.kids = kids;
    }

    @Override
    public Set<KidDTO> getKids() { return kids; }
}
