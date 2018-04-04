package ChildCareTech.utils.DTO.factories;

import ChildCareTech.model.iEntity;

public interface AbstractDTOFactory<ENTITYCLASS extends iEntity, DTOCLASS> {
    DTOCLASS getDTO(ENTITYCLASS entity);
}