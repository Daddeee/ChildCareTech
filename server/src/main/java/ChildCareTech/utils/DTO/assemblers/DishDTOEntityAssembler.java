package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.common.DTO.MenuDTO;
import ChildCareTech.model.entities.Dish;
import ChildCareTech.model.entities.Food;
import ChildCareTech.model.entities.Menu;
import ChildCareTech.utils.DTO.DTOEntityAssembler;

import java.util.HashSet;
import java.util.Set;

public class DishDTOEntityAssembler implements AbstractDTOEntityAssembler<Dish, DishDTO> {
    @Override
    public Dish assemble(DishDTO dto) {
        if (dto == null) return null;
        Dish entity = getDish(dto);

        loadFoodsRelationship(dto, entity);
        loadMenusRelationship(dto, entity);

        return entity;
    }

    public static Dish assembleMenuManySide(DishDTO dto){
        Dish entity = getDish(dto);
        if (entity == null) return null;

        loadFoodsRelationship(dto, entity);

        return entity;
    }

    private static void loadMenusRelationship(DishDTO dto, Dish entity) {
        Set<Menu> menus = new HashSet<>();
        for(MenuDTO m : dto.getMenus())
            menus.add(MenuDTOEntityAssembler.assembleDishManySide(m));
        entity.setMenus(menus);
    }

    private static void loadFoodsRelationship(DishDTO dto, Dish entity) {
        Set<Food> foods = new HashSet<>();
        for(FoodDTO f : dto.getFoods())
            foods.add(DTOEntityAssembler.getEntity(f));
        entity.setFoods(foods);
    }

    private static Dish getDish(DishDTO dto) {
        if(dto == null)
            return null;

        Dish entity = new Dish(
                dto.getId(),
                dto.getName(),
                null,
                null
        );
        return entity;
    }
}
