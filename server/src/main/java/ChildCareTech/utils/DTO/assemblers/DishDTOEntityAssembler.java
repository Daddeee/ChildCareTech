package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.common.DTO.FoodDTO;
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
        Dish entity = getDish(dto, MenuDTOEntityAssembler.assembleDishOneSide(dto.getMenu()));

        Set<Food> foods = new HashSet<>();
        for(FoodDTO f : dto.getFoods())
            foods.add(DTOEntityAssembler.getEntity(f));
        entity.setFoods(foods);

        return entity;
    }

    public static Dish assembleMenuManySide(DishDTO dto, Menu menu){
        Dish entity = getDish(dto, menu);
        if (entity == null) return null;

        Set<Food> foods = new HashSet<>();
        for(FoodDTO f : dto.getFoods())
            foods.add(DTOEntityAssembler.getEntity(f));
        entity.setFoods(foods);

        return entity;
    }

    private static Dish getDish(DishDTO dto, Menu menu) {
        if(dto == null)
            return null;

        Dish entity = new Dish(
                dto.getId(),
                dto.getName(),
                menu,
                null
        );
        return entity;
    }
}
