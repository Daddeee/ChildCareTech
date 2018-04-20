package ChildCareTech.model.validators;

import ChildCareTech.model.entities.Kid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidTutorsValidator implements ConstraintValidator<ValidTutors, Kid> {
    @Override
    public boolean isValid(Kid kid, ConstraintValidatorContext constraintValidatorContext) {
        return !(kid.getFirstTutor() == null && kid.getSecondTutor() == null);
    }
}
