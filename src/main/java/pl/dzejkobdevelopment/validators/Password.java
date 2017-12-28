package pl.dzejkobdevelopment.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by Jakub Michałowski on 19.11.2017.
 * All rights reserved.
 */

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    String message() default "Hasło musi zawierać duże i małe litery, cyfry oraz składać się z conajmniej 6 znaków (max 20).";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
