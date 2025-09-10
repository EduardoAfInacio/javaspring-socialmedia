package com.eduardoinacio.javaspring_socialmedia.controller.dto.auth.Annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class DateOfBirthValidator implements ConstraintValidator<ValidDateOfBirth, LocalDate> {
    private int minAge;

    @Override
    public void initialize(ValidDateOfBirth constraintAnnotation) {
        this.minAge = constraintAnnotation.age();
    }

    @Override
    public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext constraintValidatorContext) {
        if (dateOfBirth == null) {
            return true;
        }

        LocalDate minAgeDate = LocalDate.now().minusYears(minAge);

        return !dateOfBirth.isAfter(minAgeDate);
    }
}
