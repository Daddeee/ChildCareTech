package ChildCareTech.model.DAO;

import ChildCareTech.model.entities.Bus;
import ChildCareTech.model.entities.Trip;
import ChildCareTech.model.AbstractGenericDAO;
import org.hibernate.Hibernate;
import org.hibernate.query.Query;

import java.util.List;

/**
 * A Data Access Object that operates with Bus entities.
 */
public class BusDAO extends AbstractGenericDAO<Bus, Integer> {
    public BusDAO() {
        super(Bus.class);
    }

    /**
     * Get all buses that are available for use in the provided trip.
     * For a bus to be available, it must not be already associated to a trip that
     * overlaps temporally with the provided trip.
     *
     * @param trip the trip for which buses are searched.
     * @return a List containing all available bus entities.
     */
    public List<Bus> getAvailableBuses(Trip trip){
        Query<Bus> query = session.createQuery(
                "from Bus " +
                   "where id not in (select b.id " +
                        "from Bus b join b.trips t " +
                        "where (t.depDate <= :arrDate and t.arrDate >= :depDate) " +
                                "or t.id = :id)"
                , Bus.class)
                .setParameter("id", trip.getId())
                .setParameter("arrDate", trip.getArrDate())
                .setParameter("depDate", trip.getDepDate());
        return query.getResultList();
    }

    @Override
    public void initializeLazyRelations(Bus obj) {
        initializeTripPartecipationRelation(obj);
        initializeTripRelation(obj);
    }

    private void initializeTripPartecipationRelation(Bus obj) {
        Hibernate.initialize(obj.getTripPartecipations());
    }

    private void initializeTripRelation(Bus obj) {
        Hibernate.initialize(obj.getTrips());
    }
}