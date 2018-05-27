package ChildCareTech.model.validators;

import ChildCareTech.model.entities.Kid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * This class is used to validate the tutors a Kid entity.
 */
public class ValidTutorsValidator implements ConstraintValidator<ValidTutors, Kid> {
    /**
     * @param kid the entity to be validated.
     * @param constraintValidatorContext the validation context.
     * @return true if the kid has at least 1 tutor, false otherwise.
     */
    @Override
    public boolean isValid(Kid kid, ConstraintValidatorContext constraintValidatorContext) {
        return !(kid.getFirstTutor() == null && kid.getSecondTutor() == null);
    }
}
