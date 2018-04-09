package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.common.DTO.MealDTO;
import ChildCareTech.common.DTO.MenuDTO;
import ChildCareTech.model.dish.Dish;
import ChildCareTech.model.meal.Meal;
import ChildCareTech.model.menu.Menu;
import ChildCareTech.utils.DTO.DTOEntityAssembler;

import java.util.HashSet;
import java.util.Set;

public class MenuDTOEntityAssembler implements AbstractDTOEntityAssembler<Menu, MenuDTO> {
    @Override
    public Menu assemble(MenuDTO dto) {
        if(dto == null)
            return null;

        Menu entity = new Menu(
                DTOEntityAssembler.getEntity(dto.getMeal()),
                dto.getNumMenu()
        );

        entity.setDrink(
                DTOEntityAssembler.getEntity(dto.getDrink())
        );

        Set<Dish> dishes = new HashSet<>();
        for(DishDTO d : dto.getDishes())
            dishes.add(DishDTOEntityAssembler.assembleMenuManySide(d, entity));
        entity.setDishes(dishes);

        return entity;
    }

    public static Menu assembleDishOneSide(MenuDTO dto) {
        if(dto == null)
            return null;

        Menu entity = new Menu(
                DTOEntityAssembler.getEntity(dto.getMeal()),
                dto.getNumMenu()
        );

        entity.setDrink(
                DTOEntityAssembler.getEntity(dto.getDrink())
        );

        return entity;
    }
}
