package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.model.iEntity;

/**
 * Defines a generic DTO factory.
 *
 * @param <ENTITYCLASS> the entity output type.
 * @param <DTOCLASS> the data transfer object type, used to generate entity objects.
 */
public interface AbstractEntityFactory<ENTITYCLASS extends iEntity, DTOCLASS> {
    /**
     * @param dto the data transfer object of which get the entity object.
     * @return the corresponding entity.
     */
    ENTITYCLASS assemble(DTOCLASS dto);
}
