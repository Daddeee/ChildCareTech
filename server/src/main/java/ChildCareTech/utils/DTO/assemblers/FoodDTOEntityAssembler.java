package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.DTO.SupplyDTO;
import ChildCareTech.model.entities.Food;
import ChildCareTech.model.entities.Person;
import ChildCareTech.model.entities.Supply;

import java.util.HashSet;
import java.util.Set;

public class FoodDTOEntityAssembler implements AbstractDTOEntityAssembler<Food, FoodDTO> {
    @Override
    public Food assemble(FoodDTO dto) {
        if(dto == null) return null;
        Food entity = getFood(dto);

        assembleSupplyRelationship(dto, entity);
        assembleAllergiesRelationShip(dto, entity);

        return entity;
    }

    public static Food assembleSupplyOneSide(FoodDTO dto){
        return getFood(dto);
    }
    public static Food assembleAllergiesManySide(FoodDTO dto) {
        return getFood(dto);
    }

    private static void assembleSupplyRelationship(FoodDTO dto, Food entity) {
        Set<Supply> supplies = new HashSet<>();
        for(SupplyDTO e : dto.getSupplies())
            supplies.add(SupplyDTOEntityAssembler.assembleFoodManySide(e, entity));
        entity.setSupplies(supplies);
    }

    private static void assembleAllergiesRelationShip(FoodDTO dto, Food entity) {
        Set<Person> allergies = new HashSet<>();

        for(PersonDTO p : dto.getAllergies())
            allergies.add(PersonDTOEntityAssembler.assembleAllergiesManySide(p));

        entity.setAllergies(allergies);
    }

    private static Food getFood(FoodDTO dto) {
        if(dto == null)
            return null;

        return new Food(
                dto.getId(),
                dto.getName(),
                dto.isDrink(),
                dto.getResidualQuantity(),
                null,
                null
        );
    }
}
