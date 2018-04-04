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

        String fiscalCode = entity.getFiscalCode();
        String firstName = entity.getFirstName();
        String lastName = entity.getLastName();
        LocalDate birthDate = entity.getBirthDate();
        Sex sex = entity.getSex();
        String address = entity.getAddress();
        String phoneNumber = entity.getAddress();

        return new PersonDTO(fiscalCode, firstName, lastName, birthDate, sex, address, phoneNumber);
    }
}

