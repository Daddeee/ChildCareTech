package ChildCareTech.common.DTO;

import java.util.Set;

public interface AdultDTO {
    PersonDTO getPerson();
    Set<KidDTO> getContacts();
}
