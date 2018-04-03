package ChildCareTech.model.event;

import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.model.person.Person;
import ChildCareTech.model.person.PersonDTOImpl;
import ChildCareTech.model.workday.WorkDayDTOImpl;
import ChildCareTech.utils.DTOFactory;

import java.time.LocalTime;

public class EventDTOImpl implements EventDTO {
    private WorkDayDTO workDay;
    private PersonDTO person;
    private LocalTime time;
    private boolean isIn;

    public EventDTOImpl(Event event){
        workDay = DTOFactory.getDTO(event.getWorkDay());
        person = DTOFactory.getDTO(event.getPerson());
        time = event.getTime();
        isIn = event.isIn();
    }

    @Override
    public WorkDayDTO getWorkDay() {
        return workDay;
    }

    @Override
    public PersonDTO getPerson() {
        return person;
    }

    @Override
    public LocalTime getTime() {
        return time;
    }

    @Override
    public boolean isIn() {
        return isIn;
    }
}