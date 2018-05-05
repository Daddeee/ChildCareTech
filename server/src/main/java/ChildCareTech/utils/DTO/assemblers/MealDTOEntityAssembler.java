package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.CanteenDTO;
import ChildCareTech.common.DTO.MealDTO;
import ChildCareTech.model.entities.Canteen;
import ChildCareTech.model.entities.Meal;
import ChildCareTech.model.entities.WorkDay;

public class MealDTOEntityAssembler implements AbstractDTOEntityAssembler<Meal, MealDTO> {
    @Override
    public Meal assemble(MealDTO dto) {
        if(dto == null) return null;
        return getMeal(
                dto,
                CanteenDTOEntityAssembler.assembleMealOneSide(dto.getCanteen()),
                WorkDayDTOEntityAssembler.assembleMealOneSide(dto.getWorkDay())
        );
    }

    public static Meal assembleCanteenManySide(MealDTO dto, Canteen canteen) {
        if(dto == null) return null;

        return getMeal(
                dto,
                canteen,
                WorkDayDTOEntityAssembler.assembleMealOneSide(dto.getWorkDay())
        );
    }

    public static Meal assembleWorkDayManySide(MealDTO dto, WorkDay workDay) {
        if(dto == null) return null;

        return getMeal(
                dto,
                CanteenDTOEntityAssembler.assembleMealOneSide(dto.getCanteen()),
                workDay
        );
    }

    private static Meal getMeal(MealDTO dto, Canteen canteen, WorkDay workDay) {
        if(dto == null)
            return null;

        return new Meal(
                dto.getId(),
                canteen,
                dto.getMealNum(),
                workDay,
                EventDTOEntityAssembler.assembleMealOneSide(dto.getEntryEvent()),
                EventDTOEntityAssembler.assembleMealOneSide(dto.getExitEvent())
        );
    }
}
