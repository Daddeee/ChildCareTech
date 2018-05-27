package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * This class provides a Data Transfer Object encapsulation for {@link ChildCareTech.model.entities.Checkpoint CheckPoint} entity.
 */
public class CheckpointDTO implements Serializable {
    private int id;
    private EventDTO event;
    private PersonDTO person;
    private LocalTime time;

    public CheckpointDTO(int id, EventDTO event, PersonDTO person, LocalTime time) {
        this.id = id;
        this.event = event;
        this.person = person;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public EventDTO getEvent() {
        return event;
    }

    public void setEvent(EventDTO event) {
        this.event = event;
    }

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}