package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.model.iEntity;

public abstract class AbstractDTOEntityAssembler<ENTITYCLASS extends iEntity, DTOCLASS> {
    public ENTITYCLASS assemble(DTOCLASS dto){
        ENTITYCLASS entity = assembleWithoutRelations(dto);
        assembleRelations(entity, dto);
        return entity;
    }

    public abstract ENTITYCLASS assembleWithoutRelations(DTOCLASS dto);
    public abstract void assembleRelations(ENTITYCLASS entity, DTOCLASS dto);
}
