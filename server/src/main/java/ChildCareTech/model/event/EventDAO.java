package ChildCareTech.model.event;

import ChildCareTech.utils.AbstractGenericDAO;

public class EventDAO extends AbstractGenericDAO<Event, Integer> {
    public EventDAO() {
        super(Event.class);
    }

    @Override
    public void initializeLazyRelations(Event obj) {

    }
}