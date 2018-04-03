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

    public KidDTOImpl(PersonDTO person, AdultDTO tutorOne, AdultDTO tutorTwo, PediatristDTO pediatrist, Set<AdultDTO> contacts) {
        this.person = person;
        this.tutorOne = tutorOne;
        this.tutorTwo = tutorTwo;
        this.pediatrist = pediatrist;
        this.contacts = contacts;
    }

    public KidDTOImpl(PersonDTO person, AdultDTO tutorOne, PediatristDTO pediatrist, Set<AdultDTO> contacts) {
        this(person, tutorOne, null, pediatrist, contacts);
    }

    public PersonDTO getPerson() {
        return person;
    }

    public AdultDTO getFirstTutor() {
        return tutorOne;
    }

    public AdultDTO getSecondTutor() {
        return tutorTwo;
    }

    public PediatristDTO getPediatrist() {
        return pediatrist;
    }

    public Set<AdultDTO> getContacts() {
        return contacts;
    }
}
