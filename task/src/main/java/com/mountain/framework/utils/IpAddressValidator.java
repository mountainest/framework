package com.mountain.framework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IpAddressValidator implements ConstraintValidator<IpAddress, String> {
    private static final Pattern PATTERN =
        Pattern.compile("^([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})$");

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        // 不影响非必填项
        if (s == null || s.length() <= 0) {
            return true;
        }

        Matcher matcher = IpAddressValidator.PATTERN.matcher(s);
        try {
            if (!matcher.matches()) {
                return false;
            } else {
                for (int i = 1; i <= 4; i++) {
                    int octet = Integer.valueOf(matcher.group(i));
                    if (octet > 255) {
                        return false;
                    }
                }
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
