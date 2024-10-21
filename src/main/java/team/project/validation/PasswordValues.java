package team.project.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordValuesValidation.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValues {
    String message() default "parameters must contain at least one uppercase letter,"
            + " one lowercase letter,"
            + " one digit, and one special character.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
