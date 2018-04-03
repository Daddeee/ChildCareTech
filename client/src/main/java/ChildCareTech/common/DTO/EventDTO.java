package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.rmi.Remote;
import java.time.LocalTime;

public interface EventDTO extends Serializable, Remote {
    PersonDTO getPerson();

    WorkDayDTO getWorkDay();

    LocalTime getTime();

    boolean isIn();
}