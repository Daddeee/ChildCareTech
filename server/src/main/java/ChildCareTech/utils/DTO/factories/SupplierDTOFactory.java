package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.DTO.SupplierDTO;
import ChildCareTech.common.DTO.SupplyDTO;
import ChildCareTech.model.kid.Kid;
import ChildCareTech.model.supplier.Supplier;
import ChildCareTech.model.supply.Supply;
import ChildCareTech.utils.DTO.DTOFactory;

import java.util.HashSet;
import java.util.Set;

public class SupplierDTOFactory implements AbstractDTOFactory<Supplier, SupplierDTO> {
    @Override
    public SupplierDTO getDTO(Supplier entity) {
        if(entity == null)
            return null;

        PersonDTO person = DTOFactory.getDTO(entity.getPerson());

        Set<KidDTO> contacts = new HashSet<>();
        for(Kid k : entity.getContacts())
            contacts.add(DTOFactory.getDTO(k));

        Set<SupplyDTO> supplies = new HashSet<>();
        for(Supply s : entity.getSupplies())
            supplies.add(DTOFactory.getDTO(s));

        return new SupplierDTO(person, contacts, supplies);
    }
}

