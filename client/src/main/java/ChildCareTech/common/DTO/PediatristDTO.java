package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.util.Set;

public interface PediatristDTO extends Serializable{
    PersonDTO getPerson();
    Set<KidDTO> getKids();
}
