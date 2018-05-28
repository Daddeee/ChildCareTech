package ChildCareTech.utils.DTO.factories;

import ChildCareTech.model.iEntity;

/**
 * Defines a generic DTO factory.
 *
 * @param <ENTITYCLASS> the entity type, used to generate data transfer objects.
 * @param <DTOCLASS> the data transfer object output type.
 */
public interface AbstractDTOFactory<ENTITYCLASS extends iEntity, DTOCLASS> {
    /**
     * @param entity the entity to which get the DTO.
     * @return the corresponding DTO.
     */
    DTOCLASS getDTO(ENTITYCLASS entity);
}