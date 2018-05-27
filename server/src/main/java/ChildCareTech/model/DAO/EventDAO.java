package ChildCareTech.model.DAO;

import ChildCareTech.model.entities.Event;
import ChildCareTech.model.AbstractGenericDAO;
import org.hibernate.Hibernate;

/**
 * A Data Access Object that operates with Event entities.
 */
public class EventDAO extends AbstractGenericDAO<Event, Integer> {
    public EventDAO() { super(Event.class); }

    @Override
    public void initializeLazyRelations(Event obj) {
        initializeCheckpointRelation(obj);
    }

    private void initializeCheckpointRelation(Event obj) {
        Hibernate.initialize(obj.getCheckpoints());
    }
}
