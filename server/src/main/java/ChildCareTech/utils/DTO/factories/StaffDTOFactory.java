package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.DTO.StaffDTO;
import ChildCareTech.model.kid.Kid;
import ChildCareTech.model.staff.Staff;
import ChildCareTech.utils.DTO.DTOFactory;

import java.util.HashSet;
import java.util.Set;

public class StaffDTOFactory implements AbstractDTOFactory<Staff, StaffDTO> {
    @Override
    public StaffDTO getDTO(Staff entity) {
        if(entity == null)
            return null;

        PersonDTO person = DTOFactory.getDTO(entity.getPerson());

        Set<KidDTO> contacts = new HashSet<>();
        for(Kid k : entity.getContacts())
            contacts.add(DTOFactory.getDTO(k));

        return new StaffDTO(person, contacts);
    }
}

