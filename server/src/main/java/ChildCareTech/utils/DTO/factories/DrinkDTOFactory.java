package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.DrinkDTO;
import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.common.DTO.MenuDTO;
import ChildCareTech.model.entities.Drink;
import ChildCareTech.model.entities.Food;
import ChildCareTech.utils.DTO.DTOFactory;

import java.util.HashSet;
import java.util.Set;

public class DrinkDTOFactory implements AbstractDTOFactory<Drink, DrinkDTO> {
    @Override
    public DrinkDTO getDTO(Drink entity) {
        DrinkDTO dto = getDrinkDTO(entity);
        if(dto == null) return null;

        dto.setMenu(
                MenuDTOFactory.getDrinkOneSide(entity.getMenu(), dto)
        );

        Set<FoodDTO> foods = new HashSet<>();
        for (Food f : entity.getFoods())
            foods.add(DTOFactory.getDTO(f));
        dto.setFoods(foods);

        return dto;
    }

    public static DrinkDTO getMenuOneSide(Drink entity, MenuDTO menuDTO){
        DrinkDTO dto = getDrinkDTO(entity);
        if (dto == null) return null;

        dto.setMenu(
                menuDTO
        );

        Set<FoodDTO> foods = new HashSet<>();
        for (Food f : entity.getFoods())
            foods.add(DTOFactory.getDTO(f));
        dto.setFoods(foods);

        return dto;
    }

    private static DrinkDTO getDrinkDTO(Drink entity) {
        if (entity == null)
            return null;

        DrinkDTO dto = new DrinkDTO(
                entity.getId(),
                entity.getName(),
                null,
                null
        );
        return dto;
    }
}

