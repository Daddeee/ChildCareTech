package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.CanteenDTO;
import ChildCareTech.common.DTO.MealDTO;
import ChildCareTech.common.DTO.MenuDTO;
import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.model.entities.Meal;

public class MealDTOFactory implements AbstractDTOFactory<Meal, MealDTO> {
    @Override
    public MealDTO getDTO(Meal entity) {
        if(entity == null) return null;

        MealDTO dto = getMealDTO(
                entity,
                CanteenDTOFactory.getMealOneSide(entity.getCanteen()),
                WorkDayDTOFactory.getMealOneSide(entity.getWorkDay())
        );

        dto.setMenu(MenuDTOFactory.getMealOneSide(entity.getMenu(), dto));

        return dto;
    }

    public static MealDTO getCanteenManySide(Meal entity, CanteenDTO canteenDTO){
        if(entity == null) return null;

        MealDTO dto = getMealDTO(
                entity,
                canteenDTO,
                WorkDayDTOFactory.getMealOneSide(entity.getWorkDay())
        );

        dto.setMenu(MenuDTOFactory.getMealOneSide(entity.getMenu(), dto));

        return dto;
    }

    public static MealDTO getWorkDayManySide(Meal entity, WorkDayDTO workDayDTO){
        if(entity == null) return null;

        MealDTO dto = getMealDTO(
                entity,
                CanteenDTOFactory.getMealOneSide(entity.getCanteen()),
                workDayDTO
        );

        dto.setMenu(MenuDTOFactory.getMealOneSide(entity.getMenu(), dto));

        return dto;
    }

    public static MealDTO getMenuOneSide(Meal entity, MenuDTO menuDTO){
        if(entity == null) return null;

        MealDTO dto = getMealDTO(
                entity,
                CanteenDTOFactory.getMealOneSide(entity.getCanteen()),
                WorkDayDTOFactory.getMealOneSide(entity.getWorkDay())
        );

        dto.setMenu(menuDTO);

        return dto;
    }

    private static MealDTO getMealDTO(Meal entity, CanteenDTO canteenDTO ,WorkDayDTO workDayDTO) {
        if (entity == null)
            return null;

        return new MealDTO(
                entity.getId(),
                canteenDTO,
                workDayDTO,
                entity.getMealNum(),
                EventDTOFactory.getMealOneSide(entity.getEntryEvent(), workDayDTO),
                EventDTOFactory.getMealOneSide(entity.getExitEvent(), workDayDTO),
                entity.getStatus(),
                null
        );
    }
}

