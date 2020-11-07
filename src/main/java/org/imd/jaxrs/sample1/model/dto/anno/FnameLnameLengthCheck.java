package org.imd.jaxrs.sample1.model.dto.anno;

import org.imd.jaxrs.sample1.model.dto.validator.FnameLnameLengthCheckValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FnameLnameLengthCheckValidator.class})
public @interface FnameLnameLengthCheck {

    String message() default "fname and lname combination is too short.";

    int length() default 3;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
