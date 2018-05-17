package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.model.entities.Food;
import ChildCareTech.model.entities.Person;
import org.hibernate.Hibernate;

import java.util.HashSet;
import java.util.Set;

public class PersonDTOFactory implements AbstractDTOFactory<Person, PersonDTO> {
    @Override
    public PersonDTO getDTO(Person entity) {
        PersonDTO dto = getPersonDTO(entity);
        if(dto == null) return null;

        loadAllergiesRelationship(entity, dto);

        return dto;
    }

    public static PersonDTO getAllergiesManySide(Person entity) { return getPersonDTO(entity); }

    private static void loadAllergiesRelationship(Person entity, PersonDTO dto){
        Set<FoodDTO> foods = new HashSet<>();

        if(Hibernate.isInitialized(entity.getAllergies()))
            for(Food f : entity.getAllergies())
                foods.add(FoodDTOFactory.getAllergiesManySide(f));

        dto.setAllergies(foods);
    }

    private static PersonDTO getPersonDTO(Person entity) {
        if (entity == null)
            return null;

        return new PersonDTO(
            entity.getId(),
            entity.getFiscalCode(),
            entity.getFirstName(),
            entity.getLastName(),
            entity.getBirthDate(),
            entity.getSex(),
            entity.getAddress(),
            entity.getPhoneNumber(),
            null
        );
    }
}

