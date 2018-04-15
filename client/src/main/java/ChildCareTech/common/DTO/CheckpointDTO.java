package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.time.LocalTime;

public class CheckpointDTO implements Serializable {
    private int id;
    private WorkDayDTO workDay;
    private PersonDTO person;
    private LocalTime time;
    private boolean isIn;

    public CheckpointDTO(int id, WorkDayDTO workDay, PersonDTO person, LocalTime time, boolean isIn) {
        this.id = id;
        this.workDay = workDay;
        this.person = person;
        this.time = time;
        this.isIn = isIn;
    }

    public int getId() {
        return id;
    }

    public WorkDayDTO getWorkDay() {
        return workDay;
    }

    public void setWorkDay(WorkDayDTO workDay) {
        this.workDay = workDay;
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