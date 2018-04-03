package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.rmi.Remote;
import java.util.Set;

public interface PediatristDTO extends Serializable, AdultDTO, Remote {
    Set<KidDTO> getKids();
}