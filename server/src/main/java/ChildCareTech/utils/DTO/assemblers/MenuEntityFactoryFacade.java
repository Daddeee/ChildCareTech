package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.common.DTO.MenuDTO;
import ChildCareTech.model.entities.Dish;
import ChildCareTech.model.entities.Meal;
import ChildCareTech.model.entities.Menu;
import ChildCareTech.utils.DTO.EntityFactoryFacade;

import java.util.HashSet;
import java.util.Set;

public class MenuEntityFactoryFacade implements AbstractEntityFactoryFacade<Menu, MenuDTO> {
    @Override
    public Menu assemble(MenuDTO dto) {
        if(dto == null) return null;
        Menu entity = getMenu(dto, null);

        entity.setMeal(MealEntityFactoryFacade.assembleMenuOneSide(dto.getMeal(), entity));
        loadDishesRelationship(dto, entity);

        return entity;
    }

    public static Menu assembleDishManySide(MenuDTO dto) {
        if(dto == null) return null;
        Menu entity = getMenu(dto, EntityFactoryFacade.getEntity(dto.getMeal()));

        return entity;
    }

    public static Menu assembleMealOneSide(MenuDTO dto, Meal meal){
        if(dto == null) return null;
        Menu entity = getMenu(dto, meal);

        loadDishesRelationship(dto, entity);

        return entity;
    }

    private static void loadDishesRelationship(MenuDTO dto, Menu entity) {
        Set<Dish> dishes = new HashSet<>();
        for(DishDTO d : dto.getDishes())
            dishes.add(DishEntityFactoryFacade.assembleMenuManySide(d));
        entity.setDishes(dishes);
    }

    private static Menu getMenu(MenuDTO dto, Meal meal) {
        if(dto == null)
            return null;

        Menu entity = new Menu(
                dto.getId(),
                meal,
                dto.getNumMenu(),
                null
        );
        return entity;
    }
}
