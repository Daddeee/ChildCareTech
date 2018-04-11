package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.StaffDTO;
import ChildCareTech.model.entities.Kid;
import ChildCareTech.model.entities.Staff;
import ChildCareTech.utils.DTO.DTOFactory;

import java.util.HashSet;
import java.util.Set;

public class StaffDTOFactory implements AbstractDTOFactory<Staff, StaffDTO> {
    @Override
    public StaffDTO getDTO(Staff entity) {
        if (entity == null)
            return null;

        StaffDTO dto = new StaffDTO(
                entity.getId(),
                DTOFactory.getDTO(entity.getPerson()),
                null
        );

        Set<KidDTO> contacts = new HashSet<>();
        for (Kid k : entity.getContacts())
            contacts.add(KidDTOFactory.getAdultContactsManySide(k));
        dto.setContacts(contacts);

        return dto;
    }
}

