package ChildCareTech.common.DTO;

import ChildCareTech.model.staff.Staff;

import java.io.Serializable;
import java.util.Set;

public class StaffDTO extends AdultDTO implements Serializable {
    public StaffDTO(PersonDTO person, Set<KidDTO> contacts){
        super(person, contacts);
    }
}