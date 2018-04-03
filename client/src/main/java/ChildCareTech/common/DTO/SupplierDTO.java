package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.util.Set;

public interface SupplierDTO extends Serializable, AdultDTO {
    Set<SupplyDTO> getSupplies();
}