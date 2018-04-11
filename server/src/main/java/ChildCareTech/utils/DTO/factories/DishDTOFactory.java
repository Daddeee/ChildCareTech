package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.common.DTO.MenuDTO;
import ChildCareTech.model.entities.Dish;
import ChildCareTech.model.entities.Food;
import ChildCareTech.utils.DTO.DTOFactory;

import java.util.HashSet;
import java.util.Set;

public class DishDTOFactory implements AbstractDTOFactory<Dish, DishDTO> {
    @Override
    public DishDTO getDTO(Dish entity) {
        if(entity == null) return null;
        DishDTO dto = getDishDTO(entity, MenuDTOFactory.getDishOneSide(entity.getMenu()));

        Set<FoodDTO> foods = new HashSet<>();
        for (Food f : entity.getFoods())
            foods.add(DTOFactory.getDTO(f));
        dto.setFoods(foods);

        return dto;
    }

    public static DishDTO getMenuManySide(Dish entity, MenuDTO menuDTO){
        DishDTO dto = getDishDTO(entity, menuDTO);
        if (dto == null) return null;


        Set<FoodDTO> foods = new HashSet<>();
        for (Food f : entity.getFoods())
            foods.add(DTOFactory.getDTO(f));
        dto.setFoods(foods);

        return dto;
    }

    private static DishDTO getDishDTO(Dish entity, MenuDTO menuDTO) {
        if (entity == null)
            return null;

        DishDTO dto = new DishDTO(
                entity.getId(),
                entity.getName(),
                menuDTO,
                null
        );
        return dto;
    }
}

