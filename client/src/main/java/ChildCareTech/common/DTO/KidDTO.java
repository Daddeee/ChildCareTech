package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.util.Set;

public interface KidDTO extends Serializable{
    PersonDTO getPerson();
    AdultDTO getFirstTutor();
    AdultDTO getSecondTutor();
    PediatristDTO getPediatrist();
    Set<AdultDTO> getContacts();
}
