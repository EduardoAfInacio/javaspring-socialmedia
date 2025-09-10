package com.eduardoinacio.javaspring_socialmedia.controller.dto.auth.Annotations;

import com.eduardoinacio.javaspring_socialmedia.Utils.PasswordStrengthVerification;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StrongPasswordStrengthValidator implements ConstraintValidator<StrongPassword, String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        if(password == null || password.isBlank()){
            return true;
        }

        var errors = PasswordStrengthVerification.verifyPasswordStrength(password);

        if(!errors.isEmpty()){
            constraintValidatorContext.disableDefaultConstraintViolation();

            for(String error : errors){
                constraintValidatorContext.buildConstraintViolationWithTemplate(error).addConstraintViolation();
            }

            return false;
        }

        return true;
    }
}
