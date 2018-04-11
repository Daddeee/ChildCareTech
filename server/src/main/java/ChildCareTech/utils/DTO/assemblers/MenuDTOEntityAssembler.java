package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.common.DTO.MenuDTO;
import ChildCareTech.model.entities.Dish;
import ChildCareTech.model.entities.Drink;
import ChildCareTech.model.entities.Menu;
import ChildCareTech.utils.DTO.DTOEntityAssembler;

import java.util.HashSet;
import java.util.Set;

public class MenuDTOEntityAssembler implements AbstractDTOEntityAssembler<Menu, MenuDTO> {
    @Override
    public Menu assemble(MenuDTO dto) {
        Menu entity = getMenu(dto);
        if (entity == null) return null;

        entity.setDrink(
                DrinkDTOEntityAssembler.assembleMenuOneSide(dto.getDrink(), entity)
        );

        Set<Dish> dishes = new HashSet<>();
        for(DishDTO d : dto.getDishes())
            dishes.add(DishDTOEntityAssembler.assembleMenuManySide(d, entity));
        entity.setDishes(dishes);

        return entity;
    }

    public static Menu assembleDishOneSide(MenuDTO dto) {
        Menu entity = getMenu(dto);
        if (entity == null) return null;

        entity.setDrink(
                DTOEntityAssembler.getEntity(dto.getDrink())
        );

        return entity;
    }

    public static Menu assembleDrinkOneSide(MenuDTO dto, Drink drink){
        Menu entity = getMenu(dto);
        if (entity == null) return null;

        entity.setDrink(
                drink
        );

        Set<Dish> dishes = new HashSet<>();
        for(DishDTO d : dto.getDishes())
            dishes.add(DishDTOEntityAssembler.assembleMenuManySide(d, entity));
        entity.setDishes(dishes);

        return entity;
    }

    private static Menu getMenu(MenuDTO dto) {
        if(dto == null)
            return null;

        Menu entity = new Menu(
                dto.getId(),
                DTOEntityAssembler.getEntity(dto.getMeal()),
                dto.getNumMenu(),
                null,
                null
        );
        return entity;
    }
}
