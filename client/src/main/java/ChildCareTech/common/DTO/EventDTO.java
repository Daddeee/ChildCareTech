package ChildCareTech.common.DTO;

import ChildCareTech.common.EventStatus;

import java.io.Serializable;
import java.time.LocalTime;

public class EventDTO implements Serializable {
    private String name;
    private LocalTime startTime;
    private LocalTime endTime;
    private EventStatus status;

    public EventDTO(String name, LocalTime startTime, LocalTime endTime, EventStatus status){
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public EventStatus getStatus() {
        return status;
    }
}
