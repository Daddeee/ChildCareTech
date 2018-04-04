package ChildCareTech.model.route;

import ChildCareTech.utils.GenericDAO;

public class RouteDAO extends GenericDAO<Route, Integer> {
    public RouteDAO() {
        super(Route.class);
    }
}