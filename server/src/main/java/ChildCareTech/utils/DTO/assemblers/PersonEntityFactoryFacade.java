package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.model.entities.Food;
import ChildCareTech.model.entities.Person;

import java.util.HashSet;
import java.util.Set;

public class PersonEntityFactoryFacade implements AbstractEntityFactoryFacade<Person, PersonDTO> {
    @Override
    public Person assemble(PersonDTO dto) {
        Person entity = getPerson(dto);
        if(entity == null) return null;

        assembleAllergiesRelationship(dto, entity);

        return entity;
    }

    public static Person assembleAllergiesManySide(PersonDTO dto) {
        return getPerson(dto);
    }

    private static void assembleAllergiesRelationship(PersonDTO dto, Person entity){
        Set<Food> allergies = new HashSet<>();

        for(FoodDTO f : dto.getAllergies())
            allergies.add(FoodEntityFactoryFacade.assembleAllergiesManySide(f));

        entity.setAllergies(allergies);
    }

    private static Person getPerson(PersonDTO dto) {
        if(dto == null)
            return null;

        return new Person(
                dto.getId(),
                dto.getFiscalCode(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getBirthDate(),
                dto.getSex(),
                dto.getAddress(),
                dto.getPhoneNumber()
        );
    }
}
