package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.model.iEntity;

public interface AbstractEntityFactoryFacade<ENTITYCLASS extends iEntity, DTOCLASS> {
    ENTITYCLASS assemble(DTOCLASS dto);
}
