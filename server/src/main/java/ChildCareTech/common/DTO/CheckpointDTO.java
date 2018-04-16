package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.time.LocalTime;

public class CheckpointDTO implements Serializable {
    private int id;
    private EventDTO event;
    private PersonDTO person;
    private LocalTime time;
    private boolean isIn;

    public CheckpointDTO(int id, EventDTO event, PersonDTO person, LocalTime time, boolean isIn) {
        this.id = id;
        this.event = event;
        this.person = person;
        this.time = time;
        this.isIn = isIn;
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

    public boolean isIn() {
        return isIn;
    }

    public void setIn(boolean in) {
        isIn = in;
    }
}