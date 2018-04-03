package ChildCareTech.model.supplier;

import ChildCareTech.common.DTO.SupplierDTO;
import ChildCareTech.common.DTO.SupplyDTO;
import ChildCareTech.model.adult.AdultDTOImpl;
import ChildCareTech.model.supply.Supply;
import ChildCareTech.model.supply.SupplyDTOImpl;

import java.util.HashSet;
import java.util.Set;

public class SupplierDTOImpl extends AdultDTOImpl implements SupplierDTO {
    private Set<SupplyDTO> supplies;

    public SupplierDTOImpl(Supplier supplier){
        super(supplier);
        supplies = new HashSet<>();
        for(Supply s : supplier.getSupplies())
            supplies.add(new SupplyDTOImpl(s));
    }

    @Override
    public Set<SupplyDTO> getSupplies() {
        return supplies;
    }
}