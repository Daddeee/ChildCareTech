package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.common.DTO.MenuDTO;
import ChildCareTech.model.entities.Dish;
import ChildCareTech.model.entities.Food;
import ChildCareTech.model.entities.Menu;
import ChildCareTech.utils.DTO.DTOFactoryFacade;
import org.hibernate.Hibernate;

import java.util.HashSet;
import java.util.Set;

public class DishDTOFactory implements AbstractDTOFactory<Dish, DishDTO> {
    @Override
    public DishDTO getDTO(Dish entity) {
        if(entity == null) return null;
        DishDTO dto = getDishDTO(entity);

        loadFoodRelationship(entity, dto);
        loadMenuRelationship(entity, dto);

        return dto;
    }

    public static DishDTO getMenuManySide(Dish entity){
        DishDTO dto = getDishDTO(entity);
        if (dto == null) return null;

        loadFoodRelationship(entity, dto);

        return dto;
    }

    private static DishDTO getDishDTO(Dish entity) {
        if (entity == null)
            return null;

        DishDTO dto = new DishDTO(
                entity.getId(),
                entity.getName(),
                null,
                null
        );
        return dto;
    }

    private static void loadMenuRelationship(Dish entity, DishDTO dto) {
        Set<MenuDTO> menus = new HashSet<>();
        if(Hibernate.isInitialized(entity.getMenus()) && entity.getMenus() != null)
            for (Menu m : entity.getMenus())
                menus.add(MenuDTOFactory.getDishManySide(m));
        dto.setMenus(menus);
    }

    private static void loadFoodRelationship(Dish entity, DishDTO dto) {
        Set<FoodDTO> foods = new HashSet<>();

        if(Hibernate.isInitialized(entity.getFoods()))
            for (Food f : entity.getFoods())
                foods.add(DTOFactoryFacade.getDTO(f));

        dto.setFoods(foods);
    }
}

