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
        if(entity == null)
            return null;

        MealDTO meal = DTOFactory.getDTO(entity.getMeal());
        int numMenu = entity.getNumMenu();
        DrinkDTO drink = DTOFactory.getDTO(entity.getDrink());

        Set<DishDTO> dishes = new HashSet<>();
        for(Dish d : entity.getDishes())
            dishes.add(DTOFactory.getDTO(d));

        return new MenuDTO(meal, numMenu, dishes, drink);
    }
}

