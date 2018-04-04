package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.util.Set;

public class KidDTO implements Serializable {
    private PersonDTO person;
    private AdultDTO firstTutor;
    private AdultDTO secondTutor;
    private PediatristDTO pediatrist;
    private Set<AdultDTO> contacts;

    public KidDTO(PersonDTO person, AdultDTO firstTutor, AdultDTO secondTutor, PediatristDTO pediatrist, Set<AdultDTO> contacts) {
        this.person = person;
        this.firstTutor = firstTutor;
        this.secondTutor = secondTutor;
        this.pediatrist = pediatrist;
        this.contacts = contacts;
    }

    public PersonDTO getPerson() {
        return person;
    }

    public AdultDTO getFirstTutor() {
        return firstTutor;
    }

    public AdultDTO getSecondTutor() {
        return secondTutor;
    }

    public PediatristDTO getPediatrist() {
        return pediatrist;
    }

    public Set<AdultDTO> getContacts() {
        return contacts;
    }
}