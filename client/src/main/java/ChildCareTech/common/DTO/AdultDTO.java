package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.rmi.Remote;
import java.util.Set;

public interface AdultDTO extends Serializable, Remote {
    PersonDTO getPerson();
    Set<KidDTO> getContacts();
}
