package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

/**
 * This class provides a Data Transfer Object encapsulation for Supplier entity.
 */
public class SupplierDTO extends AdultDTO implements Serializable {
    private Set<SupplyDTO> supplies;

    public SupplierDTO(int id, PersonDTO person, Set<KidDTO> contacts, Set<SupplyDTO> supplies) {
        super(id, person, contacts);
        this.supplies = supplies == null ? Collections.EMPTY_SET : supplies;
    }

    public Set<SupplyDTO> getSupplies() {
        return supplies;
    }

    public void setSupplies(Set<SupplyDTO> supplies) {
        this.supplies = supplies;
    }
}