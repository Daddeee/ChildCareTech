package ChildCareTech.model.validators;

import ChildCareTech.model.entities.Adult;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidAdultPhoneNumberValidator implements ConstraintValidator<ValidAdultPhoneNumber, Adult> {
    @Override
    public boolean isValid(Adult adult, ConstraintValidatorContext constraintValidatorContext) {
        return adult.getPerson() != null &&
                adult.getPerson().getPhoneNumber() != null &&
                !adult.getPerson().getPhoneNumber().isEmpty();
    }
}
