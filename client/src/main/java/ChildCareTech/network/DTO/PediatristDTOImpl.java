package ChildCareTech.network.DTO;

import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.PediatristDTO;
import ChildCareTech.common.DTO.PersonDTO;

import java.util.Set;

public class PediatristDTOImpl implements PediatristDTO {
    private PersonDTO person;
    private Set<KidDTO> kids;

    public PediatristDTOImpl(PersonDTO person, Set<KidDTO> kids) {
        this.person = person;
        this.kids = kids;
    }

    public PediatristDTOImpl(PersonDTO person) {
        this(person, null);
    }

    @Override
    public PersonDTO getPerson() { return person; }

    @Override
    public Set<KidDTO> getKids() { return kids; }
}
