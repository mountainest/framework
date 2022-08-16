package io.github.mountainest.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.OffsetDateTime;

public class MyDateTimeFormatValidator implements ConstraintValidator<MyDateTimeFormat, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            OffsetDateTime.parse(s);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
