package ChildCareTech.network.DTO;

import ChildCareTech.common.DTO.AdultDTO;
import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.PediatristDTO;
import ChildCareTech.common.DTO.PersonDTO;

import java.util.Set;

public class KidDTOImpl implements KidDTO {
    private PersonDTO person;
    private AdultDTO tutorOne;
    private AdultDTO tutorTwo;
    private PediatristDTO pediatrist;
    private Set<AdultDTO> contacts;

    public KidDTOImpl() { }

    public PersonDTO getPerson() { return person; }

    public void setPerson(PersonDTO person) { this.person = person; }

    public AdultDTO getTutorOne() { return tutorOne; }

    public void setTutorOne(AdultDTO tutorOne) { this.tutorOne = tutorOne; }

    public AdultDTO getTutorTwo() { return tutorTwo; }

    public void setTutorTwo(AdultDTO tutorTwo) { this.tutorTwo = tutorTwo; }

    public PediatristDTO getPediatrist() { return pediatrist; }

    public void setPediatrist(PediatristDTO pediatrist) { this.pediatrist =pediatrist; }

    public Set<AdultDTO> getContacts() { return contacts; }

    public void setContacts(Set<AdultDTO> contacts) { this.contacts = contacts; }
}
