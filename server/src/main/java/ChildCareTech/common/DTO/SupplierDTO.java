package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.util.Set;

public class SupplierDTO extends AdultDTO implements Serializable {
    private Set<SupplyDTO> supplies;

    public SupplierDTO(PersonDTO person, Set<KidDTO> contacts, Set<SupplyDTO> supplies) {
        super(person, contacts);
        this.supplies = supplies;
    }

    public Set<SupplyDTO> getSupplies() {
        return supplies;
    }
}