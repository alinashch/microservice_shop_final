package com.example.productservice.annotation;


import com.example.productservice.validator.DurationValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DurationValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Duration {

    String message() default "Duration must be between 0:00 and 7:00";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}