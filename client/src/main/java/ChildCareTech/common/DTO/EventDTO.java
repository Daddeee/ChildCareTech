package ChildCareTech.common.DTO;

import ChildCareTech.common.EventStatus;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Set;

public class EventDTO implements Serializable {
    private int id;
    private String name;
    private WorkDayDTO workDay;
    private LocalTime beginTime;
    private LocalTime endTime;
    private EventStatus eventStatus;
    private Set<CheckpointDTO> checkpoints;

    public EventDTO(int id, String name, WorkDayDTO workDay, LocalTime beginTime, LocalTime endTime, EventStatus eventStatus, Set<CheckpointDTO> checkpoints){
        this.id = id;
        this.name = name;
        this.workDay = workDay;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.eventStatus = eventStatus;
        this.checkpoints = checkpoints;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public WorkDayDTO getWorkDay() {
        return workDay;
    }

    public LocalTime getBeginTime() {
        return beginTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public Set<CheckpointDTO> getCheckpoints() {
        return checkpoints;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWorkDay(WorkDayDTO workDay) {
        this.workDay = workDay;
    }

    public void setBeginTime(LocalTime beginTime) {
        this.beginTime = beginTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }

    public void setCheckpoints(Set<CheckpointDTO> checkpoints) {
        this.checkpoints = checkpoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventDTO)) return false;
        return this.name.equals(((EventDTO) o).name) &&
                this.workDay.equals(((EventDTO) o).workDay);
    }
}
