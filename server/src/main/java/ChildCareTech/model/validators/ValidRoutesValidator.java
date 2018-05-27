package ChildCareTech.model.validators;

import ChildCareTech.model.entities.Route;
import ChildCareTech.model.entities.Trip;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

/**
 * This class is used to validate the sequence of routes of a Trip entity.
 */
public class ValidRoutesValidator implements ConstraintValidator<ValidRoutes, Trip> {
    /**
     * Check if the routes associated to this trip are in correct order: the arrival location of a route must
     * be the departure location of the next route in the sequence.
     *
     * @param trip the entity to be validated.
     * @param constraintValidatorContext the validation context.
     * @return true if the sequence is correct, false otherwise.
     */
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
