package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.SupplierDTO;
import ChildCareTech.common.DTO.SupplyDTO;
import ChildCareTech.model.entities.Kid;
import ChildCareTech.model.entities.Supplier;
import ChildCareTech.model.entities.Supply;
import ChildCareTech.utils.DTO.DTOFactory;

import java.util.HashSet;
import java.util.Set;

public class SupplierDTOFactory implements AbstractDTOFactory<Supplier, SupplierDTO> {
    @Override
    public SupplierDTO getDTO(Supplier entity) {
        if (entity == null)
            return null;

        SupplierDTO dto = new SupplierDTO(
                DTOFactory.getDTO(entity.getPerson()),
                null,
                null
        );

        Set<KidDTO> contacts = new HashSet<>();
        for (Kid k : entity.getContacts())
            contacts.add(KidDTOFactory.getAdultContactsManySide(k));
        dto.setContacts(contacts);


        Set<SupplyDTO> supplies = new HashSet<>();
        for (Supply s : entity.getSupplies())
            supplies.add(SupplyDTOFactory.getSupplierManySide(s, dto));
        dto.setSupplies(supplies);

        return dto;
    }

    public static SupplierDTO getSupplyOneSide(Supplier entity){
        if (entity == null)
            return null;

        SupplierDTO dto = new SupplierDTO(
                DTOFactory.getDTO(entity.getPerson()),
                null,
                null
        );

        Set<KidDTO> contacts = new HashSet<>();
        for (Kid k : entity.getContacts())
            contacts.add(KidDTOFactory.getAdultContactsManySide(k));
        dto.setContacts(contacts);

        return dto;
    }
}

