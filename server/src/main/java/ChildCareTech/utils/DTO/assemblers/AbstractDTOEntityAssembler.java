package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.model.iEntity;

public interface AbstractDTOEntityAssembler<ENTITYCLASS extends iEntity, DTOCLASS> {
    ENTITYCLASS assemble(DTOCLASS dto);
}
