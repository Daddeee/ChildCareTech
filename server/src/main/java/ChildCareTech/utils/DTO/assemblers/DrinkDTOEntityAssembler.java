package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.DrinkDTO;
import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.model.drink.Drink;
import ChildCareTech.model.food.Food;
import ChildCareTech.model.menu.Menu;
import ChildCareTech.utils.DTO.DTOEntityAssembler;

import java.util.HashSet;
import java.util.Set;

public class DrinkDTOEntityAssembler implements AbstractDTOEntityAssembler<Drink, DrinkDTO> {
    @Override
    public Drink assemble(DrinkDTO dto) {
        if(dto == null)
            return null;

        Drink entity = new Drink(
                dto.getName(),
                null
        );

        entity.setMenu(MenuDTOEntityAssembler.assembleDrinkOneSide(dto.getMenu(), entity));

        Set<Food> foods = new HashSet<>();
        for(FoodDTO f : dto.getFoods())
            foods.add(DTOEntityAssembler.getEntity(f));
        entity.setFoods(foods);

        return entity;
    }

    public static Drink assembleMenuOneSide(DrinkDTO dto, Menu menu){
        if(dto == null)
            return null;

        Drink entity = new Drink(
                dto.getName(),
                menu
        );

        Set<Food> foods = new HashSet<>();
        for(FoodDTO f : dto.getFoods())
            foods.add(DTOEntityAssembler.getEntity(f));
        entity.setFoods(foods);

        return entity;
    }
}
