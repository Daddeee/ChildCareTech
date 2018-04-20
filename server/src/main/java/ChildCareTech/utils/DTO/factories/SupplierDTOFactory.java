package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.SupplierDTO;
import ChildCareTech.common.DTO.SupplyDTO;
import ChildCareTech.model.entities.Kid;
import ChildCareTech.model.entities.Supplier;
import ChildCareTech.model.entities.Supply;
import ChildCareTech.utils.DTO.DTOFactory;
import org.hibernate.Hibernate;

import java.util.HashSet;
import java.util.Set;

public class SupplierDTOFactory implements AbstractDTOFactory<Supplier, SupplierDTO> {
    @Override
    public SupplierDTO getDTO(Supplier entity) {
        SupplierDTO dto = getSupplierDTO(entity);
        if (dto == null) return null;

        loadContactRelationship(entity, dto);
        loadSupplyRelationship(entity, dto);

        return dto;
    }

    public static SupplierDTO getSupplyOneSide(Supplier entity){
        SupplierDTO dto = getSupplierDTO(entity);
        if (dto == null) return null;

        loadContactRelationship(entity, dto);

        return dto;
    }

    private static SupplierDTO getSupplierDTO(Supplier entity) {
        if (entity == null)
            return null;

        SupplierDTO dto = new SupplierDTO(
                entity.getId(),
                DTOFactory.getDTO(entity.getPerson()),
                null,
                null
        );
        return dto;
    }

    private static void loadContactRelationship(Supplier entity, SupplierDTO dto) {
        Set<KidDTO> contacts = new HashSet<>();

        if(Hibernate.isInitialized(entity.getContacts()))
            for (Kid k : entity.getContacts())
                contacts.add(KidDTOFactory.getAdultContactsManySide(k));

        dto.setContacts(contacts);
    }

    private static void loadSupplyRelationship(Supplier entity, SupplierDTO dto) {
        Set<SupplyDTO> supplies = new HashSet<>();

        if(Hibernate.isInitialized(entity.getSupplies()))
            for (Supply s : entity.getSupplies())
                supplies.add(SupplyDTOFactory.getSupplierManySide(s, dto));

        dto.setSupplies(supplies);
    }
}

