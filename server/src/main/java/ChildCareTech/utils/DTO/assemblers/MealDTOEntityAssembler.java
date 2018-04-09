package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.CanteenDTO;
import ChildCareTech.common.DTO.MealDTO;
import ChildCareTech.model.canteen.Canteen;
import ChildCareTech.model.meal.Meal;
import ChildCareTech.model.workday.WorkDay;
import ChildCareTech.utils.DTO.DTOEntityAssembler;

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
                DTOEntityAssembler.getEntity(dto.getWorkDay())
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
