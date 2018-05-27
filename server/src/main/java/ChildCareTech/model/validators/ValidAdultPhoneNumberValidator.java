package ChildCareTech.model.validators;

import ChildCareTech.model.entities.Adult;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * This class is used to validate the phone number of an Adult entity.
 */
public class ValidAdultPhoneNumberValidator implements ConstraintValidator<ValidAdultPhoneNumber, Adult> {
    /**
     * @param adult the entity to be validated.
     * @param constraintValidatorContext the validation context.
     * @return true if the person associated to the adult has a not-empty and not-null phone number, false otherwise.
     */
    @Override
    public boolean isValid(Adult adult, ConstraintValidatorContext constraintValidatorContext) {
        return adult.getPerson() != null &&
                adult.getPerson().getPhoneNumber() != null &&
                !adult.getPerson().getPhoneNumber().isEmpty();
    }
}
