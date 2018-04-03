package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.util.Set;

public interface AdultDTO extends Serializable {
    PersonDTO getPerson();
    Set<KidDTO> getContacts();
}
