package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.time.LocalTime;

public class EventDTO implements Serializable {
    private String name;
    private LocalTime startTime;
    private LocalTime endTime;

    public EventDTO(String name, LocalTime startTime, LocalTime endTime){
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
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
}
