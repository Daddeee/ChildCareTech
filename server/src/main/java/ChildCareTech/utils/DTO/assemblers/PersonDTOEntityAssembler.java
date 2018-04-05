package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.model.person.Person;

public class PersonDTOEntityAssembler extends AbstractDTOEntityAssembler<Person, PersonDTO> {
    @Override
    public Person assembleWithoutRelations(PersonDTO dto) {
        return null;
    }

    @Override
    public void assembleRelations(Person entity, PersonDTO dto) {

    }
}
