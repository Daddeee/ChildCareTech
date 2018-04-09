package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.common.DTO.DrinkDTO;
import ChildCareTech.common.DTO.MealDTO;
import ChildCareTech.common.DTO.MenuDTO;
import ChildCareTech.model.dish.Dish;
import ChildCareTech.model.menu.Menu;
import ChildCareTech.utils.DTO.DTOFactory;

import java.util.HashSet;
import java.util.Set;

public class MenuDTOFactory implements AbstractDTOFactory<Menu, MenuDTO> {
    @Override
    public MenuDTO getDTO(Menu entity) {
        if (entity == null)
            return null;

        MenuDTO dto = new MenuDTO(
                DTOFactory.getDTO(entity.getMeal()),
                entity.getNumMenu(),
                null,
                null
        );

        dto.setDrink(
                DrinkDTOFactory.getMenuOneSide(entity.getDrink(), dto)
        );

        Set<DishDTO> dishes = new HashSet<>();
        for (Dish d : entity.getDishes())
            dishes.add(DishDTOFactory.getMenuManySide(d, dto));
        dto.setDishes(dishes);

        return dto;
    }

    public static MenuDTO getDishOneSide(Menu entity){
        if (entity == null)
            return null;

        MenuDTO dto = new MenuDTO(
                DTOFactory.getDTO(entity.getMeal()),
                entity.getNumMenu(),
                null,
                null
        );

        dto.setDrink(
                DrinkDTOFactory.getMenuOneSide(entity.getDrink(), dto)
        );

        return dto;
    }

    public static MenuDTO getDrinkOneSide(Menu entity, DrinkDTO drinkDTO){
        if (entity == null)
            return null;

        MenuDTO dto = new MenuDTO(
                DTOFactory.getDTO(entity.getMeal()),
                entity.getNumMenu(),
                null,
                null
        );

        dto.setDrink(
                drinkDTO
        );

        Set<DishDTO> dishes = new HashSet<>();
        for (Dish d : entity.getDishes())
            dishes.add(DishDTOFactory.getMenuManySide(d, dto));
        dto.setDishes(dishes);

        return dto;
    }
}

