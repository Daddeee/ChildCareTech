package ChildCareTech.common.DTO;

import java.util.Set;

public interface PediatristDTO {
    PersonDTO getPerson();
    Set<KidDTO> getKids();
}
