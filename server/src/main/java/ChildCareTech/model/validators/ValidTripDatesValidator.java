package ChildCareTech.model.validators;

import ChildCareTech.model.entities.Trip;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidTripDatesValidator implements ConstraintValidator<ValidTripDates, Trip> {
    @Override
    public void initialize(ValidTripDates constraintAnnotation) {}

    @Override
    public boolean isValid(Trip trip, ConstraintValidatorContext constraintValidatorContext) {
        if(trip == null || trip.getArrDate() == null || trip.getDepDate() == null)
            return true;

        return !(trip.getDepDate().isAfter(trip.getArrDate()));
    }
}
