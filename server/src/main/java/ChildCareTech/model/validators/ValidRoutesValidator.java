package ChildCareTech.model.validators;

import ChildCareTech.model.entities.Route;
import ChildCareTech.model.entities.Trip;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

public class ValidRoutesValidator implements ConstraintValidator<ValidRoutes, Trip> {
    @Override
    public void initialize(ValidRoutes constraintAnnotation) {

    }

    @Override
    public boolean isValid(Trip trip, ConstraintValidatorContext constraintValidatorContext) {
        Set<Route> routes = trip.getRoutes();
        int n = routes.size();

        if(n == 0) return true;

        Route[] sortedRoutes = new Route[n];
        for(Route r : routes)
            sortedRoutes[r.getRouteNumber() - 1] = r;

        for(int i = 0; i < n - 1; i++)
            if(!areConnected(sortedRoutes[i], sortedRoutes[i+1]))
                return false;
        return true;
    }

    private boolean areConnected(Route a, Route b){
        return a.getArrivalLocation().equals(b.getDepartureLocation());
    }
}
