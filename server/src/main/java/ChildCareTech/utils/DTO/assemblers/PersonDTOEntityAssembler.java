package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.model.entities.Person;

public class PersonDTOEntityAssembler implements AbstractDTOEntityAssembler<Person, PersonDTO> {
    @Override
    public Person assemble(PersonDTO dto) {
        if(dto == null)
            return null;

        return new Person(
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
