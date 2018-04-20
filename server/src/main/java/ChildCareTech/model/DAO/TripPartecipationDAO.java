package ChildCareTech.model.DAO;

import ChildCareTech.model.entities.Bus;
import ChildCareTech.model.entities.Trip;
import ChildCareTech.model.entities.TripPartecipation;
import ChildCareTech.utils.AbstractGenericDAO;
import org.hibernate.query.Query;

public class TripPartecipationDAO extends AbstractGenericDAO<TripPartecipation, Integer> {
    public TripPartecipationDAO() {
        super(TripPartecipation.class);
    }

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