package io.github.mountainest.utils;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyDateTimeFormatValidator.class)
public @interface MyDateTimeFormat {
    String message() default "时间必须满足3000-01-01T00:00:00+00:00格式";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
