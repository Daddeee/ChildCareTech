package ChildCareTech.model.DAO;

import ChildCareTech.model.entities.Bus;
import ChildCareTech.model.entities.Trip;
import ChildCareTech.model.entities.TripPartecipation;
import ChildCareTech.model.AbstractGenericDAO;
import org.hibernate.query.Query;

/**
 * A Data Access Object that operates with TripPartecipation entities.
 */
public class TripPartecipationDAO extends AbstractGenericDAO<TripPartecipation, Integer> {
    public TripPartecipationDAO() {
        super(TripPartecipation.class);
    }

    /**
     * Deletes all the trip partecipations associated to the given trip and the given bus.
     * <p>
     * This method is used as a "cleanup" procedure when removing a bus from his associated trip.
     *
     * @param trip the trip entity
     * @param bus the bus entity
     */
    public void removeAssociatedTripPartecipations(Trip trip, Bus bus){
        Query query = session.createQuery(
                "delete from TripPartecipation " +
                        "where bus = :bus and trip = :trip"
        ).setParameter("bus", bus).setParameter("trip", trip);

        query.executeUpdate();
    }

    @Override
    public void initializeLazyRelations(TripPartecipation tripPartecipation) {}
}