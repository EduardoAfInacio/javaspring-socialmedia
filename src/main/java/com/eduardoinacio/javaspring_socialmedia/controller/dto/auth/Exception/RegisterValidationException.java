package com.eduardoinacio.javaspring_socialmedia.controller.dto.auth.Exception;

import com.eduardoinacio.javaspring_socialmedia.controller.dto.auth.RegisterErrorsResponse;

import java.util.List;
import java.util.Map;

public class RegisterValidationException extends RuntimeException {
    private Map<String, List<String>> fieldErrors;
    public RegisterValidationException(Map<String, List<String>> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public RegisterErrorsResponse getErrorResponse(){
        return new RegisterErrorsResponse(fieldErrors);
    }
}
