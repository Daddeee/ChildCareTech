package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.SupplierDTO;
import ChildCareTech.common.DTO.SupplyDTO;
import ChildCareTech.model.entities.Supplier;
import ChildCareTech.model.entities.Supply;
import ChildCareTech.utils.DTO.DTOEntityAssembler;

import java.util.HashSet;
import java.util.Set;

public class SupplierDTOEntityAssembler implements AbstractDTOEntityAssembler<Supplier, SupplierDTO> {
    @Override
    public Supplier assemble(SupplierDTO dto) {
        if(dto == null)
            return null;

        Supplier entity = new Supplier(
                DTOEntityAssembler.getEntity(dto.getPerson())
        );

        Set<Supply> supplies = new HashSet<>();
        for(SupplyDTO s : dto.getSupplies())
            supplies.add(DTOEntityAssembler.getEntity(s));
        entity.setSupplies(supplies);

        return entity;
    }

    public static Supplier assembleSupplyOneSide(SupplierDTO dto) {
        if(dto == null)
            return null;

        Supplier entity = new Supplier(
                DTOEntityAssembler.getEntity(dto.getPerson())
        );

        return entity;
    }
}
