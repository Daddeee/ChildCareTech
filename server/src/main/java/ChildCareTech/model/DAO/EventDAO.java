package ChildCareTech.model.DAO;

import ChildCareTech.model.entities.Event;
import ChildCareTech.utils.AbstractGenericDAO;

public class EventDAO extends AbstractGenericDAO<Event, Integer> {
    public EventDAO() { super(Event.class); }

    @Override
    public void initializeLazyRelations(Event obj) {

    }
}
