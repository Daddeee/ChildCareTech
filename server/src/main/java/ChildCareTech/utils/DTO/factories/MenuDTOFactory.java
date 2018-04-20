package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.common.DTO.DrinkDTO;
import ChildCareTech.common.DTO.MenuDTO;
import ChildCareTech.model.entities.Dish;
import ChildCareTech.model.entities.Menu;
import ChildCareTech.utils.DTO.DTOFactory;
import org.hibernate.Hibernate;

import java.util.HashSet;
import java.util.Set;

public class MenuDTOFactory implements AbstractDTOFactory<Menu, MenuDTO> {
    @Override
    public MenuDTO getDTO(Menu entity) {
        MenuDTO dto = getMenuDTO(entity);
        if (dto == null) return null;

        dto.setDrink(
                DrinkDTOFactory.getMenuOneSide(entity.getDrink(), dto)
        );

        loadDishRelationship(entity, dto);

        return dto;
    }

    public static MenuDTO getDishOneSide(Menu entity){
        MenuDTO dto = getMenuDTO(entity);
        if (dto == null) return null;

        dto.setDrink(
                DrinkDTOFactory.getMenuOneSide(entity.getDrink(), dto)
        );

        return dto;
    }

    public static MenuDTO getDrinkOneSide(Menu entity, DrinkDTO drinkDTO){
        MenuDTO dto = getMenuDTO(entity);
        if (dto == null) return null;

        dto.setDrink(
                drinkDTO
        );

        loadDishRelationship(entity, dto);

        return dto;
    }

    private static MenuDTO getMenuDTO(Menu entity) {
        if (entity == null)
            return null;

        MenuDTO dto = new MenuDTO(
                entity.getId(),
                DTOFactory.getDTO(entity.getMeal()),
                entity.getNumMenu(),
                null,
                null
        );
        return dto;
    }

    private static void loadDishRelationship(Menu entity, MenuDTO dto) {
        Set<DishDTO> dishes = new HashSet<>();

        if(Hibernate.isInitialized(entity.getDishes()))
            for (Dish d : entity.getDishes())
                dishes.add(DishDTOFactory.getMenuManySide(d, dto));

        dto.setDishes(dishes);
    }
}

