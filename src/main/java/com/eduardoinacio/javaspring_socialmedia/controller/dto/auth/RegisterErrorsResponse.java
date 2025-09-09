package com.eduardoinacio.javaspring_socialmedia.controller.dto.auth;

import java.util.List;
import java.util.Map;

public record RegisterErrorsResponse(Map<String, List<String>> fieldErrors) {
}
