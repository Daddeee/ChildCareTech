package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.model.event.Event;
import ChildCareTech.model.person.Person;
import ChildCareTech.utils.DTO.DTOEntityAssembler;

import java.util.HashSet;
import java.util.Set;

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
