package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.Sex;
import ChildCareTech.model.person.Person;

import java.time.LocalDate;

public class PersonDTOFactory implements AbstractDTOFactory<Person, PersonDTO> {
    @Override
    public PersonDTO getDTO(Person entity) {
        if (entity == null)
            return null;

        return new PersonDTO(
            entity.getFiscalCode(),
            entity.getFirstName(),
            entity.getLastName(),
            entity.getBirthDate(),
            entity.getSex(),
            entity.getAddress(),
            entity.getAddress()
        );
    }
}

