package com.example.productservice.validator;



import com.example.productservice.annotation.Duration;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DurationValidator implements ConstraintValidator<Duration, LocalDate> {

    @Override
    public void initialize(Duration constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        if (localDate == null) {
            return false;
        }
        return localDate.isAfter(LocalDate.of(1900, 1,1))  && localDate.isBefore(LocalDate.of(2100, 12, 31)) ;
    }
}
