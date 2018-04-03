package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.rmi.Remote;
import java.util.Set;

public interface KidDTO extends Serializable, Remote {
    PersonDTO getPerson();
    AdultDTO getFirstTutor();
    AdultDTO getSecondTutor();
    PediatristDTO getPediatrist();
    Set<AdultDTO> getContacts();
}