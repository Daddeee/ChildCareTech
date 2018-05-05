package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.CanteenDTO;
import ChildCareTech.common.DTO.MealDTO;
import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.model.entities.Meal;
import ChildCareTech.utils.DTO.DTOFactory;

public class MealDTOFactory implements AbstractDTOFactory<Meal, MealDTO> {
    @Override
    public MealDTO getDTO(Meal entity) {
        return getMealDTO(entity,
                CanteenDTOFactory.getMealOneSide(entity.getCanteen()),
                WorkDayDTOFactory.getMealOneSide(entity.getWorkDay())
        );
    }

    public static MealDTO getCanteenManySide(Meal entity, CanteenDTO canteenDTO){
        if (entity == null)
            return null;

        return getMealDTO(
                entity,
                canteenDTO,
                WorkDayDTOFactory.getMealOneSide(entity.getWorkDay())
        );
    }

    public static MealDTO getWorkDayManySide(Meal entity, WorkDayDTO workDayDTO){
        if(entity == null)
            return null;

        return getMealDTO(
                entity,
                CanteenDTOFactory.getMealOneSide(entity.getCanteen()),
                workDayDTO
        );
    }

    private static MealDTO getMealDTO(Meal entity, CanteenDTO canteenDTO ,WorkDayDTO workDayDTO) {
        if (entity == null)
            return null;

        return new MealDTO(
                entity.getId(),
                canteenDTO,
                workDayDTO,
                entity.getMealNum(),
                EventDTOFactory.getMealOneSide(entity.getEntryEvent()),
                EventDTOFactory.getMealOneSide(entity.getExitEvent())
        );
    }
}

