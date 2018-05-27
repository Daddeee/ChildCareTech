package ChildCareTech.model.DAO;

import ChildCareTech.model.entities.Kid;
import ChildCareTech.model.entities.Trip;
import ChildCareTech.model.AbstractGenericDAO;
import org.hibernate.Hibernate;
import org.hibernate.query.Query;

import java.util.List;

/**
 * A Data Access Object that operates with Kid entities.
 */
public class KidDAO extends AbstractGenericDAO<Kid, Integer> {
    public KidDAO() {
        super(Kid.class);
    }

    /**
     * Get all kids that can partecipate in the provided trip.
     * For a kid to be available, it must not be already associated to a trip that
     * overlaps temporally with the provided trip.
     *
     * @param trip the trip for which kids are searched.
     * @return a List containing all available kid entities.
     */
    public List<Kid> getAvailableKids(Trip trip){
        Query<Kid> query = session.createQuery(
                "from Kid " +
                        "where person not in (" +
                            "select t.person " +
                            "from TripPartecipation t " +
                            "where t.trip.depDate <= :arrDate and t.trip.arrDate >= :depDate)"
                , Kid.class)
                .setParameter("arrDate", trip.getArrDate())
                .setParameter("depDate", trip.getDepDate());
        return query.getResultList();
    }

    @Override
    public void initializeLazyRelations(Kid obj) {
        initializeContactsRelation(obj);
        initializeAllergiesRelation(obj);
    }

    private void initializeContactsRelation(Kid obj) {
        Hibernate.initialize(obj.getContacts());
    }
    private void initializeAllergiesRelation(Kid obj) {
        Hibernate.initialize(obj.getPerson().getAllergies());
    }
}