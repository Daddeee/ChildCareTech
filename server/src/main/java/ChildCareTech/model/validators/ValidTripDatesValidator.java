package ChildCareTech.model.validators;

import ChildCareTech.model.entities.Trip;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * This class is used to validate the dates of a Trip entity.
 */
public class ValidTripDatesValidator implements ConstraintValidator<ValidTripDates, Trip> {
    /**
     * Check if the dates (arrival and departure) associated to this trip are correctly assigned: both must be not null and
     * the departure date can't surpass the arrival date.
     *
     * @param trip the entity to be validated.
     * @param constraintValidatorContext the validation context.
     * @return true if both dates are correct, false otherwise.
     */
    @Override
    public boolean isValid(Trip trip, ConstraintValidatorContext constraintValidatorContext) {
        if(trip == null || trip.getArrDate() == null || trip.getDepDate() == null)
            return true;

        return !(trip.getDepDate().isAfter(trip.getArrDate()));
    }
}
