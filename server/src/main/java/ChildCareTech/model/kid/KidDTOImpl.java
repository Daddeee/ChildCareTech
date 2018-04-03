package ChildCareTech.model.kid;

import ChildCareTech.common.DTO.AdultDTO;
import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.PediatristDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.model.adult.Adult;
import ChildCareTech.model.adult.AdultDTOImpl;
import ChildCareTech.model.pediatrist.PediatristDTOImpl;
import ChildCareTech.model.person.PersonDTOImpl;
import ChildCareTech.utils.DTOFactory;

import java.util.HashSet;
import java.util.Set;

public class KidDTOImpl implements KidDTO {
    private PersonDTO person;
    private AdultDTO firstTutor;
    private AdultDTO secondTutor;
    private PediatristDTO pediatrist;
    private Set<AdultDTO> contacts;

    public KidDTOImpl(Kid kid) {
        person = DTOFactory.getDTO(kid.getPerson());
        firstTutor = DTOFactory.getDTO(kid.getFirstTutor());
        secondTutor = DTOFactory.getDTO(kid.getSecondTutor());
        pediatrist = DTOFactory.getDTO(kid.getPediatrist());
        contacts = new HashSet<>();
        for(Adult a : kid.getContacts())
            contacts.add(DTOFactory.getDTO(a));
    }

    @Override
    public PersonDTO getPerson() {
        return person;
    }

    @Override
    public AdultDTO getFirstTutor() {
        return firstTutor;
    }

    @Override
    public AdultDTO getSecondTutor() {
        return secondTutor;
    }

    @Override
    public PediatristDTO getPediatrist() {
        return pediatrist;
    }

    @Override
    public Set<AdultDTO> getContacts() {
        return contacts;
    }
}