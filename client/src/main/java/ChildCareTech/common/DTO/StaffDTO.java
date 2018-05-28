package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.util.Set;

/**
 * This class provides a Data Transfer Object encapsulation for Staff entity.
 */
public class StaffDTO extends AdultDTO implements Serializable {
    public StaffDTO(int id, PersonDTO person, Set<KidDTO> contacts) {
        super(id, person, contacts);
    }
}