package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.MealDTO;
import ChildCareTech.model.entities.Canteen;
import ChildCareTech.model.entities.Meal;
import ChildCareTech.model.entities.WorkDay;

public class MealDTOEntityAssembler implements AbstractDTOEntityAssembler<Meal, MealDTO> {
    @Override
    public Meal assemble(MealDTO dto) {
        if(dto == null)
            return null;

        return new Meal(
                CanteenDTOEntityAssembler.assembleMealOneSide(dto.getCanteen()),
                dto.getMealNum(),
                WorkDayDTOEntityAssembler.assembleMealOneSide(dto.getWorkDay())
        );
    }

    public static Meal assembleCanteenManySide(MealDTO dto, Canteen canteen) {
        if(dto == null)
            return null;

        return new Meal(
                canteen,
                dto.getMealNum(),
                WorkDayDTOEntityAssembler.assembleMealOneSide(dto.getWorkDay())
        );
    }

    public static Meal assembleWorkDayManySide(MealDTO dto, WorkDay workDay) {
        if(dto == null)
            return null;

        return new Meal(
                CanteenDTOEntityAssembler.assembleMealOneSide(dto.getCanteen()),
                dto.getMealNum(),
                workDay
        );
    }
}
