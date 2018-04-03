package ChildCareTech.common.DTO;

import java.util.Set;

public interface KidDTO {
    PersonDTO getPerson();
    AdultDTO getFirstTutor();
    AdultDTO getSecondTutor();
    PediatristDTO getPediatrist();
    Set<AdultDTO> getContacts();
}
