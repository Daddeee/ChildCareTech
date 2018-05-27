package ChildCareTech.model.DAO;

import ChildCareTech.model.entities.Route;
import ChildCareTech.model.AbstractGenericDAO;

/**
 * A Data Access Object that operates with Route entities.
 */
public class RouteDAO extends AbstractGenericDAO<Route, Integer> {
    public RouteDAO() {
        super(Route.class);
    }

    @Override
    public void initializeLazyRelations(Route obj) {

    }
}