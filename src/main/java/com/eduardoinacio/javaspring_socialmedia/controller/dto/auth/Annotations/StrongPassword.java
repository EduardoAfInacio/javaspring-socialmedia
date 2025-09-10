package com.eduardoinacio.javaspring_socialmedia.controller.dto.auth.Annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StrongPasswordStrengthValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface StrongPassword {
    String message() default "Password must be at least 8 characters long, contain at least one uppercase letter, one lowercase letter, one number and one special character";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
