package ChildCareTech.common.DTO;

import java.util.Set;

public interface AdultDTO {
    void setPerson(PersonDTO person);
    void setContacts(Set<KidDTO> contacts);

    PersonDTO getPerson();
    Set<KidDTO> getContacts();
}
