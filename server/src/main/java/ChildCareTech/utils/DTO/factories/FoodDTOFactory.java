package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.DTO.SupplyDTO;
import ChildCareTech.model.entities.Food;
import ChildCareTech.model.entities.Person;
import ChildCareTech.model.entities.Supply;
import org.hibernate.Hibernate;

import java.util.HashSet;
import java.util.Set;

public class FoodDTOFactory implements AbstractDTOFactory<Food, FoodDTO> {
    @Override
    public FoodDTO getDTO(Food entity) {
        FoodDTO dto = getFoodDTO(entity);
        if(dto == null) return null;

        loadSupplyRelationship(entity, dto);
        loadAllergiesRelationship(entity, dto);

        return dto;
    }

    public static FoodDTO getSupplyOneSide(Food entity){
        FoodDTO dto = getFoodDTO(entity);
        if(dto == null) return null;

        loadAllergiesRelationship(entity, dto);

        return dto;
    }
    public static FoodDTO getAllergiesManySide(Food entity) {
        FoodDTO dto = getFoodDTO(entity);
        if(dto == null) return null;

        loadSupplyRelationship(entity, dto);

        return dto;
    }

    private static FoodDTO getFoodDTO(Food entity) {
        if (entity == null)
            return null;

        return new FoodDTO(
                entity.getId(),
                entity.getName(),
                entity.isDrink(),
                entity.getResidualQuantity(),
                null,
                null
        );
    }

    private static void loadAllergiesRelationship(Food entity, FoodDTO dto){
        Set<PersonDTO> allergies = new HashSet<>();

        if(Hibernate.isInitialized(entity.getAllergies()))
            for(Person p : entity.getAllergies())
                allergies.add(PersonDTOFactory.getAllergiesManySide(p));

        dto.setAllergies(allergies);
    }

    private static void loadSupplyRelationship(Food entity, FoodDTO dto) {
        Set<SupplyDTO> supplies = new HashSet<>();

        if(Hibernate.isInitialized(entity.getSupplies()))
            for (Supply s : entity.getSupplies())
                supplies.add(SupplyDTOFactory.getFoodManySide(s, dto));

        dto.setSupplies(supplies);
    }
}

