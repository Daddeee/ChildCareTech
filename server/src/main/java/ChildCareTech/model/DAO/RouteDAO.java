package ChildCareTech.model.DAO;

import ChildCareTech.model.entities.Route;
import ChildCareTech.model.AbstractGenericDAO;

public class RouteDAO extends AbstractGenericDAO<Route, Integer> {
    public RouteDAO() {
        super(Route.class);
    }

    @Override
    public void initializeLazyRelations(Route obj) {

    }
}