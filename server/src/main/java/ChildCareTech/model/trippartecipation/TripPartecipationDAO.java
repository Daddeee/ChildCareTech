package ChildCareTech.model.trippartecipation;

import ChildCareTech.utils.AbstractGenericDAO;

public class TripPartecipationDAO extends AbstractGenericDAO<TripPartecipation, Integer> {
    public TripPartecipationDAO() {
        super(TripPartecipation.class);
    }

    @Override
    public void initializeLazyRelations(TripPartecipation tripPartecipation) {}
}