package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.time.LocalTime;

public interface EventDTO extends Serializable {
    PersonDTO getPerson();
    WorkDayDTO getWorkDay();
    LocalTime getTime();
    boolean isIn();
}