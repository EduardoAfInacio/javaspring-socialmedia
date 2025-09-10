package com.eduardoinacio.javaspring_socialmedia.config;

import com.eduardoinacio.javaspring_socialmedia.controller.dto.auth.Exception.RegisterValidationException;
import com.eduardoinacio.javaspring_socialmedia.controller.dto.auth.RegisterErrorsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(RegisterValidationException.class)
    public ResponseEntity<RegisterErrorsResponse> handleRegisterValidationException(RegisterValidationException ex) {
        return ResponseEntity.badRequest().body(ex.getErrorResponse());
    }
}
