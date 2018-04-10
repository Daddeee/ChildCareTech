package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.CanteenDTO;
import ChildCareTech.common.DTO.MealDTO;
import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.model.entities.Meal;
import ChildCareTech.utils.DTO.DTOFactory;

public class MealDTOFactory implements AbstractDTOFactory<Meal, MealDTO> {
    @Override
    public MealDTO getDTO(Meal entity) {
        if (entity == null)
            return null;

        return new MealDTO(
                CanteenDTOFactory.getMealOneSide(entity.getCanteen()),
                WorkDayDTOFactory.getMealOneSide(entity.getWorkDay()),
                entity.getMealNum()
        );
    }

    public static MealDTO getCanteenManySide(Meal entity, CanteenDTO canteenDTO){
        if (entity == null)
            return null;

        return new MealDTO(
                canteenDTO,
                DTOFactory.getDTO(entity.getWorkDay()),
                entity.getMealNum()
        );
    }

    public static MealDTO getWorkDayManySide(Meal entity, WorkDayDTO workDayDTO){
        if (entity == null)
            return null;

        return new MealDTO(
                CanteenDTOFactory.getMealOneSide(entity.getCanteen()),
                workDayDTO,
                entity.getMealNum()
        );
    }
}

