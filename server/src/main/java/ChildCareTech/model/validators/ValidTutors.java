package ChildCareTech.model.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidTutorsValidator.class})
@Documented
public @interface ValidTutors {
    String message() default "{ChildCareTech.model.validators" +
            "ValidTutors.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
