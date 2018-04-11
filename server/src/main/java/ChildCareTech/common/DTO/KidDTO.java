package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

public class KidDTO implements Serializable {
    private int id;
    private PersonDTO person;
    private AdultDTO firstTutor;
    private AdultDTO secondTutor;
    private PediatristDTO pediatrist;
    private Set<AdultDTO> contacts;

    public KidDTO(int id, PersonDTO person, AdultDTO firstTutor, AdultDTO secondTutor, PediatristDTO pediatrist, Set<AdultDTO> contacts) {
        this.id = id;
        this.person = person;
        this.firstTutor = firstTutor;
        this.secondTutor = secondTutor;
        this.pediatrist = pediatrist;
        this.contacts = contacts == null ? Collections.EMPTY_SET : contacts;
    }

    public int getId() {
        return id;
    }

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    public AdultDTO getFirstTutor() {
        return firstTutor;
    }

    public void setFirstTutor(AdultDTO firstTutor) {
        this.firstTutor = firstTutor;
    }

    public AdultDTO getSecondTutor() {
        return secondTutor;
    }

    public void setSecondTutor(AdultDTO secondTutor) {
        this.secondTutor = secondTutor;
    }

    public PediatristDTO getPediatrist() {
        return pediatrist;
    }

    public void setPediatrist(PediatristDTO pediatrist) {
        this.pediatrist = pediatrist;
    }

    public Set<AdultDTO> getContacts() {
        return contacts;
    }

    public void setContacts(Set<AdultDTO> contacts) {
        this.contacts = contacts;
    }
}