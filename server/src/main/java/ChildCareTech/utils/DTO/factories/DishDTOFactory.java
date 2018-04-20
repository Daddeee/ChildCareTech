package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.common.DTO.MenuDTO;
import ChildCareTech.model.entities.Dish;
import ChildCareTech.model.entities.Food;
import ChildCareTech.utils.DTO.DTOFactory;
import org.hibernate.Hibernate;

import java.util.HashSet;
import java.util.Set;

public class DishDTOFactory implements AbstractDTOFactory<Dish, DishDTO> {
    @Override
    public DishDTO getDTO(Dish entity) {
        if(entity == null) return null;
        DishDTO dto = getDishDTO(entity, MenuDTOFactory.getDishOneSide(entity.getMenu()));

        loadFoodRelationship(entity, dto);

        return dto;
    }

    public static DishDTO getMenuManySide(Dish entity, MenuDTO menuDTO){
        DishDTO dto = getDishDTO(entity, menuDTO);
        if (dto == null) return null;


        loadFoodRelationship(entity, dto);

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

    private static void loadFoodRelationship(Dish entity, DishDTO dto) {
        Set<FoodDTO> foods = new HashSet<>();

        if(Hibernate.isInitialized(entity.getFoods()))
            for (Food f : entity.getFoods())
                foods.add(DTOFactory.getDTO(f));

        dto.setFoods(foods);
    }
}

