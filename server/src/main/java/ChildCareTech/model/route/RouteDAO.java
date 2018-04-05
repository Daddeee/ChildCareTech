package ChildCareTech.model.route;

import ChildCareTech.utils.AbstractGenericDAO;

public class RouteDAO extends AbstractGenericDAO<Route, Integer> {
    public RouteDAO() {
        super(Route.class);
    }
}