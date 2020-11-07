package org.imd.jaxrs.sample1.model.dto.validator;

import org.imd.jaxrs.sample1.model.dto.UserDto;
import org.imd.jaxrs.sample1.model.dto.anno.FnameLnameLengthCheck;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FnameLnameLengthCheckValidator implements ConstraintValidator<FnameLnameLengthCheck, UserDto> {

    private FnameLnameLengthCheck constraintAnnotation;

    @Override
    public boolean isValid(UserDto value, ConstraintValidatorContext context) {
        final String fname = value.getFname();
        final String lname = value.getLname();
        return calculateLenght(fname) + calculateLenght(lname) >= constraintAnnotation.length();
    }

    private int calculateLenght(String s) {
        return (s != null) ? s.length() : 0;
    }


    @Override
    public void initialize(FnameLnameLengthCheck constraintAnnotation) {
        this.constraintAnnotation = constraintAnnotation;
    }
}
