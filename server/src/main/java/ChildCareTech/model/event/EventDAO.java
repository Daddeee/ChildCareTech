package ChildCareTech.model.event;

import ChildCareTech.utils.GenericDAO;

public class EventDAO extends GenericDAO<Event, Integer> {
    public EventDAO() {
        super(Event.class);
    }
}