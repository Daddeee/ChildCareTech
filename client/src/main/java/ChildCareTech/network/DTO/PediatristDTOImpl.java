package ChildCareTech.network.DTO;

import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.PediatristDTO;
import ChildCareTech.common.DTO.PersonDTO;

import java.util.Set;

public class PediatristDTOImpl implements PediatristDTO {
    private PersonDTO person;
    private Set<KidDTO> kids;

    @Override
    public PersonDTO getPerson() { return person; }

    @Override
    public void setPerson(PersonDTO person) { this.person = person; }

    @Override
    public Set<KidDTO> getKids() { return kids; }

    @Override
    public void setKids(Set<KidDTO> kids) { this.kids = kids; }

}
