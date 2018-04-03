package ChildCareTech.common.DTO;

import java.util.Set;

public interface KidDTO {
    PersonDTO getPerson();
    AdultDTO getTutorOne();
    AdultDTO getTutorTwo();
    PediatristDTO getPediatrist();
    Set<AdultDTO> getContacts();

    void setPerson(PersonDTO person);
    void setTutorOne(AdultDTO tutorOne);
    void setTutorTwo(AdultDTO tutorTwo);
    void setPediatrist(PediatristDTO pediatrist);
    void setContacts(Set<AdultDTO> contacts);

}
